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
package com.jeremyr.multiclipboard.buffertableview.listeners;

import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

/**
 *
 * @author jeremyr
 */
public class DataTableRowSelectionListener<T extends BufferBase> implements ChangeListener<T> {
  private final TextArea bufferTextArea;

  public DataTableRowSelectionListener(TextArea bufferTextArea, SimpleStringProperty clipboardContents) {
    this.bufferTextArea = bufferTextArea;
  }

  @Override
  public void changed(ObservableValue<? extends T> observable, T oldValueProperty, T newValueProperty) {
    BufferBase oldValue = (BufferBase) oldValueProperty;
    BufferBase newValue = (BufferBase) newValueProperty;

    /*
     * If oldValue is null, we do nothing.
     * If oldValue is not null, and is the clipboard, we unbind the buffer TextArea.
     *    If oldValue is not null, and is not the clipboard, we write the contents of the buffer TextArea back
     * to oldValue's data.
     */
    if (oldValue != null) {
      this.bufferTextArea.textProperty().unbindBidirectional(oldValue.getDataProperty());
    }

    /*
     *   If newValue is null, set buffer TextArea contents to an empty string, and
     * make bufferTextArea non-editable.
     *   If newValue is not null, and is the clipboard, make buffer TextArea non-editable,
     * and bind the bufferTextArea textProperty to the clipboardContents SimpleStringProperty.
     *   If newValue is not null, and is not the clipboard, make bufferTextArea editable, and
     * set the bufferTextArea contents to the newValue's data.
     */
    if (newValue == null) {
      this.bufferTextArea.textProperty().set("");
      this.bufferTextArea.setEditable(false);
    } else {
      this.bufferTextArea.setEditable(true);
      this.bufferTextArea.textProperty().bindBidirectional(newValue.getDataProperty());
    }
  }
}
