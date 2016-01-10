package de.chkal.mvctoolbox.core.redirect;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Redirect {

  private static final Pattern PARAM_PATTERN = Pattern.compile("\\{(\\w+)\\}");

  private final String template;

  private final Map<String, Object> params = new LinkedHashMap<>();

  private Redirect(String template) {
    this.template = template;
  }

  public static Redirect to(String path) {
    return new Redirect(path);
  }

  public Redirect set(String name, Object value) {
    params.put(name, value);
    return this;
  }

  @Override
  public String toString() {
    return build();
  }

  public String build() {

    // we cannot use here StringBuilder because Matcher requires a StringBuffer
    StringBuffer result = new StringBuffer("redirect:");

    // create copy as we modify the map in this algorithm
    Map<String, Object> remainingParams = new LinkedHashMap<>(params);

    /*
     * Step 1: Build path segment
     */
    Matcher matcher = PARAM_PATTERN.matcher(template);
    while (matcher.find()) {
      String name = matcher.group(1);
      Object value = remainingParams.remove(name);
      if (value == null) {
        throw new IllegalStateException("Cannot find value for parameter: " + name);
      }
      matcher.appendReplacement(result, encodePath(value.toString()));
    }
    matcher.appendTail(result);

    /*
     * Step 2: Build query string
     */
    String queryString = remainingParams.entrySet().stream()
        .map(e -> createQueryParam(e))
        .filter(s -> !s.isEmpty())
        .collect(Collectors.joining("&"));
    if (!queryString.isEmpty()) {
      result.append("?").append(queryString);
    }

    return result.toString();

  }

  private static String createQueryParam(Map.Entry<String, Object> e) {
    if (e.getKey() != null && e.getValue() != null) {
      return encodeQuery(e.getKey()) + "=" + encodeQuery(e.getValue().toString());
    }
    return "";
  }

  private static String encodeQuery(String s) {
    if (s != null) {
      try {
        return URLEncoder.encode(s, "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new IllegalArgumentException(e);
      }
    }
    return null;
  }

  private static String encodePath(String s) {
    if (s != null) {
      // According to the RFC this is the only difference between query and path segment encoding
      return encodeQuery(s).replace("+", "%20");
    }
    return null;
  }

}
