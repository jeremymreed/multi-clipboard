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
package multiclipboard;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import clipboardinterface.ClipboardInterfaceController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threads.manager.ThreadManager;

/**
 *
 * @author jeremyr
 */
public class MultiClipboard extends Application {

  private ClipboardInterfaceController clipboardInterfaceController;
  private ThreadManager threadManager;

  @Override
  public void start(Stage stage) throws Exception {
    System.out.println( "Started stage." );

    this.threadManager = new ThreadManager();

    Platform.setImplicitExit(true);

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/clipboardinterface/ClipboardInterfaceLayout.fxml"));

    Parent root = (Parent)fxmlLoader.load();

    this.clipboardInterfaceController = (ClipboardInterfaceController) fxmlLoader.getController();

    this.threadManager.spawnThreads(this.clipboardInterfaceController.getText());

    Scene scene = new Scene(root);

    stage.setTitle("Clipboard Monitor");
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() {
    System.out.println("stop() called");
    this.threadManager.stopThreads();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger("Hello World");
    logger.info("Hello World");

    // Print internal state
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    StatusPrinter.print(lc);

    launch(args);
  }
}
