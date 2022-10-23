package de.chkal.mvctoolbox.jsp;

import jakarta.enterprise.inject.spi.CDI;
import jakarta.mvc.binding.BindingResult;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.lang.annotation.Annotation;

public abstract class BaseTag extends SimpleTagSupport {

  protected <T> T getBean(Class<T> type) {
    return CDI.current().select(type).get();
  }

  protected <T> T getBean(final Annotation qualifier) {
    return (T) CDI.current().select(qualifier).get();
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
