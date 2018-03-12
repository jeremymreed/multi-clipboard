/*
 * The MIT License
 *
 * Copyright 2018 Jeremy M. Reed
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
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.Task;
import javafx.scene.input.Clipboard;

/**
 *
 * @author jeremyr
 */
public class ClipboardMonitorTask extends Task {

  private final Logger logger;
  private volatile SimpleStringProperty text;
  private final ClipboardMonitorRunnable clipboardMonitorRunnable;

  public ClipboardMonitorTask( SimpleStringProperty text ) {
    this.logger = LoggerFactory.getLogger("Hello World");
    this.text = text;
    this.clipboardMonitorRunnable = new ClipboardMonitorRunnable(Clipboard.getSystemClipboard(), this.text );
  }

  @Override
  protected Object call() throws Exception {
    System.out.println( "Started ClipboardMonitorTask!" );

    try {
      while(true) {
        Platform.runLater(this.clipboardMonitorRunnable);

        Thread.sleep(100);
      }

    } catch (InterruptedException interruptedException) {
      System.out.println("ClipboardMonitorTask: Thread was interrupted, cleaning up!");
    } catch ( Exception exception ) {
      this.logger.error("Caught Exception: ", exception);
    }

    return null;
  }
}
