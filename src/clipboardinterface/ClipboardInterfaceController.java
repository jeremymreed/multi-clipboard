/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clipboardinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 *
 * @author jeremyr
 */
public class ClipboardInterfaceController {
  
  final private ClipboardInterface clipboardInterface;

  @FXML
  private Text actiontarget;
  @FXML
  private TextArea buffer;

  public ClipboardInterfaceController() {
    this.clipboardInterface = new ClipboardInterface();
  }

  @FXML
  protected void handleWriteButtonAction(ActionEvent event) {
    this.clipboardInterface.writeClipboard(buffer.getText());
    actiontarget.setText("Write button pressed");
  }
  
  @FXML
  protected void handleReadButtonAction(ActionEvent event) {
    String data = this.clipboardInterface.readClipboard();
    buffer.setText(data);
    actiontarget.setText("Read button pressed");
  }
}
