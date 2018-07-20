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
import com.jeremyr.multiclipboard.buffertableview.models.Buffer;
import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import com.jeremyr.multiclipboard.clipboardinterface.ClipboardInterfaceController;
import com.jeremyr.multiclipboard.threads.manager.ThreadManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.junit.Assert;
import org.loadui.testfx.GuiTest;
import org.slf4j.LoggerFactory;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author jeremyr
 */
public class BufferControlsDisabledAcceptanceTest extends ApplicationTest {

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

  /*
   * This acceptance test verifies that the buffer's controls are disabled when there are no buffers
   * in the Buffer TableView.
   *  - Clear Buffer Button.
   *  - Read From Clipboard Button.
   *  - Write To Clipboard Button.
   *  - Buffer TextArea.  Should not be editable.
  */
  @Test
  public void testBufferControlDisabledAcceptanceTest() {
    Button clearBufferButton = (Button) GuiTest.find("#clearBufferButton");
    Button readFromClipboardButton = (Button) GuiTest.find("#readFromClipboardButton");
    Button writeToClipboardButton = (Button) GuiTest.find("#writeToClipboardButton");
    TextArea bufferTextArea = (TextArea) GuiTest.find("#bufferTextArea");

    TableView<BufferBase> dataTable = (TableView<BufferBase>) GuiTest.find("#dataTable");
    ObservableList<BufferBase> testData = FXCollections.observableArrayList();

    dataTable.setItems(testData);

    // Wait for user interaction events to complete before running assertions.
    WaitForAsyncUtils.waitForFxEvents();

    Assert.assertEquals("The buffer TableView was not empty!", 0, dataTable.getItems().size());
    Assert.assertTrue("The Clear Buffer Button was not disabled!", clearBufferButton.isDisabled());
    Assert.assertTrue("The Read From Clipboard Button was not disabled!", readFromClipboardButton.isDisabled());
    Assert.assertTrue("The Write To Clipboard Button was not disabled!", writeToClipboardButton.isDisabled());
    Assert.assertFalse("The Buffer TextArea was not disabled!", bufferTextArea.isEditable());
  }
}
