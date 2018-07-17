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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author jeremyr
 */
public class EditBufferNameAcceptanceTest extends ApplicationTest {
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

  // TODO add test methods here.
  // The methods must be annotated with annotation @Test. For example:
  //
  // @Test
  // public void hello() {}
  @Test
  public void testEditBufferName() {
    // Set up some stuff. (Setup stuff works properly.)
    TableView<BufferBase> dataTable = (TableView<BufferBase>) GuiTest.find("#dataTable");
    ObservableList<BufferBase> testData = FXCollections.observableArrayList();
    Buffer test = new Buffer(0, "Test Buffer");
    Buffer test2 = new Buffer(1, "Another Buffer");
    testData.add(test);
    testData.add(test2);

    dataTable.setItems(testData);

    // Wait for user interaction events to complete before running assertions.
    WaitForAsyncUtils.waitForFxEvents();

    clickOn((TextFieldTableCell) TableViewTestUtils.getNodeAt(1, 0, dataTable));
    clickOn((TextFieldTableCell) TableViewTestUtils.getNodeAt(1, 0, dataTable));
    press(KeyCode.BACK_SPACE);
    write("Foo!");
    press(KeyCode.ENTER);

    Assert.assertEquals("Did not edit the buffer name correctly!", "Foo!", test2.getName());
  }
}
