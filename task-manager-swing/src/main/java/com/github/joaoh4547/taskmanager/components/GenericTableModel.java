package com.github.joaoh4547.taskmanager.components;

import javax.swing.table.DefaultTableModel;

public class GenericTableModel
  extends DefaultTableModel {

  public GenericTableModel() {
    super();
  }

  public GenericTableModel(Object[][] objectArray, String[] columns) {
    super(objectArray, columns);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
