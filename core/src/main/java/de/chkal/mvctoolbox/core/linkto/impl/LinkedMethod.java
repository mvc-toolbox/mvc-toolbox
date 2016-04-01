package de.chkal.mvctoolbox.core.linkto.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Encapsulates an uri-template and query- and matrix parameters.</p>
 *
 * @author Florian Hirsch
 */
public class LinkedMethod {

	private final String uriTemplate;

	private final Set<String> queryParams;

	private final Set<String> matrixParams;

	private LinkedMethod(String uriTemplate, Set<String> queryParams, Set<String> matrixParams) {
		this.uriTemplate = uriTemplate;
		this.queryParams = queryParams;
		this.matrixParams = matrixParams;
	}

	public static Builder fromTemplate(String template) {
		return new Builder(template);
	}

	public String uriTemplate() {
		return uriTemplate;
	}

	public Set<String> gueryParams() {
		return queryParams;
	}

	public Set<String> matrixParams() {
		return matrixParams;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || getClass() != other.getClass()) {
			return false;
		}
		LinkedMethod that = (LinkedMethod) other;
		return Objects.equals(uriTemplate, that.uriTemplate) &&
				Objects.equals(queryParams, that.queryParams) &&
				Objects.equals(matrixParams, that.matrixParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uriTemplate, queryParams, matrixParams);
	}

	@Override
	public String toString() {
		return String.format("{ uriTemplate: %s, queryParams: %s, matrixParams: %s }", uriTemplate, queryParams, matrixParams);
	}

	/**
	 * <p>Builder for a LinkedMethod.</p>
	 */
	public static class Builder {

		private String template;

		private Set<String> queryParams;

		private Set<String> matrixParams;

		public Builder(String template) {
			Objects.requireNonNull(template, "template must not be null");
			this.template = template;
		}

		public Builder queryParam(String queryParam) {
			if (queryParams == null) {
				queryParams = new HashSet<>();
			}
			queryParams.add(queryParam);
			return this;
		}

		public Builder matrixParam(String matrixParam) {
			if (matrixParams == null) {
				matrixParams = new HashSet<>();
			}
			matrixParams.add(matrixParam);
			return this;
		}

		public LinkedMethod build() {
			return new LinkedMethod(template,
					queryParams == null ? Collections.emptySet() : queryParams,
					matrixParams == null ? Collections.emptySet() : matrixParams);
		}

	}

}
