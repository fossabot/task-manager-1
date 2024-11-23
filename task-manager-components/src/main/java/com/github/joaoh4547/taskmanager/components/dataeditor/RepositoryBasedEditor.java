package com.github.joaoh4547.taskmanager.components.dataeditor;

import com.github.joaoh4547.taskmanager.data.Repository;

public interface RepositoryBasedEditor<T>
  extends DataEditor<T> {

  Repository<T, ?> getRepository();

  void validate(T entity);

  void validateDelete(T entity);

  void validateInsert(T entity);

  void validateUpdate(T entity);

  void handleEditObject(T object);
}
