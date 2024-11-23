package com.github.joaoh4547.taskmanager.components.ui.table;

public interface PageableTable<T> extends Table<T> {

    int getPage();

    int getPageSize();

    int getTotalPages();


}
