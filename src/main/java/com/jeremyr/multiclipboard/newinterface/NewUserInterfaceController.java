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
package com.jeremyr.multiclipboard.newinterface;

import java.util.concurrent.atomic.AtomicBoolean;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author jeremyr
 */
public class NewUserInterfaceController {

  /* Bound to the clipboard TextArea (Will be bound to the clipboard buffer eventually) */
  private SimpleStringProperty text;

  /** This AtomicBoolean controls the Clipboard Monitor's nuke clipboard feature */
  private AtomicBoolean shouldNukeClipboard;

  public NewUserInterfaceController() {
    this.shouldNukeClipboard = new AtomicBoolean();
    this.shouldNukeClipboard.set(false);
  }

  public void initialize() {
    this.text = new SimpleStringProperty( );
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
   * Returns a reference to the AtomicBoolean variable used to control the
   * Clipboard Monitor's nuke clipboard feature.
   *
   * @return The AtomicBoolean that controls the Clipboard Monitor's nuke clipboard feature
   */
  public AtomicBoolean getShouldNukeClipboard() {
    return this.shouldNukeClipboard;
  }

  @FXML
  protected void handleCloseAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleAboutAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleLicenseAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleClearClipboardButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleToggleNukeClipboardRadioButton(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleReadFromClipboardButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleWriteToClipboardButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleClearBufferButtonAction(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }

  @FXML
  protected void handleToggleBufferWrapTextRadioButton(ActionEvent event) {
    throw new UnsupportedOperationException("Not Implemented yet");
  }
}
