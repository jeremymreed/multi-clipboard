/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffertableview.listeners;

import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

/**
 *
 * @author jeremyr
 */
public class DataTableRowSelectionListener<T extends BufferBase> implements ChangeListener<T> {
  private final TextArea bufferTextArea;

  public DataTableRowSelectionListener(TextArea bufferTextArea) {
    this.bufferTextArea = bufferTextArea;
  }

  @Override
  public void changed(ObservableValue<? extends T> observable, T oldValueProperty, T newValueProperty) {
    BufferBase oldValue = (BufferBase) oldValueProperty;
    BufferBase newValue = (BufferBase) newValueProperty;

    if (oldValue != null) {
      oldValue.setData(this.bufferTextArea.textProperty().get());
    }

    // We are assuming that when newValue == null, there is no currently selected buffer.
    if (newValue == null) {
      this.bufferTextArea.textProperty().set("");
      this.bufferTextArea.setEditable(false);
    } else {
      this.bufferTextArea.setEditable(true);
      this.bufferTextArea.textProperty().set(newValue.getData());
    }
  }
}
