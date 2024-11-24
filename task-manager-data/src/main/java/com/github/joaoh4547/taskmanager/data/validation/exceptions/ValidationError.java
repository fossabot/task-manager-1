package com.github.joaoh4547.taskmanager.data.validation.exceptions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a validation error within the system. This record encapsulates
 * information about a specific validation error including the error message,
 * the property where the error occurred, and the severity level of the error.
 */
public record ValidationError(String message, String property,
                              ErrorSeverity severity) {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationError that = (ValidationError) o;
    return new EqualsBuilder().append(message, that.message)
        .append(property, that.property).append(severity, that.severity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(message).append(property)
        .append(severity).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("message", message)
        .append("property", property).append("severity", severity)
        .toString();
  }
}
