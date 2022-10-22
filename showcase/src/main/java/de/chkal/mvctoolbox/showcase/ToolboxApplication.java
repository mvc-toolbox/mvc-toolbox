package de.chkal.mvctoolbox.showcase;

import jakarta.mvc.form.FormMethodOverwriter;
import jakarta.mvc.form.FormMethodOverwriter.Options;
import jakarta.mvc.security.Csrf;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/mvc")
public class ToolboxApplication extends Application {

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.OFF);
    return properties;
  }

}
