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
  
  @FXML
  private Text actiontarget;
  @FXML
  private TextArea buffer;

  @FXML
  protected void handleWriteButtonAction(ActionEvent event) {
    buffer.setText("Write button pressed");
    actiontarget.setText("Write button pressed");
  }
  
  @FXML
  protected void handleReadButtonAction(ActionEvent event) {
    buffer.setText("Read button pressed");
    actiontarget.setText("Read button pressed");
  }
}
