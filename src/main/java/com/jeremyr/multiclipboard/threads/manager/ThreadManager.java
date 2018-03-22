/*
 * The MIT License
 *
 * Copyright 2018 jeremyr.
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
package com.jeremyr.multiclipboard.threads.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.property.SimpleStringProperty;
import com.jeremyr.multiclipboard.threads.clipboardmonitor.ClipboardMonitorTask;

/**
 * Manages our threads.  Starts and stops them.  Shuts down and cleans up.
 * Holds our ExecutorService object, and wraps around it.
 *
 * @author jeremyr
 */
public class ThreadManager {

  final private Logger logger;

  /** Interface to the Thread Pool */
  final private ExecutorService executorService;

  /** Get the Thread Pool */
  public ThreadManager() {
    this.logger = LoggerFactory.getLogger("MultiClipboard");
    this.executorService = Executors.newCachedThreadPool();
  }

  /**
   * Starts the ClipboardMonitorTask thread
   *
   * @param text The Observable Value bound to the Clipboard TextArea.
   */
  public void spawnThreads(SimpleStringProperty text) {
    this.executorService.submit(new ClipboardMonitorTask( text ) );
  }

  /**
   * Shuts down the Thread Pool, and forces threads to shutdown.
   * Called when the Application's close method is called.
   */
  public void stopThreads( ) {
    this.logger.info("ThreadManager: Stopping threads");
    this.executorService.shutdown( );
    this.executorService.shutdownNow( );
  }
}
