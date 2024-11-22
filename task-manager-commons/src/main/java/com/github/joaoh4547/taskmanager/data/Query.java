package com.github.joaoh4547.taskmanager.data;

@FunctionalInterface
public interface Query<T> {

  Boolean valid(T obj);
}
