package org.ii.kucharyna.persistance.communication;

import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpUriRequest;

abstract class MongoRequest {
	protected final String host;
	protected final String apiKey;
	protected final String basePath;

	MongoRequest(String host, String basePath, String apiKey) {
		if (host == null)
			throw new IllegalArgumentException("host cannot be null");
		if (basePath == null)
			throw new IllegalArgumentException("basePath cannot be null");
		this.basePath = basePath;
		this.host = host;
		this.apiKey = apiKey;
	}

	protected String getApiKey() {
		if (this.apiKey != null)
			return this.apiKey;
		else
			return "";
	}

	abstract HttpUriRequest getHttpRequest() throws URISyntaxException;
}
