package de.chkal.mvctoolbox.core.translation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Locale;
import org.junit.Before;
import org.junit.Test;

public class PropertiesTranslationResolverTest {

  private PropertiesTranslationResolver systemUnderTest;

  @Before
  public void setUp() {
    systemUnderTest = new PropertiesTranslationResolver();
  }

  @Test(expected = NullPointerException.class)
  public void expectNullPointerExceptionWhenResolveParamKeyIsNull() {
    systemUnderTest.resolve(null, Locale.US);
  }

  @Test(expected = NullPointerException.class)
  public void expectNullPointerExceptionWhenResolveParamLocaleIsNull() {
    systemUnderTest.resolve("my.key", null);
  }

  @Test
  public void expectDefaultTranslationWhenNoPropertiesForLocaleAreFound() {
    final String translation = systemUnderTest.resolve("hello", Locale.CANADA);

    assertEquals("Hello", translation);
  }

  @Test
  public void expectCorrectTranslationWhenPropertiesForLocaleAreFound() {
    final String translation = systemUnderTest.resolve("hello", Locale.GERMAN);

    assertEquals("Hallo", translation);
  }

  @Test
  public void expectNullWhenNoTranslationForKeyIsFound() {
    final String translation = systemUnderTest.resolve("unknown", Locale.GERMAN);

    assertNull(translation);
  }

  @Test(expected = NullPointerException.class)
  public void resolveWithArgsExpectNullPointerExceptionWhenParamKeyIsNull() {
    systemUnderTest.resolve(null, Locale.US, "John", "Doe");
  }

  @Test(expected = NullPointerException.class)
  public void resolveWithArgsExpectNullPointerExceptionWhenParamLocaleIsNull() {
    systemUnderTest.resolve("my.key", null, "John", "Doe");
  }

  @Test(expected = NullPointerException.class)
  public void resolveWithArgsExpectNullPointerExceptionWhenParamArgsIsNull() {
    systemUnderTest.resolve("my.key", Locale.US, null);
  }

  @Test
  public void resolveWithArgsExpectDefaultTranslationWhenLocaleHasNoTranslations() {
    final String translation = systemUnderTest.resolve("hello.placeholder", Locale.CANADA, "John", "Doe");

    assertEquals("Hello, John Doe!", translation);
  }

  @Test
  public void resolveWithArgsExpectNullWhenNoTranslationForKeyIsFound() {
    final String translation = systemUnderTest.resolve("hello.withArgs", Locale.CANADA, "John", "Doe");

    assertNull(translation);
  }

  @Test
  public void resolveWithArgsExpectTranslationWhenLocaleHasTranslations() {
    final String translation = systemUnderTest.resolve("hello.placeholder", Locale.GERMAN, "John", "Doe");

    assertEquals("Hallo, John Doe!", translation);
  }

  @Test
  public void resolveWithArgsExpectNoErrorWhenTooLessPlaceholdersAreInTemplate() {
    final String translation = systemUnderTest.resolve("hello.tooLess", Locale.US, "John", "Doe");

    assertEquals("Hello, John!", translation);
  }

  @Test
  public void resolveWithArgsExpectNoErrorWhenTooManyPlaceholdersAreInTemplate() {
    final String translation = systemUnderTest.resolve("hello.tooMany", Locale.US, "John", "Doe");

    assertEquals("Hello, John Doe {2}!", translation);
  }
}