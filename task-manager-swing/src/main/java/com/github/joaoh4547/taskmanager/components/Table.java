package com.github.joaoh4547.taskmanager.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import javax.swing.event.TableModelEvent;

import com.github.joaoh4547.taskmanager.utils.Objects;

public class Table<T>
  extends JPanel {

  private final JTable table;
  private GenericTableModel model;

  private Collection<T> tableData = new ArrayList<T>();

  private final Collection<TableColumn> columns = new ArrayList<>();

  public Table() {

    model = new GenericTableModel();
    createColumns();
    table = new JTable(model);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

  }

  protected void createColumns() {

  }

  public void addColumn(String property) {
    columns.add(new TableColumn(property, property));
  }

  public void addColumn(String property, String name) {
    columns.add(new TableColumn(property, name));
  }

  public void setTableData(Collection<T> tableData) {
    this.tableData = tableData;
    refreshData();
  }

  private String[] getColumns() {
    return columns.stream().map(TableColumn::name).toArray(String[]::new);
  }

  private Object[][] toObjectArray() {
    Object[][] data = new Object[tableData.size()][columns.size()];
    int i = 0;
    for (T rowData : tableData) {
      int j = 0;
      for (TableColumn column : columns) {
        data[i][j] = getPropertyFromRowData(rowData, column.property());
        j++;
      }
      i++;
    }
    return data;
  }

  public void refreshData() {
    model.setDataVector(toObjectArray(), getColumns());
    model.fireTableDataChanged();
    table.setModel(model);
    table.tableChanged(new TableModelEvent(model));
  }

  private Object getPropertyFromRowData(T rowData, String property) {
    return Objects.getValue(rowData, property);
  }

}
