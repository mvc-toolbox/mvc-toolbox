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
}