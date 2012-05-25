package org.ii.kucharyna.persistance.communication;

public class MongoRequestFactory {
  private static final String defaultApiToken = "4fb3ff36e4b007d2669b6b63";
  private static final String defaultHost = "api.mongolab.com";
  private static final String defaultBasePath = "/api/1/";

  private final String apiToken;
  private final String host;
  private final String basePath;
  private final Integer port;

  public MongoRequestFactory() {
    this.apiToken = MongoRequestFactory.defaultApiToken;
    this.basePath = MongoRequestFactory.defaultBasePath;
    this.host = MongoRequestFactory.defaultHost;
    this.port = null;
  }

  public MongoRequestFactory(String host, String basePath, Integer port,
      String apiToken) {
    if (host == null)
      throw new NullPointerException(
          "MongoRequestFactory#constructor: host cannot be null");
    if (basePath == null)
      throw new NullPointerException(
          "MongoRequestFactory#constructor: basePath cannot be null");
    this.apiToken = apiToken;
    this.basePath = basePath;
    this.host = host;
    this.port = port;
  }

  public MongoRequest createShowDatabasesRequest() {
    return new ShowDatabasesRequest(host, basePath, port, apiToken);
  }

  public MongoRequest createShowCollectionsRequest(String database) {
    return new ShowCollectionsRequest(database, host, basePath, port, apiToken);
  }

  public MongoRequest createGetAllDocumentsRequest(String database,
      String collection) {
    return new GetAllDocumentsRequest(database, collection, host, basePath,
        port, apiToken);
  }

  public MongoRequest createGetDocumentsRequest(String database,
      String collection, MongoQuery query) {
    return new GetDocumentsRequest(database, collection, query, host, basePath,
        port, apiToken);
  }
}
