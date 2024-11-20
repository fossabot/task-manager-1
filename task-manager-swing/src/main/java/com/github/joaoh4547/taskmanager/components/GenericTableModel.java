package com.github.joaoh4547.taskmanager.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class GenericTableModel<T>
  extends AbstractTableModel {

  private final Collection<T> itens;

  protected GenericTableModel() {
    this.itens = new ArrayList<>();
  }

  public void addItem(T item) {
    itens.add(item);
    fireTableRowsInserted(itens.size() - 1, itens.size() - 1);
  }

  public void removeItem(int index) {
    if (index >= 0 && index < itens.size()) {
      getItemsAsList().remove(index);
      fireTableRowsDeleted(index, index);
    }
  }

  public T getItem(int index) {
    return getItemsAsList().get(index);
  }

  public List<T> getItemsAsList() {
    return new ArrayList<>(itens);
  }

  public int getRowCount() {
    return itens.size();
  }

  public Collection<T> getItens() {
    return itens;
  }
}
