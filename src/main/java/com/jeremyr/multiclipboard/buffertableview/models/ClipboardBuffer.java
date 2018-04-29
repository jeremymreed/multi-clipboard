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
  private final boolean isClipboard;
  private final SimpleStringProperty name;
  private final SimpleStringProperty createDate;
  private final SimpleStringProperty clipboardContents;
  private final TimeManager timeManager;

  public ClipboardBuffer(int index, String name, SimpleStringProperty clipboardContents) {
    this.index = index;
    this.isClipboard = true;
    this.timeManager = new TimeManager();
    this.name = new SimpleStringProperty(this.index + ": " + name);
    this.createDate = new SimpleStringProperty(this.timeManager.getFormattedDate("US/Eastern"));

    this.clipboardContents = clipboardContents;
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
    return this.clipboardContents;
  }

  @Override
  public String getData() {
    return this.clipboardContents.get();
  }

  @Override
  public void setData(String data) {
    this.clipboardContents.set(data);
  }

  @Override
  public boolean isClipboard() {
    return this.isClipboard;
  }

  @Override
  public void empty() {
    this.clipboardContents.set("");
  }

  @Override
  public String toString() {
    return "ClipboardBuffer: " + this.index + ": " + this.name.get() + ", " + this.createDate.get() + ", " + this.clipboardContents.get();
  }
}
