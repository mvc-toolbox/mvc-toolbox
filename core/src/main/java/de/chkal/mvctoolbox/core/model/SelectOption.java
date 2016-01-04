package de.chkal.mvctoolbox.core.model;

import java.io.Serializable;
import java.util.Objects;

public class SelectOption implements Serializable {

  private static final long serialVersionUID = 1L;

  private final Object value;
  private final String label;
  private final boolean disabled;

  public SelectOption(Object value) {
    this(value, (value != null) ? value.toString() : null);
  }

  public SelectOption(Object value, String label) {
    this(value, label, false);
  }

  public SelectOption(Object value, String label, boolean disabled) {
    this.value = value;
    this.label = (label != null) ? label : "";
    this.disabled = disabled;
  }

  @Override
  public String toString() {
    return "SelectOption{" +
        "value=" + value +
        ", label='" + label + '\'' +
        ", disabled=" + disabled +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SelectOption)) {
      return false;
    }
    SelectOption that = (SelectOption) o;
    return disabled == that.disabled &&
        Objects.equals(value, that.value) &&
        Objects.equals(label, that.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, label, disabled);
  }

  public Object getValue() {
    return value;
  }

  public String getLabel() {
    return label;
  }

  public boolean isDisabled() {
    return disabled;
  }

}
