package de.chkal.mvctoolbox.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClassList {

  private final List<String> list = new ArrayList<>();

  public static ClassList empty() {
    return new ClassList();
  }

  public static ClassList of(String... classes) {
    ClassList classList = new ClassList();
    for (String c : classes) {
      classList.add(c);
    }
    return classList;
  }

  private ClassList() {
    // use static factory methods
  }

  public ClassList add(String classes) {
    if (classes != null && classes.trim().length() > 0) {
      list.add(classes.trim());
    }
    return this;
  }

  public String getAllClasses() {
    if (!list.isEmpty()) {
      return list.stream().collect(Collectors.joining(" "));
    }
    return null;
  }

  public void write(HtmlWriter writer) throws IOException {
    String allClasses = getAllClasses();
    if (allClasses != null) {
      writer.attribute("class", allClasses);
    }
  }

}
