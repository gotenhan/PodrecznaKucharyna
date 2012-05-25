package org.ii.kucharyna.persistance.communication;

import java.io.IOException;
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

public abstract class MongoRequest {
  protected final String host;
  protected final String apiKey;
  protected final String basePath;
  protected final Integer port;

  MongoRequest(String host, String basePath, Integer port, String apiKey) {
    if (host == null)
      throw new NullPointerException("MongoRequest#constructor: host cannot be null");
    if (basePath == null)
      throw new NullPointerException("MongoRequest#constructor: basePath cannot be null");
    this.basePath = basePath;
    this.host = host;
    this.apiKey = apiKey;
    this.port = port;
  }

  protected final String getApiKey() {
    if (this.apiKey != null)
      return this.apiKey;
    else
      return "";
  }

  protected final String getParams() throws IOException {
    List<NameValuePair> params = getParamsList();
    return URLEncodedUtils.format(params, "UTF-8");
  }

  protected List<NameValuePair> getParamsList() throws IOException {
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    if(apiKey != null)
      params.add(new BasicNameValuePair("apiKey", getApiKey()));
    return params;
  }

  protected String buildPath(){
    return basePath;
  }

  HttpUriRequest getHttpRequest() throws URISyntaxException, IOException {
    int port = this.port == null ? -1 : this.port;
    URI uri = URIUtils.createURI("https", this.host, port , buildPath(),
        getParams(), null);
    return new HttpGet(uri);
  }
}
