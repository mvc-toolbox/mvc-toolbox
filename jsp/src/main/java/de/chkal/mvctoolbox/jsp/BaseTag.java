package de.chkal.mvctoolbox.jsp;

import javax.enterprise.inject.spi.CDI;
import javax.mvc.binding.BindingResult;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class BaseTag extends SimpleTagSupport {

  protected <T> T getBean(Class<T> type) {
    return CDI.current().select(type).get();
  }

  protected boolean hasError(String param) {

    if (param != null) {

      BindingResult bindingResult = getBean(BindingResult.class);

      boolean hasBindingError = bindingResult.getErrors(param) != null;
      boolean hasValidationError = bindingResult.getErrors(param) != null;

      return hasBindingError || hasValidationError;

    }
    return false;

  }


}
