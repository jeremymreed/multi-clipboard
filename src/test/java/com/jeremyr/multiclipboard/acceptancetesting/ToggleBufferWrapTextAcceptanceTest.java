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
package com.jeremyr.multiclipboard.acceptancetesting;

import ch.qos.logback.classic.LoggerContext;
import com.jeremyr.multiclipboard.acceptancetesting.fakes.JavaFXClipboardFake;
import com.jeremyr.multiclipboard.clipboardinterface.ClipboardInterfaceController;
import com.jeremyr.multiclipboard.threads.manager.ThreadManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.After;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author jeremyr
 */
public class ToggleBufferWrapTextAcceptanceTest extends ApplicationTest {
  private ThreadManager threadManager;
  private JavaFXClipboardFake javaFXClipboardFake;

  @Override
  public void start(Stage stage) throws Exception {
    ClipboardInterfaceController clipboardInterfaceController;
    this.threadManager = new ThreadManager();

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ClipboardInterfaceLayout.fxml"));

    Parent root = (Parent)fxmlLoader.load();

    this.javaFXClipboardFake = new JavaFXClipboardFake();

    clipboardInterfaceController = (ClipboardInterfaceController) fxmlLoader.getController();
    clipboardInterfaceController.setJavaFXClipboardWrapper(this.javaFXClipboardFake);

    this.threadManager.spawnThreads(javaFXClipboardFake, clipboardInterfaceController.getClipboardContents(), clipboardInterfaceController.getShouldNukeClipboard());

    stage.setScene(new Scene(root));
    stage.show();
    stage.toFront();
  }

  @Override
  public void stop() {
    System.out.println("stop() called");
    this.threadManager.stopThreads();

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.stop();
  }

  @After
   public void tearDown() throws Exception {
     FxToolkit.hideStage();
     release(new KeyCode[]{});
     release(new MouseButton[]{});
   }

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
  @Test
  public void testToggleBufferWrapText() {
    RadioButton bufferTextAreaWrapTextRadioButton = (RadioButton) GuiTest.find("#bufferTextAreaWrapTextRadioButton");
    TextArea bufferTextArea = (TextArea) GuiTest.find("#bufferTextArea");
    boolean initialRadioButtonState = bufferTextAreaWrapTextRadioButton.isSelected();
    boolean initialTextAreaState = bufferTextArea.wrapTextProperty().get();

    clickOn("#bufferTextAreaWrapTextRadioButton");

    // Wait for user interaction events to complete before running assertions.
    WaitForAsyncUtils.waitForFxEvents();

    // Did the radio button state change properly?
    Assert.assertEquals("The Toggle Buffer Wrap Text radio button was not toggled properly!",
            !initialRadioButtonState,
            bufferTextAreaWrapTextRadioButton.isSelected());

    // Did the buffer Text Area wrap text property change properly?
    Assert.assertEquals("The Buffer Text Area Wrap Text property was not toggled properly!",
            !initialTextAreaState,
            bufferTextArea.wrapTextProperty().get());
  }
}
