package de.chkal.mvctoolbox.core.message;

import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MvcMessagesImplTest {

	@Test
	public void testAddForBindingResultShouldAddMessages() {
		final var bindingResult = mock(BindingResult.class);
		final var fooError = createParamError("foo", "foo error");
		final var barError = createParamError("bar", "bar error");

		when(bindingResult.getAllErrors()).thenReturn(Set.of(fooError, barError));

		final var sut = new MvcMessagesImpl();

		sut.add(bindingResult);

		assertThat(sut.getAll()).containsExactlyInAnyOrder(createErrorMessage("foo", "foo error"),
				createErrorMessage("bar", "bar error"));
	}

	private ParamError createParamError(final String param, final String message) {
		return new ParamError() {
			@Override
			public String getMessage() {
				return message;
			}

			@Override
			public String getParamName() {
				return param;
			}
		};
	}

	private MvcMessage createErrorMessage(final String param, final String message) {
		return MvcMessage.error(param, message);
	}
}