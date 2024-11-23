package com.github.joaoh4547.taskmanager.components.dataeditor;

import java.util.Collection;
import java.util.function.Predicate;

import com.github.joaoh4547.taskmanager.components.ui.table.Table;

public interface DataEditor<T> {

  void edit(T obj);

  void add(T obj);

  void remove(T obj);

  void remove(Predicate<T> predicate);

  void view();

  Collection<T> getItems();

  Table<T> createTable();

  T getSelected();

  Collection<T> getSelectedItems();

}
