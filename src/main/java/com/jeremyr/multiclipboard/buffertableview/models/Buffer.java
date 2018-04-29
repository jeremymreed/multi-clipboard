/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jeremyr.multiclipboard.buffertableview.models;

import com.jeremyr.multiclipboard.timemanager.TimeManager;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jeremyr
 */
public class Buffer implements BufferBase {

  private int index;
  private final SimpleStringProperty name;
  private final SimpleStringProperty createDate;
  private SimpleStringProperty data;
  private final TimeManager timeManager;

  public Buffer(int index, String name) {
    this.index = index;
    this.timeManager = new TimeManager();
    this.name = new SimpleStringProperty(this.index + ": " + name);
    this.createDate = new SimpleStringProperty(this.timeManager.getFormattedDate("US/Eastern"));

    this.data = new SimpleStringProperty(this.index + ": Buffer: Hello World!");
  }

  @Override
  public int getIndex() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void setIndex(int index) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void setName(String name) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getCreateDate() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public SimpleStringProperty getDataProperty() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public String getData() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void setData(String data) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void emptyBuffer() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
