package de.chkal.mvctoolbox.core.typesafe;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ViewName {
  String value();
}