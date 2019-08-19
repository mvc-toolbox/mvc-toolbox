package de.chkal.mvctoolbox.core.fixtures;

import java.net.URI;
import java.util.Locale;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.mvc.MvcContext;
import javax.mvc.security.Csrf;
import javax.mvc.security.Encoders;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.UriBuilder;

/**
 * Empty MVC Context for integration testing. May be replaced with some Mock
 * during testing.
 */
@RequestScoped
public class EmptyMvcContext implements MvcContext {

  @Override
  public Configuration getConfig() {
    return null;
  }

  @Override
  public String getBasePath() {
    return null;
  }

  @Override
  public Csrf getCsrf() {
    return null;
  }

  @Override
  public Encoders getEncoders() {
    return null;
  }

  @Override
  public Locale getLocale() {
    return null;
  }

  @Override
  public URI uri(final String identifier) {
    return null;
  }

  @Override
  public URI uri(final String identifier, final Map<String, Object> params) {
    return null;
  }

  @Override
  public UriBuilder uriBuilder(final String identifier) {
    return null;
  }
}
