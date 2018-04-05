/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffercontrol;

import com.jeremyr.multiclipboard.wrappers.JavaFXClipboardWrapper;
import java.io.IOException;
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

  /** Reference to the JavaFX System Clipboard. */
  private final JavaFXClipboardWrapper clipboardWrapper;

  /** Index number of this buffer */
  private Integer index;

  /** Reference to the buffer TextArea */
  @FXML
  private TextArea buffer;

  /** Reference to the wrap text radio button */
  @FXML
  private RadioButton bufferTextWrapRadioButton;

  /**
   * Default constructor.
   * Sets the index, gets the layout, and makes this class the controller for
   * that layout.
   *
   * @param index The index number for this buffer instance.
   */
  public BufferControl(int index) {
    this.index = index;
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BufferLayout.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    this.clipboardWrapper = new JavaFXClipboardWrapper();

    try {
      fxmlLoader.load();
    }
    catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }
  }

  /**
   * Set up after the FXML is loaded.
   * This one is for debug purposes only.
   */
  public void initialize() {
    this.buffer.textProperty().set(this.index.toString());
  }

  /**
   * Called when the user clicks the read from clipboard button.
   * Copies the JavaFX System Clipboard Contents to the buffer TextArea.
   *
   * @param event The ActionEvent instance.
   */
  @FXML
  protected void handleReadClipboardButtonAction(ActionEvent event) {
    this.buffer.textProperty().set(this.clipboardWrapper.readClipboard());
  }

  /**
   * Called when the user clicks the write to clipboard button.
   * Copies the buffer TextArea textProperty to the JavaFX System Clipboard.
   *
   * @param event The ActionEvent instance.
   */
  @FXML
  protected void handleWriteClipboardButtonAction(ActionEvent event) {
    this.clipboardWrapper.writeClipboard(this.buffer.textProperty().get());
  }

  /**
   * Called when the user clicks the clear buffer button.
   * Sets the buffer TextArea textProperty to the empty string.
   *
   * @param event The ActionEvent instance.
   */
  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    this.buffer.textProperty().set("");
  }

  /**
   * Called when the user toggles the wrap text radio button.
   * Sets the buffer wrapTextProperty to the value of bufferTextWrapRadioButton.isSelected().
   *
   * @param event The ActionEvent instance.
   */
  @FXML
  protected void handleBufferWrapTextRadioButtonAction(ActionEvent event) {
    this.buffer.wrapTextProperty().set(this.bufferTextWrapRadioButton.isSelected());
  }

  /**
   * Called when the user clicks the remove buffer button.
   * Remove ourself from the ObservableList.
   *
   * @param event The ActionEvent instance.
   */
  @FXML
  protected void handleRemoveBufferButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
