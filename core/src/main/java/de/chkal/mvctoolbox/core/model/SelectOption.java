package de.chkal.mvctoolbox.core.model;

import java.io.Serializable;
import java.util.Objects;

public class SelectOption implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String value;
  private final String label;

  public SelectOption(Integer value) {
    this((value != null) ? String.valueOf(value) : null);
  }

  public SelectOption(Integer value, String label) {
    this((value != null) ? String.valueOf(value) : null, label);
  }

  public SelectOption(Long value) {
    this((value != null) ? String.valueOf(value) : null);
  }

  public SelectOption(Long value, String label) {
    this((value != null) ? String.valueOf(value) : null, label);
  }

  public SelectOption(Boolean value) {
    this((value != null) ? String.valueOf(value) : null);
  }

  public SelectOption(Boolean value, String label) {
    this((value != null) ? String.valueOf(value) : null, label);
  }

  public SelectOption(Enum<?> value) {
    this((value != null) ? value.name() : null);
  }

  public SelectOption(Enum<?> value, String label) {
    this((value != null) ? value.name() : null, label);
  }

  public SelectOption(String value) {
    this(value, value);
  }

  public SelectOption(String value, String label) {
    this.value = (value != null) ? value : "";
    this.label = (label != null) ? label : "";
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
    return Objects.equals(value, that.value) &&
        Objects.equals(label, that.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, label);
  }

  @Override
  public String toString() {
    return "SelectOption{" +
        "value='" + value + '\'' +
        ", label='" + label + '\'' +
        '}';
  }

  public String getValue() {
    return value;
  }

  public String getLabel() {
    return label;
  }

}
