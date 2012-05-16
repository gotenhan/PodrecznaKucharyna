package org.ii.kucharyna.persistance.communication;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class MongoLabCommunicator {
	private final int maxRetries = 5;
	private HttpClient httpClient;

	public MongoLabCommunicator() {
		httpClient = new DefaultHttpClient();
	}

	public String sendReceive(MongoRequest request)
			throws ClientProtocolException, IOException, URISyntaxException {
		if (request == null)
			throw new IllegalArgumentException(
					"MongoLabCommunicator: request cannot be null");
		String responseString = null;
		int retriesLeft = maxRetries;
		while (true) {
			try {
				HttpResponse response = httpClient.execute(request
						.getHttpRequest());
				responseString = getResponseString(response);
				break;
			} catch (SocketTimeoutException ex) {
				if (retriesLeft <= 0)
					throw ex;
				retriesLeft--;
			}
		}
		return responseString;
	}

	// TODO investigate what happens on very long stream
	private String getResponseString(HttpResponse response) throws IOException {
		String responseString = null;
		responseString = null;
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			responseString = EntityUtils.toString(entity);
		}
		return responseString;
	}

	private Logger getLogger() {
		return Logger.getLogger("MongoLabCommunicator");
	}

	void setSocketTimeout(int millis) {
		HttpConnectionParams.setSoTimeout(httpClient.getParams(), millis);
	}

	void setConnectionTimeout(int millis) {
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
				millis);
	}
}
