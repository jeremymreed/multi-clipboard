/*
 * Copyright © 2018 Jeremy M. Reed

 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:

 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.jeremyr.multiclipboard.clipboarduserinterface;

import com.jeremyr.multiclipboard.wrappers.JavaFXClipboardWrapper;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * JavaFX Controller for the ClipboardUserInterface.
 * This is the primary user interface the user will interact with.
 *
 * @author Jeremy M. Reed
 */
public class ClipboardUserInterfaceController {

  private SimpleStringProperty text;

  /** JavaFX System Clipboard Wrapper object. */
  final private JavaFXClipboardWrapper clipboardInterface;

  /** Displays a status message at the bottom of the stage. */
  @FXML
  private Text statusmessage;

  /** This TextArea is the buffer.  This is a storage area or scratch editing area */
  @FXML
  private TextArea buffer;

  /**
   * This RadioButton toggles the bufferWrapText's wrap text property.
   */
  @FXML
  private RadioButton bufferWrapText;

  /**
   * This TextArea is the clipboard.  This is where the clipboard's current contents are shown to the user.
   * The user should not be able to edit this TextArea.  It is programmatically updated.
   *
   * This TextArea's textProperty is bound to an SimpleStringProperty observable value.
   *
   * TODO: Making this read only makes it rather difficult to read the text.  Find a way to make this readable, while
   * enforcing the read-only requirement.
   */
  @FXML
  private TextArea clipboard;

  /**
   * This RadioButton toggles the clipboardWrapText's wrap text property.
   */
  @FXML
  private RadioButton clipboardWrapText;
  /**
   * Default Constructor.
   *
   * Gets JavaFXClipboardWrapper instance.
   * Sets non-FXML fields to default values.
   */
  public ClipboardUserInterfaceController( ) {
    this.clipboardInterface = new JavaFXClipboardWrapper();
  }

  /**
   * Dependency Injection Constructor.
   * Get JavaFXClipboardWrapper from constructor parameters.
   * Set non-FXML fields to default values.
   *
   * @param clipboardInterface
   */
  public ClipboardUserInterfaceController(JavaFXClipboardWrapper clipboardInterface) {
    this.clipboardInterface = clipboardInterface;
  }

  /**
   *  Sets up FXML annotated fields.
   *
   *  This method is called in the process of creating a JavaFX controller.
   *  First the constructor is called, then FXML annotated fields are populated,
   *  then this method gets called.  It is called automatically as part of the
   *  creation process.
   */
  public void initialize( ) {
    this.text = new SimpleStringProperty( );
    this.clipboard.textProperty().bind(this.text);
    this.text.set("");
  }

  /**
   * Returns a reference to the SimpleStringProperty observable value bound to the clipboard TextArea.
   * Needed by the Thread Manager.
   *
   * @return  The SimpleStringProperty observable value bound to the clipboard TextArea.
   */
  public SimpleStringProperty getText() {
    return this.text;
  }

  /**
   * This handler is invoked when the user clicks on the "File->Close" menu item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleCloseAction(ActionEvent event) {
    Platform.exit();
  }

  /**
   * This handler is invoked when the user clicks on the "Help->About" menu item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleAboutAction(ActionEvent event) {
    Alert aboutAlert = new Alert(AlertType.INFORMATION);
    aboutAlert.setTitle("About");
    aboutAlert.setHeaderText("About this application");
    aboutAlert.setContentText("This application was created by Jeremy M. Reed\nVersion: 2.4.0\nThis application is licensed under the MIT License");

    aboutAlert.showAndWait();
  }

  /**
   * This handler is invoked when the user clicks on the "Help->License" menu item.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleLicenseAction(ActionEvent event) {
    Alert licenseAlert = new Alert(AlertType.INFORMATION);
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

  /**
   * This handler is invoked when the user clicks on the "Clear Clipboard"
   * Button.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleClearClipboardButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard("");
    statusmessage.setText("Cleared the clipboard");
  }

  /**
   * This handler is invoked when the user toggles the "Wrap Text" Radio Box
   * with the "clipboard-wrap-text-radio-button" id.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleClipboardWrapTextRadioButtonAction(ActionEvent event) {
    this.clipboard.setWrapText(this.clipboardWrapText.isSelected());
    statusmessage.setText("Clipboard Wrap Text " + (this.clipboardWrapText.isSelected() ? "enabled" : "disabled"));
  }

  /**
   * This handler is invoked when the user clicks on the "Write To Clipboard"
   * Button.
   *
   * TODO: Change the name of this method to make it clearer which radio button it's associated with...
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleWriteButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard(this.buffer.getText());
    statusmessage.setText("Wrote buffer contents to the clipboard");
  }

  /**
   * This handler is invoked when the user clicks on the "Read From Clipboard"
   * Button.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleReadButtonAction(ActionEvent event) {
    String data = this.clipboardInterface.readClipboard();
    this.buffer.setText(data);
    statusmessage.setText("Copied clipboard contents to the buffer");
  }

  /**
   * This handler is invoked when the user clicks on the "Clear Buffer"
   * Button.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    this.buffer.setText("");
    statusmessage.setText("Cleared the buffer");
  }

  /**
   * This handler is invoked when the user clicks on the "Wrap Text"
   * Radio Button with the "buffer-wrap-text-radio-button" id.
   *
   * @param event The ActionEvent object describing the event.
   */
  @FXML
  protected void handleBufferWrapTextRadioButtonAction(ActionEvent event) {
    this.buffer.setWrapText(this.bufferWrapText.isSelected());
    statusmessage.setText("Buffer Wrap Text " + (this.bufferWrapText.isSelected() ? "enabled" : "disabled"));
  }
}
