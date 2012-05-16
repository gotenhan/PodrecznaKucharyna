package org.ii.kucharyna.persistance.communication;

public class MongoRequestFactory {
	private static final String apiToken = "4fb3ff36e4b007d2669b6b63";
	private static final String host = "api.mongolab.com";
	private static final String basePath = "/api/1";
	
	public static MongoRequest createShowDatabasesRequest(){
		return new ShowDatabasesRequest(host, basePath, apiToken);
	}
}
