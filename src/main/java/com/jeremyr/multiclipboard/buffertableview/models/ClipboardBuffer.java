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
public class ClipboardBuffer implements BufferBase {

  private int index;
  private final SimpleStringProperty name;
  private final SimpleStringProperty createDate;
  private SimpleStringProperty data;
  private final TimeManager timeManager;

  public ClipboardBuffer(int index, String name) {
    this.index = index;
    this.timeManager = new TimeManager();
    this.name = new SimpleStringProperty(this.index + ": " + name);
    this.createDate = new SimpleStringProperty(this.timeManager.getFormattedDate("US/Eastern"));

    this.data = new SimpleStringProperty(this.index + ": ClipboardBuffer: Hello World!");
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void setIndex(int index) {
    this.index = index;
  }

  @Override
  public String getName() {
    return this.name.get();
  }

  @Override
  public void setName(String name) {
    this.name.set(name);
  }

  @Override
  public String getCreateDate() {
    return this.createDate.get();
  }

  @Override
  public SimpleStringProperty getDataProperty() {
    return this.data;
  }

  @Override
  public String getData() {
    return this.data.get();
  }

  @Override
  public void setData(String data) {
    this.data.set(data);
  }

  @Override
  public void empty() {
    this.data.set("");
  }

  @Override
  public String toString() {
    return "ClipboardBuffer: " + this.index + ": " + this.name.get() + ", " + this.createDate.get() + ", " + this.data.get();
  }
}
