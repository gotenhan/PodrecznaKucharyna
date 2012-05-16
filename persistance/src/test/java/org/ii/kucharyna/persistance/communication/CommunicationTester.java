package org.ii.kucharyna.persistance.communication;

public class CommunicationTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
		MongoLabCommunicator com = new MongoLabCommunicator();
		MongoRequest request = MongoRequestFactory.createShowDatabasesRequest();
		String response = com.sendReceive(request);
		System.out.println(response);
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
}
