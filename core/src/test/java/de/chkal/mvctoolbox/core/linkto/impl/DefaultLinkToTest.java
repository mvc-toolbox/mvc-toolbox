package de.chkal.mvctoolbox.core.linkto.impl;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.apache.deltaspike.testcontrol.api.mock.DynamicMockManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.mvc.MvcContext;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers.ParamsController;
import static de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers.SomeController;
import static de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers.TargetController;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <p>The test for {@link DefaultLinkTo}.</p>
 *
 * @author Florian Hirsch
 */
@RunWith(CdiTestRunner.class)
public class DefaultLinkToTest {

	@Inject
	private DynamicMockManager mockManager;

	@Inject
	private DefaultLinkTo linkTo;

	@Before
	public void onBefore() {
		mockManager.addMock(new MockedLinkedMethods());
	}

	@Test
	public void target() {
		assertThat(linkTo.target("target-method").toString(), equalTo("/base-path/target"));
		assertThat(linkTo.target("target-method", Collections.emptyList()).toString(), equalTo("/base-path/target"));
		assertThat(linkTo.target("target-method", Collections.emptyMap()).toString(), equalTo("/base-path/target"));
	}

	@Test
	public void method() {
		assertThat(linkTo.method("SomeController", "path").toString(), equalTo("/base-path/some/path"));
		assertThat(linkTo.method("ParamsController", "pathParams", Arrays.asList("foo", 4711)).toString(), equalTo("/base-path/params/path/foo/4711"));
		Map<String, Object> params = new HashMap<>();
		params.put("p1", "foo");
		params.put("p2", 4711);
		params.put("q1", "bar");
		params.put("m1", 42);
		assertThat(linkTo.method("ParamsController", "pathParams", params).toString(), equalTo("/base-path/params/path/foo/4711"));
		assertThat(linkTo.method("ParamsController", "queryParams", params).toString(), equalTo("/base-path/params/query?q1=bar"));
		assertThat(linkTo.method("ParamsController", "matrixParams", params).toString(), equalTo("/base-path/params/matrix;m1=42"));
	}

	@Typed
	static class MockedLinkedMethods extends LinkedMethods {
		MockedLinkedMethods() {
			this.application = mock(Application.class);
			when(application.getSingletons()).thenReturn(Collections.singleton(new TargetController()));
			when(application.getClasses()).thenReturn(new HashSet<>(Arrays.asList(SomeController.class, ParamsController.class)));
		}
	}

	@Produces
	private MvcContext mockMvcContext() {
		MvcContext ctx = mock(MvcContext.class);
		when(ctx.getBasePath()).thenReturn("/base-path");
		return ctx;
	}

}
