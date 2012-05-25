package org.ii.kucharyna.persistance.communication;


class ShowDatabasesRequest extends MongoRequest {

	ShowDatabasesRequest(String host, String basePath, Integer port, String apiKey) {
		super(host, basePath, port, apiKey);
	}

	@Override
	protected String buildPath() {
	  return String.format("%sdatabases", basePath);
	}
	
}
