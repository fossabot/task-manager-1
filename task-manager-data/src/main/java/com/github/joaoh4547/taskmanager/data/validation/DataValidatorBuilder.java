package com.github.joaoh4547.taskmanager.data.validation;

import com.github.joaoh4547.taskmanager.utils.Builder;

/**
 * A builder class for creating and configuring instances of
 * {@link DataValidator}.
 *
 * @param <T> the type of object being validated
 */
public class DataValidatorBuilder<T>
  implements Builder<DataValidator<T>> {

  /**
   * An instance of {@link DataValidator} used for validating objects.
   */
  public DataValidator<T> dataValidator;

  /**
   * Private constructor for the DataValidatorBuilder class to prevent direct
   * instantiation.
   */
  private DataValidatorBuilder() {
  }

  /**
   * Creates a new instance of {@link DataValidatorBuilder}.
   *
   * @param <T> the type of object being validated
   * @return a new instance of {@link DataValidatorBuilder}
   */
  public static <T> DataValidatorBuilder<T> builder() {
    return new DataValidatorBuilder<>();
  }

  public DataValidatorBuilder<T> validator(ObjectValidator<T> validator) {
    this.dataValidator = new CommonDataValidation<>(validator);
    return this;
  }

  /**
   * Adds a new validator to the chain of validators.
   *
   * @param validator the ObjectValidator to be added to the chain
   * @return the current DataValidatorBuilder instance for method chaining
   */
  public DataValidatorBuilder<T> nextValidator(ObjectValidator<T> validator) {
    DataValidator<T> target = getTargetValidator();
    if (target != null) {
      target.setNext(new CommonDataValidation<>(validator));
    }
    return this;
  }

  /**
   * Retrieves the last DataValidator in the chain of validators.
   *
   * @return the last DataValidator in the chain, or null if no validators are
   *         present
   */
  private DataValidator<T> getTargetValidator() {
    if (dataValidator == null) {
      return null;
    }
    DataValidator<T> validation = null;
    DataValidator<T> next = dataValidator.getNext();
    validation = next;
    boolean found = false;
    while (!found) {
      if (next.getNext() == null) {
        validation = next;
        found = true;
      } else {
        next = next.getNext();
      }
    }
    return validation;
  }

  /**
   * Builds the DataValidator instance that has been configured through the builder.
   *
   * @return the configured instance of DataValidator for validating objects
   */
  @Override
  public DataValidator<T> build() {
    return dataValidator;
  }

  /**
   * CommonDataValidation is a concrete implementation of the DataValidator class
   * that uses an ObjectValidator to perform validation on individual objects of type T.
   *
   * @param <T> the type of object being validated
   */
  public static class CommonDataValidation<T>
    extends DataValidator<T> {

    /**
     * An instance of ObjectValidator used to perform validation on individual objects of type T.
     *
     */
    private final ObjectValidator<T> validator;

    public CommonDataValidation(ObjectValidator<T> validator) {
      this.validator = validator;
    }

    /**
     * Validates the given object using the provided ObjectValidator.
     *
     * @param object the object to be validated
     */
    @Override
    void validate(T object) {
      validator.validate(object);
    }
  }

}
