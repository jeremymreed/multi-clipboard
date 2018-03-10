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
 *
 * @author jeremyr
 */
public class ClipboardMonitorRunnable implements Runnable {

  Logger logger;
  private volatile SimpleStringProperty text;
  private String oldData;

  public ClipboardMonitorRunnable(SimpleStringProperty text) {
    this.logger = LoggerFactory.getLogger("Hello World");
    this.text = text;
    this.oldData = "";
  }

  @Override
  public void run() {
    Clipboard clipboard = Clipboard.getSystemClipboard();
    try {
      if (clipboard.hasString()) {
        String data = clipboard.getString();

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
      if (clipboard == null) {
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
