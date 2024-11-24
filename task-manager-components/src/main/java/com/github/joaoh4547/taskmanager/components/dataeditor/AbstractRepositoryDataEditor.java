package com.github.joaoh4547.taskmanager.components.dataeditor;

import com.github.joaoh4547.taskmanager.data.validation.DataValidator;

public abstract class AbstractRepositoryDataEditor<T>
  implements RepositoryBasedEditor<T> {

  protected abstract DataValidator<T> getValidation();

  protected DataValidator<T> getDeleteValidation() {
    return getValidation();
  }

  protected DataValidator<T> getInsertValidation() {
    return getValidation();
  }

  protected DataValidator<T> getUpdateValidation() {
    return getValidation();
  }

  protected void refresh() {
  }

  @Override
  public void validate(T entity) {
    getValidation().validate(entity);
  }

  @Override
  public void validateDelete(T entity) {
    validate(entity);
    getDeleteValidation().validate(entity);
  }

  @Override
  public void validateInsert(T entity) {
    validate(entity);
    getInsertValidation().validate(entity);
  }

  @Override
  public void validateUpdate(T entity) {
    validate(entity);
    getUpdateValidation().validate(entity);
  }

  @Override
  public void add(T obj) {
    validateInsert(obj);
    getRepository().save(obj);
    refresh();
  }




}
