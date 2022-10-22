package de.chkal.mvctoolbox.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.chkal.mvctoolbox.core.message.MvcMessage;
import de.chkal.mvctoolbox.core.message.MvcMessages;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToolboxTest {

  @Mock
  MvcMessages messages;

  @InjectMocks
  Toolbox sut;

  @Test
  public void getMessagesReturnsMvcMessagesInstance() {
    assertThat(sut.getMessages()).isEqualTo(messages);
  }

  @Test
  public void msgShouldOnlyReturnGlobalMessages() {
    final MvcMessage error = MvcMessage.error("An error");
    final MvcMessage success = MvcMessage.success("A success");
    final MvcMessage warning = MvcMessage.warning("A warning");
    final MvcMessage info = MvcMessage.info("An info");
    final MvcMessage infoForParam = MvcMessage.info("foobar", "An param info");

    when(messages.getAll()).thenReturn(List.of(error, success, warning, info, infoForParam));

    assertThat(sut.msg()).containsExactly(error, success, warning, info);
  }

  @Test
  public void msgWithParamShouldThrowIllegalArgumentExceptionWhenParamIsNull() {
    assertThatThrownBy(() -> sut.msg(null)).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void msgWithParamShouldThrowIllegalArgumentExceptionWhenParamIsBlank() {
    assertThatThrownBy(() -> sut.msg(" ")).isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void msgWithParamCallsMvcMessagesGetMessagesWithParam() {
    sut.msg("foobar");

    verify(messages).getMessages(eq("foobar"));
  }

  @Test
  public void msgECallsMvcMessagesGetErrors() {
    sut.msgE();

    verify(messages).getErrors();
  }

  @Test
  public void msgICallsMvcMessagesGetInfos() {
    sut.msgI();

    verify(messages).getInfos();
  }

  @Test
  public void msgWCallsMvcMessagesGetWarnings() {
    sut.msgW();

    verify(messages).getWarnings();
  }

  @Test
  public void msgSCallsMvcMessagesGetSuccesses() {
    sut.msgS();

    verify(messages).getSuccesses();
  }
}