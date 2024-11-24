package com.github.joaoh4547.taskmanager.swing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Page {
  String name();

  String description() default "";

  String pageGroup() default "Geral";
}
