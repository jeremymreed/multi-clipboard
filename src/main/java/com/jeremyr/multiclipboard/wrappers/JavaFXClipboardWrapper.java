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
package com.jeremyr.multiclipboard.wrappers;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;

/**
 *
 * @author jeremyr
 */
public class JavaFXClipboardWrapper {
  public String readClipboard() {
    Clipboard clipboard = Clipboard.getSystemClipboard();

    if (clipboard.hasString()) {
      return clipboard.getString();
    } else {
      return "";
    }
  }

  public void writeClipboard(String data) {
    Clipboard clipboard = Clipboard.getSystemClipboard();
    ClipboardContent contents = new ClipboardContent();

    contents.put(DataFormat.PLAIN_TEXT, data);
    clipboard.setContent(contents);
  }

  /***
   * Empties the clipboard of all data.
   * isClipboardEmpty() should return true after this method is executed.
   */
  public void emptyClipboard() {
    Clipboard clipboard = Clipboard.getSystemClipboard();

    clipboard.setContent(null);
  }

  /***
   * Tells us if there is *no* data of any kind on the system clipboard.
   * Not even an empty string.
   * @return Boolean true if the clipboard is empty, false otherwise.
   */
  public Boolean isClipboardEmpty() {
    Clipboard clipboard = Clipboard.getSystemClipboard();

    return clipboard.getContentTypes().isEmpty();
  }
}
