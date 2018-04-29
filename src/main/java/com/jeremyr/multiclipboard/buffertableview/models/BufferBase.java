/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffertableview.models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jeremyr
 */
public interface BufferBase {
  
  public int getIndex();
  public void setIndex(int index);
  public String getName();
  public void setName(String name);
  public String getCreateDate();
  public SimpleStringProperty getDataProperty();
  public String getData();
  public void setData(String data);
  
  /**
   * Empties the buffer.
   * This is only relevant to the ClipboardBuffer.
   * For all other implementors, this is a nop.
   */
  public void empty();
}
