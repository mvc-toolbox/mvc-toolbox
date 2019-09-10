package de.chkal.mvctoolbox.core.translation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Locale;
import javax.inject.Inject;
import javax.mvc.MvcContext;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class TranslationResolverChainIT {

  @Inject
  private TranslationResolverChain systemUnderTest;

  private MvcContext mockMvcContext;

  @Before
  public void setUp() {
    this.mockMvcContext = mock(MvcContext.class);
  }

  @Test
  public void expectTranslationResolversAreInjected() {
    when(mockMvcContext.getLocale()).thenReturn(Locale.GERMAN);
    systemUnderTest.setMvcContext(mockMvcContext);

    final String translation = systemUnderTest.resolve("hello", null);

    Assert.assertEquals("Hallo", translation);
  }

  @Test
  public void expectTemplateTranslationIsUsedWhenArgsAreProvided() {
    when(mockMvcContext.getLocale()).thenReturn(Locale.GERMAN);
    systemUnderTest.setMvcContext(mockMvcContext);

    final String translation = systemUnderTest.resolve("hello.placeholder", new Object[]{"John", "Doe"});

    Assert.assertEquals("Hallo, John Doe!", translation);
  }
}
