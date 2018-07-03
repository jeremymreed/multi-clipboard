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
package com.jeremyr.multiclipboard.buffertableview.models;

import com.jeremyr.multiclipboard.timemanager.TimeManager;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jeremyr
 */
public class Buffer implements BufferBase {

  private int index;
  private final boolean isClipboard;
  private final SimpleStringProperty name;
  private final SimpleStringProperty createDate;
  private SimpleStringProperty data;
  private final TimeManager timeManager;

  public Buffer(int index, String name) {
    this.index = index;
    this.isClipboard = false;
    this.timeManager = new TimeManager();
    this.name = new SimpleStringProperty(this.index + ": " + name);
    this.createDate = new SimpleStringProperty(this.timeManager.getFormattedDateForLocalZone());

    this.data = new SimpleStringProperty("");
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public String getName() {
    return this.name.get();
  }

  @Override
  public void setName(String name) {
    this.name.set(name);
  }

  @Override
  public String getCreateDate() {
    return this.createDate.get();
  }

  @Override
  public SimpleStringProperty getDataProperty() {
    return this.data;
  }

  @Override
  public String getData() {
    return this.data.get();
  }

  @Override
  public void setData(String data) {
    this.data.set(data);
  }

  @Override
  public void empty() {
    // nop.
  }

  @Override
  public String toString() {
    return "Buffer: " + this.index + ": " + this.name.get() + ", " + this.createDate.get() + ", " + this.data.get();
  }
}
