package com.github.joaoh4547.taskmanager.utils;

import org.apache.commons.beanutils.BeanUtilsBean2;

public class Objects {

  public static <T> T requireNonNull(T obj, String message) {
    if (obj == null) {
      throw new NullPointerException(message);
    }
    return obj;
  }

  public static <T> Object getValue(T obj, String propertyName) {
    try {
      return BeanUtilsBean2.getInstance().getProperty(obj, propertyName);
    } catch (Exception e) {
      return null;
    }
  }
}
