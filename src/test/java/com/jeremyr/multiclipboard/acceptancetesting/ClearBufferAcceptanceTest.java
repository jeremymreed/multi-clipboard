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

import com.jeremyr.multiclipboard.MultiClipboard;
import com.jeremyr.multiclipboard.buffertableview.models.Buffer;
import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import com.jeremyr.multiclipboard.testutils.TableViewTestUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author jeremyr
 */
public class ClearBufferAcceptanceTest extends ApplicationTest {
  @Override
  public void start(Stage stage) throws Exception {
    Parent mainNode = FXMLLoader.load(MultiClipboard.class.getResource("/fxml/ClipboardInterfaceLayout.fxml"));
    stage.setScene(new Scene(mainNode));
    stage.show();
    stage.toFront();
  }

  @After
  public void tearDown() throws Exception {
    FxToolkit.hideStage();
    release(new KeyCode[]{});
    release(new MouseButton[]{});
  }

  @Test
  public void TestClearBufferAcceptanceTest() {
    // Set up some stuff. (Setup stuff works properly.)
    TableView<BufferBase> dataTable = (TableView<BufferBase>) GuiTest.find("#dataTable");
    ObservableList<BufferBase> testData = FXCollections.observableArrayList();
    Buffer test = new Buffer(0, "Test Buffer");
    Buffer test2 = new Buffer(1, "Another Buffer");
    test.setData("Foo Bar!");
    test2.setData("This is not an empty String!");
    testData.add(test);
    testData.add(test2);

    dataTable.setItems(testData);

    // Wait for user interaction events to complete before running assertions.
    WaitForAsyncUtils.waitForFxEvents();

    clickOn(TableViewTestUtils.getNodeAt(1, 1, dataTable));
    clickOn("#clearBufferButton");

    clickOn(TableViewTestUtils.getNodeAt(0, 1, dataTable));
    clickOn("#clearBufferButton");

    Assert.assertEquals("The buffer's contents were not cleared properly!", "", test2.getData());
    Assert.assertEquals("The buffer's contents were not cleared properly!", "", test.getData());
  }
}
