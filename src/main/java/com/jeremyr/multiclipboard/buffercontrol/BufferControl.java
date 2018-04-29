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
import javafx.scene.layout.GridPane;

/**
 *
 * @author jeremyr
 */
public class BufferControl extends GridPane {

  private final JavaFXClipboardWrapper clipboardWrapper;
  private volatile SimpleStringProperty clipboardText;
  
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
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @FXML
  protected void handleWriteClipboardButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @FXML
  protected void handleBufferWrapTextRadioButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @FXML
  protected void handleRemoveBufferButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
