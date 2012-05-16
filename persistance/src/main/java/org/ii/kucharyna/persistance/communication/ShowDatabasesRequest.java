package org.ii.kucharyna.persistance.communication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

class ShowDatabasesRequest extends MongoRequest {

	ShowDatabasesRequest(String host, String basePath, String apiKey) {
		super(host, basePath, apiKey);
	}

	@Override
	HttpUriRequest getHttpRequest() throws URISyntaxException {
		URI uri = URIUtils.createURI("https", this.host, -1, basePath
				+ "/databases", getParams(), null);
		return new HttpGet(uri);
	}

	private String getParams() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiKey", getApiKey()));
		return URLEncodedUtils.format(params, "UTF-8");
	}

}
