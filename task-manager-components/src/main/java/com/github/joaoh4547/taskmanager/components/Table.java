package com.github.joaoh4547.taskmanager.components;

import java.util.Collection;

public interface Table<T> {

  Collection<T> getItems();

  TableColumn addColumn(String columnName);

  TableColumn addColumn(String columnName, String property);

  void removeColumn(String columnName);

  void removeColumnByProperty(String property);

}
