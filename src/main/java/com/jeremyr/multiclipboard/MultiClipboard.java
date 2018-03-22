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
package com.jeremyr.multiclipboard;

import ch.qos.logback.classic.LoggerContext;
import com.jeremyr.multiclipboard.clipboarduserinterface.ClipboardUserInterfaceController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jeremyr.multiclipboard.threads.manager.ThreadManager;

/**
 * Main class entry point for the MultiClipboard JavaFX application.
 *
 * @author Jeremy M. Reed
 */
public class MultiClipboard extends Application {

  private ClipboardUserInterfaceController clipboardInterfaceController;
  private ThreadManager threadManager;

  /**
   * Called by JavaFX when the main stage is started.
   * Starts the Thread Manager.
   *
   * @param stage
   * @throws Exception
   */
  @Override
  public void start(Stage stage) throws Exception {
    System.out.println( "Started stage." );

    this.threadManager = new ThreadManager();

    Platform.setImplicitExit(true);
    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ClipboardUserInterfaceLayout.fxml"));

    Parent root = (Parent)fxmlLoader.load();

    this.clipboardInterfaceController = (ClipboardUserInterfaceController) fxmlLoader.getController();

    this.threadManager.spawnThreads(this.clipboardInterfaceController.getText());

    Scene scene = new Scene(root);

    stage.setTitle("Clipboard Monitor");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Called by JavaFX when the main stage is closed.
   * Does all required clean up.
   *   - Tells the Thread Manager to shutdown threads.
   *   - Tells the logger to stop logging and clean up.
   */
  @Override
  public void stop() {
    System.out.println("stop() called");
    this.threadManager.stopThreads();

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.stop();
  }

  /**
   * Creates the logger, then launches the JavaFX app.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      // TODO: Change the name of the logger to something relevant.
      Logger logger = LoggerFactory.getLogger("Hello World");
      logger.info("Hello World");
      launch(args);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    
  }
}
