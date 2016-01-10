package de.chkal.mvctoolbox.core.redirect;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RedirectTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void shouldWorkForNoParams() {
    String result = Redirect.to("/foobar").build();
    Assert.assertEquals("redirect:/foobar", result);
  }

  @Test
  public void shouldAppendSingleQueryParam() {
    String result = Redirect.to("/path")
        .set("query", "foobar")
        .build();
    Assert.assertEquals("redirect:/path?query=foobar", result);
  }

  @Test
  public void shouldEncodeQueryParamsCorrectly() {
    String result = Redirect.to("/path")
        .set("query", "foo bar")
        .build();
    Assert.assertEquals("redirect:/path?query=foo+bar", result);
  }

  @Test
  public void shouldIgnoreQueryParamsWithNullValue() {
    String result = Redirect.to("/path")
        .set("query", null)
        .build();
    Assert.assertEquals("redirect:/path", result);
  }

  @Test
  public void shouldSupportMultipleQueryParams() {
    String result = Redirect.to("/path")
        .set("p1", "foo")
        .set("p2", "bar")
        .build();
    Assert.assertEquals("redirect:/path?p1=foo&p2=bar", result);
  }

  @Test
  public void shouldSupportPathVariables() {
    String result = Redirect.to("/a/{foo}/b")
        .set("foo", "bar")
        .build();
    Assert.assertEquals("redirect:/a/bar/b", result);
  }

  @Test
  public void shouldFailForMissingPathParam() {
    exceptionRule.expect(IllegalStateException.class);
    exceptionRule.expectMessage("foobar");
    Redirect.to("/{foobar}").build();
  }

  @Test
  public void shouldEncodePathVariablesCorrectly() {
    String result = Redirect.to("/{param}")
        .set("param", "foo bar")
        .build();
    Assert.assertEquals("redirect:/foo%20bar", result);
  }

}