package de.chkal.mvctoolbox.core.translation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;
import jakarta.mvc.MvcContext;
import org.junit.Before;
import org.junit.Test;

public class PropertiesTranslationResolverTest {

  private MvcContext mvcContext;
  private PropertiesTranslationResolver systemUnderTest;

  @Before
  public void setUp() {
    mvcContext = mock(MvcContext.class);
    when(mvcContext.getLocale()).thenReturn(Locale.US);

    systemUnderTest = new PropertiesTranslationResolver("messages", mvcContext);
  }

  @Test(expected = NullPointerException.class)
  public void expectNullPointerExceptionWhenResolveParamKeyIsNull() {
    systemUnderTest.resolve(null);
  }

  @Test
  public void expectDefaultTranslationWhenNoPropertiesForLocaleAreFound() {
    when(mvcContext.getLocale()).thenReturn(Locale.FRANCE);
    final String translation = systemUnderTest.resolve("hello");

    assertEquals("Hello", translation);
  }

  @Test
  public void expectCorrectTranslationWhenPropertiesForLocaleAreFound() {
    when(mvcContext.getLocale()).thenReturn(Locale.GERMAN);
    final String translation = systemUnderTest.resolve("hello");

    assertEquals("Hallo", translation);
  }

  @Test
  public void expectNullWhenNoTranslationForKeyIsFound() {
    when(mvcContext.getLocale()).thenReturn(Locale.GERMAN);
    final String translation = systemUnderTest.resolve("unknown");

    assertNull(translation);
  }

  @Test(expected = NullPointerException.class)
  public void resolveWithArgsExpectNullPointerExceptionWhenParamKeyIsNull() {
    systemUnderTest.resolve(null, "John", "Doe");
  }

  @Test(expected = NullPointerException.class)
  public void resolveWithArgsExpectNullPointerExceptionWhenParamArgsIsNull() {
    systemUnderTest.resolve("my.key", null);
  }

  @Test
  public void resolveWithArgsExpectDefaultTranslationWhenLocaleHasNoTranslations() {
    when(mvcContext.getLocale()).thenReturn(Locale.FRANCE);
    final String translation = systemUnderTest.resolve("hello.placeholder", "John", "Doe");

    assertEquals("Hello, John Doe!", translation);
  }

  @Test
  public void resolveWithArgsExpectNullWhenNoTranslationForKeyIsFound() {
    when(mvcContext.getLocale()).thenReturn(Locale.US);
    final String translation = systemUnderTest.resolve("hello.withArgs", "John", "Doe");

    assertNull(translation);
  }

  @Test
  public void resolveWithArgsExpectTranslationWhenLocaleHasTranslations() {
    when(mvcContext.getLocale()).thenReturn(Locale.GERMAN);
    final String translation = systemUnderTest.resolve("hello.placeholder", "John", "Doe");

    assertEquals("Hallo, John Doe!", translation);
  }

  @Test
  public void resolveWithArgsExpectNoErrorWhenTooLessPlaceholdersAreInTemplate() {
    final String translation = systemUnderTest.resolve("hello.tooLess", "John", "Doe");

    assertEquals("Hello, John!", translation);
  }

  @Test
  public void resolveWithArgsExpectNoErrorWhenTooManyPlaceholdersAreInTemplate() {
    final String translation = systemUnderTest.resolve("hello.tooMany", "John", "Doe");

    assertEquals("Hello, John Doe {2}!", translation);
  }
}