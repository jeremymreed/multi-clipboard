/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffercontrol;

import com.jeremyr.multiclipboard.wrappers.JavaFXClipboardWrapper;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jeremyr
 */
public class BufferControl extends GridPane {

  private final JavaFXClipboardWrapper clipboardWrapper;
  private volatile SimpleStringProperty clipboardText;

  @FXML
  private TextArea buffer;

  @FXML
  private RadioButton bufferTextWrapRadioButton;

  public BufferControl(SimpleStringProperty clipboardText) {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BufferLayout.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    this.clipboardWrapper = new JavaFXClipboardWrapper();
    this.clipboardText = clipboardText;

    try {
      fxmlLoader.load();
    }
    catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }
  }

  @FXML
  protected void handleReadClipboardButtonAction(ActionEvent event) {
    this.buffer.textProperty().set(this.clipboardWrapper.readClipboard());
  }

  @FXML
  protected void handleWriteClipboardButtonAction(ActionEvent event) {
    this.clipboardWrapper.writeClipboard(this.buffer.textProperty().get());
  }

  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    this.buffer.textProperty().set("");
  }

  @FXML
  protected void handleBufferWrapTextRadioButtonAction(ActionEvent event) {
    this.buffer.wrapTextProperty().set(this.bufferTextWrapRadioButton.isSelected());
  }

  @FXML
  protected void handleRemoveBufferButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
