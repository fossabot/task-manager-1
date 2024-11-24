package com.github.joaoh4547.taskmanager.data.validation.exceptions;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a validation exception that holds a collection of validation
 * errors.
 * <p>
 * Extends {@code RuntimeException} to signal validation-related issues during
 * the runtime.
 */
public class ValidationException
  extends RuntimeException {

  /**
   * A collection of validation errors associated with this exception. This
   * collection is initialized as an empty set and can hold multiple
   * {@link ValidationError} instances.
   */
  private final Collection<ValidationError> errors = new HashSet<>();

  /**
   * Constructs a new ValidationException with the specified validation error.
   *
   * @param error the validation error to be associated with this exception
   */
  public ValidationException(ValidationError error) {
    super(error.message());
    errors.add(error);
  }

  /**
   * Constructs a new ValidationException with the specified error message.
   *
   * @param message the detail message explaining the reason for the validation
   *                error
   */
  public ValidationException(String message) {
    this(new ValidationError(message, null, ErrorSeverity.ERROR));
  }

  /**
   * Constructs a new ValidationException with the specified error message and
   * property.
   *
   * @param message  the detail message explaining the reason for the validation
   *                 error
   * @param property the property associated with the validation error
   */
  public ValidationException(String message, String property) {
    this(new ValidationError(message, property, ErrorSeverity.ERROR));
  }

  /**
   * Constructs a new ValidationException with the specified error message,
   * property, and severity level.
   *
   * @param message  the detail message explaining the reason for the validation
   *                 error
   * @param property the property associated with the validation error
   * @param severity the severity level of the validation error
   */
  public ValidationException(String message, String property,
                             ErrorSeverity severity) {
    this(new ValidationError(message, property, severity));
  }

  /**
   * Constructs a new ValidationException with no detail message or validation
   * errors.
   * <p>
   * This is the default constructor for creating an instance of
   * {@code ValidationException} without specifying any validation errors or
   * additional context.
   */
  public ValidationException() {
    super();
  }

  /**
   * Retrieves the collection of validation errors associated with this
   * exception.
   *
   * @return the collection of {@link ValidationError} instances.
   */
  public Collection<ValidationError> getErrors() {
    return errors;
  }

  /**
   * Adds the specified validation error to the collection of errors.
   *
   * @param error the validation error to be added
   */
  public void addError(ValidationError error) {
    errors.add(error);
  }

  /**
   * Checks whether there are any validation errors associated with this
   * exception.
   *
   * @return {@code true} if there are validation errors; {@code false}
   *         otherwise.
   */
  public boolean hasErrors() {
    return !errors.isEmpty();
  }

}
