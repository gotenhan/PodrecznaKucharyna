package org.ii.kucharyna.persistance.communication;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;

public class GetDocumentsRequest extends GetAllDocumentsRequest {
  private MongoQuery query;

  public GetDocumentsRequest(String database, String collection,
      MongoQuery query, String host, String basePath, Integer port,
      String apiKey) {
    super(database, collection, host, basePath, port, apiKey);
    if (query == null)
      throw new NullPointerException("query cannot be null");
    this.query = query;
  }

  @Override
  protected List<NameValuePair> getParamsList() throws IOException {
    List<NameValuePair> params = super.getParamsList();
    params.addAll(this.query.toRestParams());
    return params;
  }
}
