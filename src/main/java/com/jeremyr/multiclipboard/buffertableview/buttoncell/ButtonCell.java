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
package com.jeremyr.multiclipboard.buffertableview.buttoncell;

import com.jeremyr.multiclipboard.buffertableview.eventhandlers.RemoveButtonEventHandler;
import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

/**
 *
 * @author jeremyr
 */
public class ButtonCell extends TableCell<BufferBase, Boolean> {

  private final TableView<BufferBase> dataTable;

  private final Button cellButton = new Button("Remove");

  public ButtonCell(final TableView<BufferBase> dataTable) {
    this.dataTable = dataTable;
  }

  @Override
  protected void updateItem(Boolean item, boolean empty) {
    super.updateItem(item, empty);

    if(empty || item == null ) {
      setGraphic(null);
    } else {
      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
      setGraphic(this.cellButton);

      ObservableList<BufferBase> bufferList = this.dataTable.getItems();
      BufferBase buffer = bufferList.get(getIndex());
      this.cellButton.setId("button-warning");
      this.cellButton.setOnAction(new RemoveButtonEventHandler(buffer.getIndex(), bufferList));
    }
  }
}
