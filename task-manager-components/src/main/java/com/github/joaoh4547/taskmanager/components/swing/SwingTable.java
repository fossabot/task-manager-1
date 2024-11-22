package com.github.joaoh4547.taskmanager.components.swing;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.*;
import javax.swing.event.TableModelEvent;

import org.apache.commons.lang3.StringUtils;

import com.github.joaoh4547.taskmanager.components.Table;
import com.github.joaoh4547.taskmanager.components.TableColumn;
import com.github.joaoh4547.taskmanager.utils.Objects;

public abstract class SwingTable<T>
  extends JPanel
  implements Table<T> {

  private final JTable table;
  private final GenericTableModel model;

  private Collection<T> tableData = new ArrayList<T>();

  private final Collection<TableColumn> columns = new ArrayList<>();

  public SwingTable() {

    model = new GenericTableModel();
    createColumns();
    table = new JTable(model);
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    setLayout(new BorderLayout());
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

  }

  protected abstract void createColumns();

  @Override
  public Collection<T> getItems() {
    return tableData;
  }

  public TableColumn addColumn(String property) {
    var column = new TableColumn(property, property);
    columns.add(column);
    return column;
  }

  public TableColumn addColumn(String property, String name) {
    var column = new TableColumn(property, name);
    columns.add(column);
    return column;
  }

  @Override
  public void removeColumn(String columnName) {
    columns.removeIf(c -> StringUtils.equals(c.title(), columnName));
  }

  @Override
  public void removeColumnByProperty(String property) {
    columns.removeIf(c -> StringUtils.equals(c.property(), property));
  }

  public void setTableData(Collection<T> tableData) {
    this.tableData = tableData;
    refreshData();
  }

  private String[] getColumns() {
    return columns.stream().map(TableColumn::title).toArray(String[]::new);
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
