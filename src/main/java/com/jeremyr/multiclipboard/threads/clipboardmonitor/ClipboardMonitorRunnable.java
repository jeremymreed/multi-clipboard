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

import java.util.concurrent.atomic.AtomicBoolean;
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
  private volatile SimpleStringProperty clipboardContents;

  /** This AtomicBoolean determines if we should be monitoring the clipboard for changes
   * or clearing it every time the Runnable's run() method is called.
   */
  private final AtomicBoolean shouldNukeClipboard;

  /** Reference to the JavaFX Clipboard. */
  private final Clipboard clipboard;

  /**
   * Controller with dependencies passed in.
   * Set up data members.
   *
   * @param text The SimpleStringProperty Observable Value bound to the clipboard TextArea.
   * @param shouldNukeClipboard The AtomicBoolean that controls the Clipboard Monitor's
   * nuke clipboard feature.
   */
  public ClipboardMonitorRunnable(SimpleStringProperty text, AtomicBoolean shouldNukeClipboard) {
    this.logger = LoggerFactory.getLogger("MultiClipboard");
    this.clipboard = Clipboard.getSystemClipboard();
    this.clipboardContents = text;
    this.shouldNukeClipboard = shouldNukeClipboard;
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
      if (this.shouldNukeClipboard.get()) {
        clipboard.setContent(null);
        this.clipboardContents.set("");
      } else {
        if (this.clipboard.hasString() && this.clipboard.getString() != null) {
          String data = this.clipboard.getString();

          if (data == null) {
            this.logger.error("data is null!");
          }

          if (data != null) {
            this.clipboardContents.set(data);
          } else {
            this.clipboardContents.set("");
          }
        } else {
          this.clipboardContents.set("");
        }
      }
    } catch (NullPointerException nullPointerException) {
      if (this.clipboard == null) {
        this.logger.error("clipboard is null", nullPointerException);
      }

      if (this.clipboardContents == null) {
        this.logger.error("this.text is null!", nullPointerException);
      }
    }
  }
}
