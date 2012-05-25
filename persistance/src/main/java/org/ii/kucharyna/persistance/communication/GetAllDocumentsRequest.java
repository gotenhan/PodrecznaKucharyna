package org.ii.kucharyna.persistance.communication;

public class GetAllDocumentsRequest extends MongoRequest {

  private String database;
  private String collection;

  public GetAllDocumentsRequest(String database, String collection,
      String host, String basePath, Integer port, String apiKey) {
    super(host, basePath, port, apiKey);
    if (database == null)
      throw new NullPointerException();
    if (collection == null)
      throw new NullPointerException();
    this.database = database;
    this.collection = collection;
  }

  @Override
  protected String buildPath() {
    return String.format("%sdatabases/%s/collections/%s", basePath, database,
        collection);
  }

}
