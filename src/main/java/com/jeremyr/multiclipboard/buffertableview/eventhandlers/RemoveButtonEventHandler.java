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
package com.jeremyr.multiclipboard.buffertableview.eventhandlers;

import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author jeremyr
 */
public class RemoveButtonEventHandler implements EventHandler<ActionEvent> {

  private final ObservableList<BufferBase> bufferList;
  private final int index;

  public RemoveButtonEventHandler(int index, ObservableList<BufferBase> bufferList) {
    this.bufferList = bufferList;
    this.index = index;
  }

  @Override
  public void handle(ActionEvent event) {
    for (int i = 0; i < this.bufferList.size(); i += 1) {
      if (this.bufferList.get(i).getIndex() == this.index) {
        this.bufferList.remove(i);
        return;
      }
    }

    throw new IllegalStateException("Did not find the buffer to remove!  Something went wrong!");
  }
}
