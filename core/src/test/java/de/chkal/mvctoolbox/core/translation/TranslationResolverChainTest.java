package de.chkal.mvctoolbox.core.translation;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Priority;
import javax.enterprise.inject.Instance;
import javax.mvc.MvcContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TranslationResolverChainTest {

  @Mock
  private MvcContext mvcContext;

  @Mock
  private Instance<TranslationResolver> translationResolver;

  @InjectMocks
  private TranslationResolverChain sytemUnderTest;

  @Before
  public void setUp() {
    when(translationResolver.stream()).thenReturn(Stream.of(new DummyResolver3(), new DummyResolver1(), new DummyResolver2()));
  }

  @Test
  public void expectPlaceholderWhenNoTranslationResolverKnowsKey() {
    final String translation = sytemUnderTest.resolve("my.unknown.key", null);

    assertEquals("???my.unknown.key???", translation);
  }

  @Test
  public void expectTranslationFromDummyResolver3WhenResolver1AndResolver3KnowTheKey() {
    final String translation = sytemUnderTest.resolve("my.key.1", null);

    assertEquals("myFirstKeyFromDummyResolver3", translation);
  }

  @Test
  public void expectTranslationFromDummyResolver1WhenOnlyResolver1KnowsTheKey() {
    final String translation = sytemUnderTest.resolve("my.unique.key", null);

    assertEquals("myUniqueKey", translation);
  }

  @Test
  public void expectTranslationFromDummyResolver2WhenResolver1AndResolver2KnowTheKey() {
    final String translation = sytemUnderTest.resolve("my.key.2", null);

    assertEquals("mySecondKeyFromDummyResolver2", translation);
  }

  /*
   *
   * DUMMY TRANSLATION PROVIDER
   *
   */

  @Priority(TranslationResolver.DEFAULT_PRIORITY - 1)
  private static final class DummyResolver1 implements TranslationResolver {

    private static final Map<String, String> TRANSLATIONS = new HashMap<>();

    static {
      TRANSLATIONS.put("my.unique.key", "myUniqueKey");
      TRANSLATIONS.put("my.key.1", "myFirstKeyFromDummyResolver1");
      TRANSLATIONS.put("my.key.2", "mySecondKeyFromDummyResolver1");
    }

    @Override
    public String resolve(final String key, final Locale locale) {
      return TRANSLATIONS.get(key);
    }

    @Override
    public String resolve(final String key, final Locale locale, final Object... args) {
      return null;
    }
  }

  @Priority(TranslationResolver.DEFAULT_PRIORITY)
  private static final class DummyResolver2 implements TranslationResolver {

    private static final Map<String, String> TRANSLATIONS = new HashMap<>();

    static {
      TRANSLATIONS.put("my.key.2", "mySecondKeyFromDummyResolver2");
    }

    @Override
    public String resolve(final String key, final Locale locale) {
      return TRANSLATIONS.get(key);
    }

    @Override
    public String resolve(final String key, final Locale locale, final Object... args) {
      return null;
    }
  }

  @Priority(TranslationResolver.DEFAULT_PRIORITY + 1)
  private static final class DummyResolver3 implements TranslationResolver {

    private static final Map<String, String> TRANSLATIONS = new HashMap<>();

    static {
      TRANSLATIONS.put("my.key.1", "myFirstKeyFromDummyResolver3");
    }


    @Override
    public String resolve(final String key, final Locale locale) {
      return TRANSLATIONS.get(key);
    }

    @Override
    public String resolve(final String key, final Locale locale, final Object... args) {
      return null;
    }
  }
}