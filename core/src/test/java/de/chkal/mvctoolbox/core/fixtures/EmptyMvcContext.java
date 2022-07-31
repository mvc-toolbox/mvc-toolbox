package de.chkal.mvctoolbox.core.fixtures;

import java.net.URI;
import java.util.Locale;
import java.util.Map;
import jakarta.enterprise.context.RequestScoped;
import jakarta.mvc.MvcContext;
import jakarta.mvc.security.Csrf;
import jakarta.mvc.security.Encoders;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.UriBuilder;

/**
 * Empty MVC Context for integration testing. May be replaced with some Mock
 * during testing.
 *
 * @author Tobias Erdle
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
