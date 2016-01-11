package de.chkal.mvctoolbox.showcase.typesafe;

import de.chkal.mvctoolbox.core.typesafe.ViewName;

public enum Views {

  @ViewName("hello.jsp")
  VIEW1,

  @ViewName("typesafe.jsp")
  VIEW2,

  @ViewName("somedirectory/otherview.jsp")
  VIEW3;

}
