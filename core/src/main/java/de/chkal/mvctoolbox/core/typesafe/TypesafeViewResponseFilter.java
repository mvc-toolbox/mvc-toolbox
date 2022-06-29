package de.chkal.mvctoolbox.core.typesafe;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Field;

@Provider
@Priority(Priorities.ENTITY_CODER + 1)
public class TypesafeViewResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
      throws IOException {

    Object entity = responseContext.getEntity();
    if (entity instanceof Enum) {
      ViewName viewName = getViewName((Enum<?>) entity);
      if (viewName != null) {
        responseContext.setEntity(viewName.value());
      }
    }

  }

  private ViewName getViewName(Enum<?> en) {
    try {
      Field field = en.getClass().getField(en.name());
      return field.getAnnotation(ViewName.class);
    } catch (NoSuchFieldException e) {
      throw new IllegalStateException(e);
    }
  }

}
