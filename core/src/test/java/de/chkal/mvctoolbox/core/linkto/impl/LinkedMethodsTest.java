package de.chkal.mvctoolbox.core.linkto.impl;

import org.junit.Before;
import org.junit.Test;

import javax.mvc.MvcContext;

import java.lang.reflect.Method;

import static de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <p>The test for {@link LinkedMethods}.</p>
 *
 * @author Florian Hirsch
 */
public class LinkedMethodsTest {

	private static final String BASE_PATH = "/base-path";

	private LinkedMethods linkedMethods;

	@Before
	public void onBefore() {
		linkedMethods = new LinkedMethods();
		MvcContext mvcContext = mock(MvcContext.class);
		when(mvcContext.getBasePath()).thenReturn(BASE_PATH);
		linkedMethods.mvcContext = mvcContext;
	}

	@Test
	public void parseMethod() throws NoSuchMethodException {
		// basic
		assertThat(linkedMethods.parseMethod(SomeController.class.getMethod("root"), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/some").build()));
		assertThat(linkedMethods.parseMethod(SomeController.class.getMethod("path"), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/some/path").build()));
		// inheritance
		assertThat(linkedMethods.parseMethod(SomeController.class.getMethod("parent"), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/some/parent").build()));
		assertThat(linkedMethods.parseMethod(ControllerImplementation.class.getMethod("show"), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/implementation/show").build()));
		// method params
		assertThat(linkedMethods.parseMethod(ParamsController.class.getMethod("pathParams", String.class, long.class), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/params/path/{p1}/{p2}").build()));
		assertThat(linkedMethods.parseMethod(ParamsController.class.getMethod("queryParams", String.class, long.class), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/params/query").queryParam("q1").queryParam("q2").build()));
		assertThat(linkedMethods.parseMethod(ParamsController.class.getMethod("matrixParams", String.class, long.class), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/params/matrix").matrixParam("m1").matrixParam("m2").build()));
		// fields
		assertThat(linkedMethods.parseMethod(FieldsController.class.getMethod("root"), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/fields/{p1}/{p2}/{p3}") //
						.matrixParam("m1").matrixParam("m2").matrixParam("m3") //
						.queryParam("q1").queryParam("q2").queryParam("q3").build())); //
		// bean params
		assertThat(linkedMethods.parseMethod(BeanParamController.class.getMethod("bean", BeanParams.class), BASE_PATH),
				equalTo(LinkedMethod.fromTemplate("/base-path/bean/{p}").matrixParam("m").queryParam("q").build()));
	}

	@Test
	public void registerMethod() throws NoSuchMethodException {
		LinkedMethod linkedMethod = LinkedMethod.fromTemplate(BASE_PATH).build();
		linkedMethods.register(linkedMethod, SomeController.class.getMethod("root"));
		assertThat(linkedMethods.get("SomeController", "root"), is(linkedMethod));
		assertThat(linkedMethods.get("de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers$SomeController", "root"), is(linkedMethod));
		assertThat(linkedMethods.get(SomeController.class, "root"), is(linkedMethod));
	}

	@Test
	public void registerTarget() throws NoSuchMethodException {
		LinkedMethod dummy = LinkedMethod.fromTemplate(BASE_PATH).build();
		linkedMethods.register(dummy, TargetController.class.getMethod("getRoot"));
		linkedMethods.register(dummy, TargetController.class.getMethod("postRoot"));
		assertThat(linkedMethods.get("target-method").uriTemplate(), is(BASE_PATH));
		// should also work
		assertThat(linkedMethods.get("TargetController#getRoot").uriTemplate(), is(BASE_PATH));
		assertThat(linkedMethods.get("TargetController#postRoot").uriTemplate(), is(BASE_PATH));
		assertThat(linkedMethods.get("de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers$TargetController#getRoot").uriTemplate(), is(BASE_PATH));
		assertThat(linkedMethods.get("de.chkal.mvctoolbox.core.linkto.impl.LinkToTestControllers$TargetController#postRoot").uriTemplate(), is(BASE_PATH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerAmbiguous() throws NoSuchMethodException {
		linkedMethods.register(LinkedMethod.fromTemplate("path-1").build(), TargetController.class.getMethod("getRoot"));
		linkedMethods.register(LinkedMethod.fromTemplate("path-2").build(), AmbiguousController.class.getMethod("getAmbiguousRoot"));
	}

}