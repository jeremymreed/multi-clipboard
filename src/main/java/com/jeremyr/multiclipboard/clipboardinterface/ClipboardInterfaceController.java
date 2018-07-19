/*
 * The MIT License
 *
 * Copyright 2018 Jeremy M. Reed.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.jeremyr.multiclipboard.clipboardinterface;

import com.jeremyr.multiclipboard.buffertableview.buttoncell.ButtonCell;
import com.jeremyr.multiclipboard.buffertableview.eventhandlers.BufferNameEditCommitEventHandler;
import com.jeremyr.multiclipboard.buffertableview.listeners.DataTableRowSelectionListener;
import com.jeremyr.multiclipboard.buffertableview.models.Buffer;
import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import com.jeremyr.multiclipboard.wrappers.JavaFXClipboardWrapper;
import com.jeremyr.multiclipboard.Version;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Region;
import javafx.util.Callback;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterfaceController {

  @FXML
  private TextArea clipboardTextArea;

  @FXML
  private TableView<BufferBase> dataTable;

  @FXML
  private TextArea bufferTextArea;

  @FXML
  private Button readFromClipboardButton;

  @FXML
  private Button writeToClipboardButton;

  @FXML
  private Button clearBufferButton;

  @FXML
  private RadioButton nukeClipboardRadioButton;

  @FXML
  private RadioButton bufferTextAreaWrapTextRadioButton;

  @FXML
  private RadioButton clipboardTextAreaWrapTextRadioButton;

  /* TODO: Change this variable name to something less ambiguous */
  int nextIndex;

  /**
   * JavaFX System Clipboard Wrapper object.
   */
  private JavaFXClipboardWrapper clipboardInterface;

  /* Passed in to ClipboardBuffer. Updated by the ClipboardMonitor */
  private SimpleStringProperty clipboardContents;

  /** This AtomicBoolean controls the Clipboard Monitor's nuke clipboard feature */
  private final AtomicBoolean shouldNukeClipboard;

  public ClipboardInterfaceController() {
    this.shouldNukeClipboard = new AtomicBoolean();
    this.shouldNukeClipboard.set(false);
    this.nextIndex = 0;
  }

  public void setJavaFXClipboardWrapper(JavaFXClipboardWrapper javaFXClipboardWrapper) {
    this.clipboardInterface = javaFXClipboardWrapper;
  }

  public void initialize() {
    this.clipboardContents = new SimpleStringProperty( );
    this.clipboardTextArea.textProperty().bind(this.clipboardContents);

    // Want TextAreas wrap text set to true by default.
    this.clipboardTextArea.wrapTextProperty().set(true);
    this.bufferTextArea.wrapTextProperty().set(true);

    this.clipboardTextAreaWrapTextRadioButton.selectedProperty().set(true);
    this.bufferTextAreaWrapTextRadioButton.selectedProperty().set(true);

    // Buffer TableView setup stuff.
    TableColumn<BufferBase, String> nameColumn = new TableColumn<>("Buffer Name");
    nameColumn.prefWidthProperty().bind(this.dataTable.widthProperty().multiply(0.3));
    TableColumn<BufferBase, String> createDateColumn = new TableColumn<>("Created");
    createDateColumn.prefWidthProperty().bind(this.dataTable.widthProperty().multiply(0.5));
    TableColumn<BufferBase, Boolean> removeButtonColumn = new TableColumn<>("");
    removeButtonColumn.prefWidthProperty().bind(this.dataTable.widthProperty().multiply(0.2));

    this.dataTable.setEditable(true);
    this.bufferTextArea.setEditable(false);

    // Deactivate bufferTextArea controls if bufferTextArea is not editable.
    BooleanBinding bufferTextAreaNotEditable = Bindings.not(this.bufferTextArea.editableProperty());

    this.readFromClipboardButton.disableProperty().bind(bufferTextAreaNotEditable);
    this.writeToClipboardButton.disableProperty().bind(bufferTextAreaNotEditable);
    this.clearBufferButton.disableProperty().bind(bufferTextAreaNotEditable);

    ObservableList<BufferBase> data = FXCollections.observableArrayList();

    nameColumn.setOnEditCommit(new BufferNameEditCommitEventHandler());
    nameColumn.setCellFactory(TextFieldTableCell.<BufferBase>forTableColumn());
    nameColumn.setCellValueFactory(new PropertyValueFactory<BufferBase, String>("name"));
    createDateColumn.setCellValueFactory(new PropertyValueFactory<BufferBase, String>("createDate"));
    removeButtonColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BufferBase, Boolean>, ObservableValue<Boolean>>() {
      @Override
      public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<BufferBase, Boolean> cellDataFeatures) {
        return new SimpleBooleanProperty(cellDataFeatures.getValue() != null);
      }
    });

    removeButtonColumn.setCellFactory(new Callback<TableColumn<BufferBase, Boolean>, TableCell<BufferBase, Boolean>>() {
      @Override
      public TableCell<BufferBase, Boolean> call(TableColumn<BufferBase, Boolean> removeBufferButtonColumn) {
        return new ButtonCell(dataTable);
      }
    });

    this.dataTable.getColumns().addAll(nameColumn, createDateColumn, removeButtonColumn);
    this.dataTable.setItems(data);
    this.dataTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    this.dataTable.getSelectionModel().selectedItemProperty().addListener(new DataTableRowSelectionListener<>(this.bufferTextArea, this.clipboardContents));
  }

  /**
   * Returns a reference to the SimpleStringProperty observable value bound to the clipboard TextArea.
   * Needed by the Thread Manager.
   *
   * @return  The SimpleStringProperty observable value bound to the clipboard TextArea.
   */
  public SimpleStringProperty getClipboardContents() {
    return this.clipboardContents;
  }

  /**
   * Returns a reference to the AtomicBoolean variable used to control the
   * Clipboard Monitor's nuke clipboard feature.
   *
   * @return The AtomicBoolean that controls the Clipboard Monitor's nuke clipboard feature
   */
  public AtomicBoolean getShouldNukeClipboard() {
    return this.shouldNukeClipboard;
  }

  /**
   * This handler is invoked when the user clicks on the "File.Close" menu item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleCloseAction(ActionEvent event) {
    Platform.exit();
  }

  /**
   * This handler is invoked when the user clicks on the "Help.About" menu item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleAboutAction(ActionEvent event) {
    Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
    aboutAlert.setTitle("About");
    aboutAlert.setHeaderText("About this application");
    aboutAlert.setContentText(
            "This application was created by Jeremy M. Reed\nVersion: "
                    + Version.getVersion() + "-" + Version.getType()
                    + "\nThis application is licensed under the MIT License");

    aboutAlert.showAndWait();
  }

  /**
   * This handler is invoked when the user clicks on the "Help.License" menu
   * item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleLicenseAction(ActionEvent event) {
    Alert licenseAlert = new Alert(Alert.AlertType.INFORMATION);
    licenseAlert.setTitle("About");
    licenseAlert.setHeaderText("The MIT License");
    licenseAlert.setContentText("Copyright © 2018 Jeremy M. Reed\n"
            + "\n"
            + "Permission is hereby granted, free of charge, to any person\n"
            + "obtaining a copy of this software and associated documentation\n"
            + "files (the “Software”), to deal in the Software without\n"
            + "restriction, including without limitation the rights to use,\n"
            + "copy, modify, merge, publish, distribute, sublicense, and/or sell\n"
            + "copies of the Software, and to permit persons to whom the\n"
            + "Software is furnished to do so, subject to the following\n"
            + "conditions:\n"
            + "\n"
            + "The above copyright notice and this permission notice shall be\n"
            + "included in all copies or substantial portions of the Software.\n"
            + "\n"
            + "THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,\n"
            + "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES\n"
            + "OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND\n"
            + "NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT\n"
            + "HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,\n"
            + "WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n"
            + "FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR\n"
            + "OTHER DEALINGS IN THE SOFTWARE.");
    licenseAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    licenseAlert.getDialogPane().setMinWidth(550);

    licenseAlert.showAndWait();
  }

  @FXML
  protected void handleClearClipboardButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard("");
  }

  @FXML
  protected void handleToggleNukeClipboardRadioButton(ActionEvent event) {
    this.shouldNukeClipboard.set(this.nukeClipboardRadioButton.isSelected());
  }

  @FXML
  protected void handleReadFromClipboardButtonAction(ActionEvent event) {
    String data = this.clipboardInterface.readClipboard();
    if (this.bufferTextArea.isEditable()) {
      this.bufferTextArea.setText(data);
    }
  }

  @FXML
  protected void handleWriteToClipboardButtonAction(ActionEvent event) {
    if (this.bufferTextArea.isEditable()) {
      this.clipboardInterface.writeClipboard(this.bufferTextArea.getText());
    }
  }

  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    if (this.bufferTextArea.isEditable()) {
      this.bufferTextArea.setText("");
    }
  }

  @FXML
  protected void handleToggleBufferWrapTextRadioButton(ActionEvent event) {
    this.bufferTextArea.setWrapText(this.bufferTextAreaWrapTextRadioButton.isSelected());
  }

  @FXML
  protected void handleToggleClipboardWrapTextRadioButton(ActionEvent event) {
    this.clipboardTextArea.setWrapText(this.clipboardTextAreaWrapTextRadioButton.isSelected());
  }

  @FXML
  protected void handleAddBufferButtonAction(ActionEvent event) {
    Buffer newPerson = new Buffer(this.nextIndex, "New Buffer");
    this.dataTable.getItems().add(newPerson);

    this.nextIndex += 1;
  }
}
