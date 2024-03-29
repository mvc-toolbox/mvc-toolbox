package de.chkal.mvctoolbox.core.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MvcMessageTest {

	@Test
	public void successWithoutParamReturnsGlobalSuccessMessage() {
		final MvcMessage sut = MvcMessage.success("This is a global success!");

		assertEquals(MvcMessage.Severity.SUCCESS, sut.getSeverity());
		assertEquals("This is a global success!", sut.getText());
		assertTrue(sut.isGlobal());
	}

	@Test
	public void successWithParameterReturnsParamBoundSuccessMessage() {
		final MvcMessage sut = MvcMessage.success("test", "This is a param bound success!");

		assertEquals(MvcMessage.Severity.SUCCESS, sut.getSeverity());
		assertEquals("This is a param bound success!", sut.getText());
		assertFalse(sut.isGlobal());
	}

	@Test
	public void toStringShouldReturnText() {
		final MvcMessage sut = MvcMessage.info("This is a test");

		assertEquals("This is a test", sut.toString());
	}
}