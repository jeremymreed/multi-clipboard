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
package com.jeremyr.multiclipboard.clipboardinterface;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterfaceController {

  private SimpleStringProperty text;

  final private ClipboardInterface clipboardInterface;

  @FXML
  private Text statusmessage;
  @FXML
  private TextArea buffer;
  @FXML
  private TextArea clipboard;

  private Boolean shouldClipboardWrapText;
  private Boolean shouldBufferWrapText;

  public ClipboardInterfaceController( ) {
    this.clipboardInterface = new ClipboardInterface();
    this.shouldClipboardWrapText = false;
    this.shouldBufferWrapText = false;
  }

  public void initialize( ) {
    this.text = new SimpleStringProperty( );
    this.clipboard.textProperty().bind(this.text);
    this.text.set("");
  }

  public SimpleStringProperty getText() {
    return this.text;
  }

  @FXML
  protected void handleCloseAction(ActionEvent event) {
    Platform.exit();
  }

  @FXML
  protected void handleAboutAction(ActionEvent event) {
    Alert aboutAlert = new Alert(AlertType.INFORMATION);
    aboutAlert.setTitle("About");
    aboutAlert.setHeaderText("About this application");
    aboutAlert.setContentText("This application was created by Jeremy M. Reed\nVersion: 2.4.0\nThis application is licensed under the MIT License");

    aboutAlert.showAndWait();
  }

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

  @FXML
  protected void handleClearClipboardButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard("");
    statusmessage.setText("Cleared the clipboard");
  }

  @FXML
  protected void handleWrapTextRadioButtonAction(ActionEvent event) {
    this.shouldClipboardWrapText = !this.shouldClipboardWrapText;
    this.clipboard.setWrapText(this.shouldClipboardWrapText);
    statusmessage.setText("Clipboard Wrap Text " + (this.shouldClipboardWrapText ? "enabled" : "disabled"));
  }

  @FXML
  protected void handleWriteButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard(this.buffer.getText());
    statusmessage.setText("Wrote buffer contents to the clipboard");
  }

  @FXML
  protected void handleReadButtonAction(ActionEvent event) {
    String data = this.clipboardInterface.readClipboard();
    this.buffer.setText(data);
    statusmessage.setText("Copied clipboard contents to the buffer");
  }

  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    this.buffer.setText("");
    statusmessage.setText("Cleared the buffer");
  }

  @FXML
  protected void handleBufferWrapTextRadioButtonAction(ActionEvent event) {
    this.shouldBufferWrapText = !this.shouldBufferWrapText;
    this.buffer.setWrapText(this.shouldBufferWrapText);
    statusmessage.setText("Buffer Wrap Text " + (this.shouldBufferWrapText ? "enabled" : "disabled"));
  }
}
