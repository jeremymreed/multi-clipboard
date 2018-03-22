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
 * This Task is responsible for calling Platform.runLater with our Runnable
 * every 100 milliseconds.  TODO: Perhaps read this from a config file.
 *
 * We have this in a separate Task, because we really don't want to call
 * Thread.sleep on the JavaFX Application Thread.
 *
 * @author jeremyr
 */
public class ClipboardMonitorTask extends Task {

  /** Reference to the Application Logger object */
  private final Logger logger;

  /** Refernce to the ClipboardMonitorRunnable to be run via Platform.runLater.*/
  private final ClipboardMonitorRunnable clipboardMonitorRunnable;

  /**
   * Constructor with dependencies passed in.
   *
   * Set up data members.
   *
   * @param text
   */
  public ClipboardMonitorTask( SimpleStringProperty text ) {
    this.logger = LoggerFactory.getLogger("MultiClipboard");
    this.clipboardMonitorRunnable = new ClipboardMonitorRunnable(Clipboard.getSystemClipboard(), text );
  }

  /**
   * Passes ClipboardMonitorRunnable to Platform.runLater every 100 milliseconds.
   * Handle interrupts.
   *
   * @return Nothing. TODO: See if there's a better class in javafx.concurrent for this job.
   * @throws Exception
   */
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
