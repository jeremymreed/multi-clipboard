/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        if (!this.bufferList.get(i).isClipboard()) {
          this.bufferList.remove(i);
        }

        return;
      }
    }

    throw new IllegalStateException("Did not find the buffer to remove!  Something went wrong!");
  }
}
