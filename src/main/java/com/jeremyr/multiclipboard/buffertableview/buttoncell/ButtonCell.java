/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
