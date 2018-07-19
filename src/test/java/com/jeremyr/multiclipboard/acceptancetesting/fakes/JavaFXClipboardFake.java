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
package com.jeremyr.multiclipboard.acceptancetesting.fakes;

import com.jeremyr.multiclipboard.wrappers.ClipboardWrapperInterface;

/**
 *
 * This class is a fake JavaFXClipboardWrapper, it has identical behavior, only we don't actually
 * read/write anything to the system clipboard.
 * 
 * The clipboard is a simple String object.
 * 
 * @author jeremyr
 */
public class JavaFXClipboardFake implements ClipboardWrapperInterface {

  private String clipboard;

  public JavaFXClipboardFake() {
    this.clipboard = "";
  }

  @Override
  public String readClipboard() {
    if (this.clipboard != null) {
      return this.clipboard;
    } else {
      return "";
    }
  }

  @Override
  public void writeClipboard(String data) {
    if (data != null) {
      this.clipboard = data;
    } else {
      this.clipboard = "";
    }
  }

  @Override
  public void emptyClipboard() {
    this.clipboard = null;
  }

  @Override
  public Boolean isClipboardEmpty() {
    return this.clipboard == null;
  }
}
