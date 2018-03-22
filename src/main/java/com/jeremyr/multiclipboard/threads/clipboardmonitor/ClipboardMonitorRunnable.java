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
package com.jeremyr.multiclipboard.threads.clipboardmonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.Clipboard;

/**
 * This runnable is responsible for checking the JavaFX clipboard for changes, and
 * updating the SimpleStringProperty observable value with the value of the clipboard contents.
 *
 * @author jeremyr
 */
public class ClipboardMonitorRunnable implements Runnable {

  /** Reference to the application's logger object */
  private final Logger logger;

  /**
   * Reference to the Observable value bound to the clipboard TextArea.
   * This is set volatile.
   *
   * TODO: Find out if this really needs to be made volatile, since this Runnable is
   * scheduled for execution by Platform.runLater, which runs code on the JavaFX Application Thread.
   * IF all reads and writes to this variable occur on the JavaFX Application Thread, seems like
   * this should not be set volatile.  Need to get advice.
   */
  private volatile SimpleStringProperty text;

  /** Reference to the JavaFX Clipboard. */
  private final Clipboard clipboard;

  /** Store the old clipboard contents value. */
  private String oldData;

  /**
   * Controller with dependencies passed in.
   * We are passing in the JavaFX System Clipboard since that can be done outside
   * of the JavaFX Application Thread.  Actually using it requires us to be on the
   * JavaFX Application Thread.
   *
   * TODO: Consider moving the line of code getting theJavaFX System Clipboard
   * into this constructor, as it only runs once.
   *
   * Set up data members.
   *
   * @param clipboard The JavaFX System Clipboard.
   * @param text The SimpleStringProperty Observable Value bound to the clipboard TextArea.
   */
  public ClipboardMonitorRunnable(Clipboard clipboard, SimpleStringProperty text) {
    this.logger = LoggerFactory.getLogger("MultiClipboard");
    this.clipboard = clipboard;
    this.text = text;
    this.oldData = "";
  }

  /**
   * Code to be run on the JavaFX Application Thread.
   * Monitors the JavaFX System Clipboard for changes.
   * Updates the SimpleStringProperty Observable Value if the JavaFX System Clipboard
   * contents change.
   */
  @Override
  public void run() {
    try {
      if (this.clipboard.hasString()) {
        String data = this.clipboard.getString();

        if (data == null) {
          this.logger.error("data is null!");
        }

        if (data != null && !this.oldData.equals(data)) {
          this.oldData = data;
          this.text.set(data);
        }
      } else {
        this.text.set("");
      }
    } catch (NullPointerException nullPointerException) {
      if (this.clipboard == null) {
        this.logger.error("clipboard is null", nullPointerException);
      }

      if (this.oldData == null) {
        this.logger.error("this.oldData is null!", nullPointerException);
        this.oldData = "";
      }

      if (this.text == null) {
        this.logger.error("this.text is null!", nullPointerException);
      }
    }
  }
}
