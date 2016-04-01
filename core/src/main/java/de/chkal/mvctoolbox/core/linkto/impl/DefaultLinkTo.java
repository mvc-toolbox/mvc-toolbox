package de.chkal.mvctoolbox.core.linkto.impl;

import de.chkal.mvctoolbox.core.linkto.api.LinkTo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Implementation of {@link LinkTo}</p>
 *
 * @author Florian Hirsch
 */
@ApplicationScoped
public class DefaultLinkTo implements LinkTo {

	@Inject
	private LinkedMethods linkedMethods;

	@Override
	public URI target(String target) {
		return builderFor(target).build();
	}

	@Override
	public URI target(String target, List<Object> params) {
		// TODO extend LinkToBuilder if we really provide this method
		return UriBuilder.fromUri(linkedMethods.get(target).uriTemplate()).build(params.toArray(new Object[params.size()]));
	}

	@Override
	public URI target(String target, Map<String, Object> params) {
		return builderFor(target).params(params).build();
	}

	@Override
	public URI method(String controller, String method) {
		return builderFor(controller, method).build();
	}

	@Override
	public URI method(String controller, String method, List<Object> params) {
		// TODO extend LinkToBuilder if we really provide this method
		return UriBuilder.fromUri(linkedMethods.get(controller, method).uriTemplate()).build(params.toArray(new Object[params.size()]));
	}

	@Override
	public URI method(String controller, String method, Map<String, Object> params) {
		return builderFor(controller, method).params(params).build();
	}

	@Override
	public Builder builderFor(String target) {
		return new LinkToBuilder(linkedMethods.get(target));
	}

	@Override
	public Builder builderFor(String controller, String method) {
		return new LinkToBuilder(linkedMethods.get(controller, method));
	}

	@Override
	public Builder builderFor(Class<?> controller, String method) {
		return new LinkToBuilder(linkedMethods.get(controller, method));
	}

	/**
	 * <p>Implementation of {@link de.chkal.mvctoolbox.core.linkto.api.LinkTo.Builder}</p>
	 *
	 * @author Florian Hirsch
	 */
	public static class LinkToBuilder implements LinkTo.Builder {

		private LinkedMethod linkedMethod;

		private Map<String, Object> parameters = new LinkedHashMap<>();

		LinkToBuilder(LinkedMethod linkedMethod) {
			Objects.requireNonNull(linkedMethod, "linkedMethod must not be null");
			this.linkedMethod = linkedMethod;
		}

		@Override
		public LinkTo.Builder param(String param, Object value) {
			parameters.put(param, value);
			return this;
		}

		@Override
		public LinkTo.Builder params(Map<String, Object> params) {
			parameters.putAll(params);
			return this;
		}

		@Override
		public URI build() {
			UriBuilder uriBuilder = UriBuilder.fromUri(linkedMethod.uriTemplate());
			Map<String, List<Object>> multiMapParams = LinkToUtils.toMultivaluedMap(parameters);
			linkedMethod.gueryParams().forEach(queryParam -> {
				if (multiMapParams.containsKey(queryParam)) {
					multiMapParams.get(queryParam).forEach(val -> uriBuilder.queryParam(queryParam, val));
				}
			});
			linkedMethod.matrixParams().forEach(matrixParam -> {
				if (multiMapParams.containsKey(matrixParam)) {
					multiMapParams.get(matrixParam).forEach(val -> uriBuilder.matrixParam(matrixParam, val));
				}
			});
			return uriBuilder.buildFromMap(parameters);
		}

	}

}
