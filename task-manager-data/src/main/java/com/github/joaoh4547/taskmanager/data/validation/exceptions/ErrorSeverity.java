package com.github.joaoh4547.taskmanager.data.validation.exceptions;

/**
 * Enumeration representing the severity levels of validation errors.
 * <p>
 * The levels defined are:
 * </p>
 * <ul>
 * <li>INFO: Represents informational messages that do not constitute
 * errors.</li>
 * <li>WARNING: Represents potentially harmful situations that may require
 * attention but are not critical.</li>
 * <li>ERROR: Represents critical issues that need to be addressed
 * immediately.</li>
 * </ul>
 */
public enum ErrorSeverity {

  /**
   * Represents informational messages that do not constitute errors.
   */
  INFO,

  /**
   * Represents potentially harmful situations that may require attention but
   * are not critical.
   */
  WARNING,

  /**
   * Represents critical issues that need to be addressed immediately.
   */
  ERROR;

}
