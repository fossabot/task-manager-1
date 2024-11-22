package com.github.joaoh4547.taskmanager.components;

public interface PageableTable<T> extends Table<T> {

    int getPage();

    int getPageSize();

    int getTotalPages();


}
