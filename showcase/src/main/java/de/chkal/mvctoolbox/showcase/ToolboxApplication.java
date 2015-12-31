package de.chkal.mvctoolbox.showcase;

import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
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
