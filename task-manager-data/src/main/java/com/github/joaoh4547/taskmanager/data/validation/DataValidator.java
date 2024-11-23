package com.github.joaoh4547.taskmanager.data.validation;

import java.util.Collection;

/**
 * Abstract base class for data validation that supports chaining multiple validators.
 *
 * @param <T> the type of object to be validated
 */
public abstract class DataValidator<T> {

  /**
   * The next DataValidator in the chain of validators.
   * If this validator passes or completes its validation, the next validator in the chain will be invoked.
   */
  private DataValidator<T> next;

  /**
   * Retrieves the next DataValidator in the chain of validators.
   *
   * @return the next DataValidator in the chain, or null if there is no next validator.
   */
  public DataValidator<T> getNext() {
    return next;
  }

  /**
   * Sets the next DataValidator in the chain of validators.
   *
   * @param next the next DataValidator to be set in the chain
   */
  public void setNext(DataValidator<T> next) {
    this.next = next;
  }

  /**
   * Validates the provided object.
   *
   * @param object the object to be validated
   */
  abstract void validate(T object);

  void validate(Collection<T> objects) {
    for (T object : objects) {
      validate(object);
    }
  }

  /**
   * Validates the provided object and, if applicable, invokes the next validator in the chain.
   *
   * @param object the object to be validated
   */
  void doValidation(T object) {
    validate(object);
    validateNext(object);
  }

  /**
   * Invokes the next validator in the chain, if it exists, to validate the provided object.
   *
   * @param object the object to be validated
   */
  private void validateNext(T object) {
    if (next != null) {
      next.doValidation(object);
    }
  }

  /**
   * Validates a collection of objects and, if applicable, invokes the next validator in the chain.
   *
   * @param objects the collection of objects to be validated
   */
  void doValidation(Collection<T> objects) {
    validate(objects);
    validateNext(objects);
  }

  /**
   * Invokes the next validator in the chain, if it exists, to validate the provided collection of objects.
   *
   * @param objects the collection of objects to be validated
   */
  private void validateNext(Collection<T> objects) {
    if (next != null) {
      next.doValidation(objects);
    }
  }

}
