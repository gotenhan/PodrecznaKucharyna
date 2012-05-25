package org.ii.kucharyna.persistance.communication;

import java.util.HashMap;
import java.util.Map;

public class CommunicationTester {
  public static void main(String[] args) {
    try {
      MongoLabCommunicator com = new MongoLabCommunicator();
      MongoRequestFactory factory = new MongoRequestFactory();
      MongoRequest request = factory.createShowDatabasesRequest();
      MongoQuery query;

      com.sendReceive(request);

      request = factory.createShowCollectionsRequest("podreczna_kucharyna");
      com.sendReceive(request);

      request = factory.createGetAllDocumentsRequest("podreczna_kucharyna",
          "ingredients");
      com.sendReceive(request);

      request = factory.createGetAllDocumentsRequest("podreczna_kucharyna",
          "recipes");
      com.sendReceive(request);

      Map<String, Integer> fields = new HashMap<String, Integer>();
      fields.put("_id", 1);
      fields.put("instructions", 1);

      query = new MongoQuery().fields(fields).findOne();
      request = factory.createGetDocumentsRequest("podreczna_kucharyna",
          "recipes", query);
      com.sendReceive(request);
    } catch (Exception e) {
      e.printStackTrace(System.out);
    }
  }
}
