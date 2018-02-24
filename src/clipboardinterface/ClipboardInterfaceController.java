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
package clipboardinterface;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterfaceController {

  private SimpleStringProperty text;

  final private ClipboardInterface clipboardInterface;

  @FXML
  private Text actiontarget;
  @FXML
  private TextArea buffer;
  @FXML
  private TextArea clipboard;

  public ClipboardInterfaceController( ) {
    this.clipboardInterface = new ClipboardInterface();
  }

  public void initialize( ) {
    this.text = new SimpleStringProperty( );
    this.clipboard.textProperty().bind(this.text);
    this.text.set("");
  }

  public SimpleStringProperty getText() {
    return this.text;
  }

  @FXML
  protected void handleClearClipboardButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard("");
  }

  @FXML
  protected void handleWriteButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard(this.buffer.getText());
    actiontarget.setText("Write button pressed");
  }

  @FXML
  protected void handleReadButtonAction(ActionEvent event) {
    String data = this.clipboardInterface.readClipboard();
    this.buffer.setText(data);
    actiontarget.setText("Read button pressed");
  }
}
