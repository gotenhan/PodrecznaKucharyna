package org.ii.kucharyna.persistance.communication;

public class ShowCollectionsRequest extends MongoRequest {

  private String database;

  ShowCollectionsRequest(String database, String host, String basePath,
      Integer port, String apiKey) {
    super(host, basePath, port, apiKey);
    if (database == null)
      throw new NullPointerException();
    this.database = database;
  }

  @Override
  protected String buildPath() {
    return String.format("%sdatabases/%s/collections", basePath, database);
  }

}
