package de.chkal.mvctoolbox.core.linkto.impl;

import de.chkal.mvctoolbox.core.linkto.impl.DefaultLinkTo.LinkToBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * <p>The test for {@link DefaultLinkTo.LinkToBuilder}.</p>
 *
 * @author Florian Hirsch
 */
public class LinkToBuilderTest {

	private static class DummyController {
		void dummy() {}
	}

	private static LinkedMethod basePath = LinkedMethod.fromTemplate("/base-path").build();

	private static LinkedMethod allParams = LinkedMethod.fromTemplate("/base-path/params/{p1}/{p2}/{p3}") //
														.matrixParam("m1").matrixParam("m2").matrixParam("m3") //
														.queryParam("q1").queryParam("q2").queryParam("q3").build(); //

	@Test
	public void plain() {
		assertThat(new LinkToBuilder(basePath).build().toString(), equalTo("/base-path"));
	}

	@Test
	public void testParams() {
		assertThat(new LinkToBuilder(allParams) //
						.param("p1", 1).param("p2", "2").param("p3", 3).build().toString(), //
						equalTo("/base-path/params/1/2/3")); //
		assertThat(new LinkToBuilder(allParams) //
						.param("p1", 1).param("p2", "2").param("p3", 3) //
						.param("q1", 9).param("q2", "8").param("q3", 7).build().toString(), //
						equalTo("/base-path/params/1/2/3?q1=9&q2=8&q3=7")); //
		assertThat(new LinkToBuilder(allParams) //
						.param("p1", 1).param("p2", "2").param("p3", 3) //
						.param("m1", 9).param("m2", "8").param("m3", 7).build().toString(), //
						equalTo("/base-path/params/1/2/3;m1=9;m2=8;m3=7")); //
		assertThat(new LinkToBuilder(allParams) //
						.param("p1", 1).param("p2", "2").param("p3", 3) //
						.param("m1", 9).param("q2", "8").build().toString(), //
						equalTo("/base-path/params/1/2/3;m1=9?q2=8")); //
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingPathParams() {
		new LinkToBuilder(allParams).param("p1", 1).build();
	}

}
