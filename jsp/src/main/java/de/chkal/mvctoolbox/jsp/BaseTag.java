package de.chkal.mvctoolbox.jsp;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class BaseTag extends SimpleTagSupport {

  protected <T> T getBean(Class<T> type) {
    return CDI.current().select(type).get();
  }

}
