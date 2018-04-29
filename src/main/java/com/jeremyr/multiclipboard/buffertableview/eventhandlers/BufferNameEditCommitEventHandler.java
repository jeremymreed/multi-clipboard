/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffertableview.eventhandlers;

import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

/**
 *
 * @author jeremyr
 */
public class BufferNameEditCommitEventHandler implements EventHandler<CellEditEvent<BufferBase, String>> {

  @Override
  public void handle(CellEditEvent<BufferBase, String> event) {
    BufferBase item = event.getTableView().getItems().get(event.getTablePosition().getRow());
    item.setName(event.getNewValue());
  }
}
