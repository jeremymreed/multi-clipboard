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

    /*
     * If oldValue is null, we do nothing.
     * If oldValue is not null, and is the clipboard, we unbind the buffer TextArea.
     *    If oldValue is not null, and is not the clipboard, we write the contents of the buffer TextArea back
     * to oldValue's data.
     */
    if (oldValue != null) {
      this.bufferTextArea.textProperty().unbindBidirectional(oldValue.getDataProperty());
    }

    /*
     *   If newValue is null, set buffer TextArea contents to an empty string, and
     * make bufferTextArea non-editable.
     *   If newValue is not null, and is the clipboard, make buffer TextArea non-editable,
     * and bind the bufferTextArea textProperty to the clipboardContents SimpleStringProperty.
     *   If newValue is not null, and is not the clipboard, make bufferTextArea editable, and
     * set the bufferTextArea contents to the newValue's data.
     */
    if (newValue == null) {
      this.bufferTextArea.textProperty().set("");
      this.bufferTextArea.setEditable(false);
    } else {
      this.bufferTextArea.setEditable(true);
      this.bufferTextArea.textProperty().bindBidirectional(newValue.getDataProperty());
    }
  }
}
