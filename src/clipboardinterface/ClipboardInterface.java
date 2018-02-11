/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboardinterface;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterface {
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
    
    contents.putString(data);
    contents.put(DataFormat.PLAIN_TEXT, data);
    clipboard.setContent(contents);
  }
}
