/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffertableview.listeners;

import com.jeremyr.multiclipboard.buffertableview.models.BufferBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

/**
 *
 * @author jeremyr
 */
public class DataTableRowSelectionListener<T extends BufferBase> implements ChangeListener<T> {
  private final TextArea bufferTextArea;
  private final SimpleStringProperty clipboardContents;

  public DataTableRowSelectionListener(TextArea bufferTextArea, SimpleStringProperty clipboardContents) {
    this.bufferTextArea = bufferTextArea;
    this.clipboardContents = clipboardContents;
  }

  @Override
  public void changed(ObservableValue<? extends T> observable, T oldValueProperty, T newValueProperty) {
    BufferBase oldValue = (BufferBase) oldValueProperty;
    BufferBase newValue = (BufferBase) newValueProperty;

    // If oldValue is the clipboard, we don't need to write anything back.
    // But if oldvalue is NOT the clipboard, we do need to write data back, in case it got changed.
    if (oldValue != null && !oldValue.isClipboard()) {
      oldValue.setData(this.bufferTextArea.textProperty().get());
    }

    // We are assuming that when newValue == null, there is no currently selected buffer.
    if (newValue == null) {
      this.bufferTextArea.textProperty().set("");
      this.bufferTextArea.setEditable(false);
    } else {
      if (newValue.isClipboard()) {
        this.bufferTextArea.setEditable(false);
        this.bufferTextArea.textProperty().bind(this.clipboardContents);
      } else {
        this.bufferTextArea.setEditable(true);
        this.bufferTextArea.textProperty().unbind();
        this.bufferTextArea.textProperty().set(newValue.getData());
      }
    }
  }
}
