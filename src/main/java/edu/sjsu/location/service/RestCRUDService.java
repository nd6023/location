package edu.sjsu.location.service;


import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.sjsu.location.pojo.Location;


@Path("/rest")
public class RestCRUDService {
	
	ArrayList<Location> locMap = new ArrayList<Location>();
	//HashMap<String, Location> locMap = new HashMap<String, Location>();

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(Location location ) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017 collisionPrevention
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("54.174.9.45", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("event");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("clientLocation");
		
		
		
		// create a row (document) in collection based on the object sent from
		// post call
		BasicDBObject document = new BasicDBObject();
		document.put("clientID", location.getClientID());
		document.put("latitude", location.getLatitude());
		document.put("longitude", location.getLongitude());
		
		table.insert(document);

		
		
		// searching the DB to get the document for the given client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("clientID", location.getClientID());
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String detailsObj = nextDocument.toString();
			result += detailsObj + "\n";
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// sending the registered client information to the client
		return Response.status(201).entity(result).build();
	}

	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Location location) {

		String result = "";
		
		
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("54.174.9.45", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("event");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("clientLocation");
		
		
		BasicDBObject updatedClient = new BasicDBObject();
		updatedClient.put("clientID", location.getClientID());
		updatedClient.put("latitude", location.getLatitude());
		updatedClient.put("longitude", location.getLongitude());
		BasicDBObject updateQuery = new BasicDBObject().append("clientID", location.getClientID());

		table.update(updateQuery, updatedClient);

		// searching the DB to retrieve the updated details of the client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("clientID", location.getClientID());
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String details = nextDocument.toString();
			result += details + "\n";
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sending the updated records back to the client
		return Response.status(201).entity(result).build();
	}

	// ND ++ get post
	@GET
	@Path("/going/{clientID}/{eventID}/{latitude}/{longitude}/{isAgree}/{contactInfo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getRegisterPost(@PathParam("clientID") String clientID, @PathParam("eventID") String eventID, @PathParam("latitude") String latitude, @PathParam("longitude") String longitude, @PathParam("isAgree") String isAgree, @PathParam("contactInfo") String contactInfo) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("54.174.9.45", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("event");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("clientLocation");
		
		
		
		// create a row (document) in collection based on the object sent from
		// post call
		BasicDBObject document = new BasicDBObject();
		document.put("clientID", clientID);
		document.put("eventID", eventID);
		document.put("latitude", Double.parseDouble(latitude));
		document.put("longitude", Double.parseDouble(longitude));
		document.put("isAgree", Boolean.parseBoolean(isAgree));
		document.put("contactInfo", contactInfo);
		
		table.insert(document);
		System.out.println("Person will be going ====>"+clientID);
		
		// searching the DB to get the document for the given client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("clientID", clientID);
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String detailsObj = nextDocument.toString();
			result += detailsObj + "\n";
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		String response ="";
		
			
			response = "callbackMethod ( {\"success\" : \"true\"});";
		
		
		// sending the registered client information to the client ObjectMapper
		return response;
	}

	// ND -- get post
	
	
	// ND ++ get put
	@GET
	@Path("/share/{clientID}/{eventID}/{latitude}/{longitude}/{isAgree}/{contactInfo}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getRegister(@PathParam("clientID") String clientID, @PathParam("eventID") String eventID, @PathParam("latitude") String latitude, @PathParam("longitude") String longitude, @PathParam("isAgree") String isAgree, @PathParam("contactInfo") String contactInfo) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("54.174.9.45", 27017);
		
		@SuppressWarnings("deprecation")
		DB db = mongoClient.getDB("event");
		// gets collection user..similar to table
		DBCollection table = db.getCollection("clientLocation");
		
		System.out.println("Person interested in sharing==>"+clientID);
		
		// create a row (document) in collection based on the object sent from
		// post call
		BasicDBObject document = new BasicDBObject();
		document.put("clientID", clientID);
		document.put("eventID", eventID);
		document.put("latitude", Double.parseDouble(latitude));
		document.put("longitude", Double.parseDouble(longitude));
		document.put("isAgree", Boolean.parseBoolean(isAgree));
		document.put("contactInfo", contactInfo);
		
		
		BasicDBObject updateQuery = new BasicDBObject().append("clientID", clientID);

		table.update(updateQuery, document);
		
		
		// searching the DB to get the document for the given client
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("clientID", clientID);
		DBCursor cursor = table.find(searchQuery);
		while (cursor.hasNext()) {
			DBObject nextDocument = cursor.next();
			String detailsObj = nextDocument.toString();
			result += detailsObj + "\n";
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		String response ="";
		
			
			response = "callbackMethod ( {\"success\" : \"true\"});";
		
		
		// sending the registered client information to the client ObjectMapper
		return response;
	}

	// ND -- get put
	
	@GET
	@Path("/get/{clientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response read(@PathParam("clientID") String clientID) {
		String result = "";
		
			// Creating serverside mongoDB which is listening on port 27017
				MongoClient mongoClient;
				try {
					mongoClient = new MongoClient("54.174.9.45", 27017);
				
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("event");
				// gets collection user..similar to table
				DBCollection table = db.getCollection("clientLocation");
				
				// searching the DB to retrieve the updated details of the client
				BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put("clientID", clientID);
				DBCursor cursor = table.find(searchQuery);
				while (cursor.hasNext()) {
					DBObject nextDocument = cursor.next();
					String details = nextDocument.toString();
					result += details + "\n";
					
				}
				
				
				
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
		// sending the updated records back to the client
		return Response.status(201).entity(result).build();
		
	}
	
	// Get all
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readAll() {
		String result = "";
		
			// Creating serverside mongoDB which is listening on port 27017
				MongoClient mongoClient;
				try {
					mongoClient = new MongoClient("54.174.9.45", 27017);
				
				@SuppressWarnings("deprecation")
				DB db = mongoClient.getDB("event");
				// gets collection user..similar to table
				DBCollection table = db.getCollection("clientLocation");
				
				// searching the DB to retrieve the updated details of the client
				BasicDBObject searchQuery = new BasicDBObject();
				//searchQuery.put("registrationID", registrationID);
				DBCursor cursor = table.find();
				Location loc= null;
				while (cursor.hasNext()) {
					DBObject nextDocument = cursor.next();
					String details = nextDocument.toString();
					result += details + "\n";
					loc= new Location();
					/*loc.setRegistrationID((String) nextDocument.get("registrationID"));
					loc.setLatitude((Double) nextDocument.get("latitude"));
					loc.setLongitude((Double) nextDocument.get("longitude"));
					loc.setAltitude((Double) nextDocument.get("altitude"));
					loc.setDirection((String) nextDocument.get("direction"));
					loc.setSpeed((Double) nextDocument.get("speed"));
					locMap.put((String) nextDocument.get("registrationID"), loc);
					loc = null;
					*/
//					Object reg1 = nextDocument.get("registrationID");
//					Object reg2 = nextDocument.get("latitude");
//					Object reg3 = nextDocument.get("longitude");
//					System.out.println(reg1.toString());
//					System.out.println("Value of 2 is: "+reg2.toString());
//					System.out.println("Value of 3 is: "+reg3.toString());
				}
				/*Location curLoc = new Location("99",99,99,99,"N",99,"99");
				System.out.println("Is it safe to make a move? " + isSafe(curLoc, locMap));*/
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		// sending the updated records back to the client
		return Response.status(201).entity(result).build();
		
	}
	
	// Get all nearby
		@GET
		@Path("/get/nearby/{clientID}/{eventID}/{latitude}/{longitude}/{isAgree}/{contactInfo}")
		@Produces(MediaType.APPLICATION_JSON)
		public String readNearby(@PathParam("clientID") String clientID, @PathParam("eventID") String eventID, @PathParam("latitude") String latitude,@PathParam("longitude") String longitude, @PathParam("isAgree") String isAgree, @PathParam("contactInfo") String contactInfo) {
			String result = "";
			boolean isAgr = Boolean.parseBoolean(isAgree);
			
				// Creating serverside mongoDB which is listening on port 27017
					MongoClient mongoClient;
					double latit = Double.parseDouble(latitude);
					double longi = Double.parseDouble(longitude);
					//String response ="";
					//String response1 ="";
					try {
						mongoClient = new MongoClient("54.174.9.45", 27017);
					
					@SuppressWarnings("deprecation")
					DB db = mongoClient.getDB("event");
					// gets collection user..similar to table
					DBCollection table = db.getCollection("clientLocation");
					
					// searching the DB to retrieve the updated details of the client
					BasicDBObject searchQuery = new BasicDBObject();
					//searchQuery.put("registrationID", registrationID);
					DBCursor cursor = table.find();
					Location loc= null;
					String clientID1 = "";
					String eventID1 = "";
					double latitude1 = 0;
					double longitude1 = 0;
					boolean isAgree1 = false;
					String contactInfo1 ="";
					String out = "";
					while (cursor.hasNext()) {
						DBObject nextDocument = cursor.next();
						String details = nextDocument.toString();
						result += details + "\n";
						clientID1 = (String) nextDocument.get("clientID");
						eventID1 = (String) nextDocument.get("eventID");
						latitude1 = (Double)nextDocument.get("latitude");
						longitude1 = (Double)nextDocument.get("longitude");
						//latitude1 = 4;
						//longitude1 = 4;
						isAgree1 = (Boolean)nextDocument.get("isAgree");
						contactInfo1 = (String) nextDocument.get("contactInfo");
						
						if (isNear(latit, longi,latitude1, longitude1)){
							loc= new Location();
							loc.setClientID(clientID1);
							loc.setEventID(eventID1);
							loc.setLatitude(latitude1);
							loc.setLongitude(longitude1);
							loc.setAgree(isAgree1);
							loc.setContactInfo(contactInfo1);
							locMap.add(loc);
							loc = null;
						}
						
//						Object reg1 = nextDocument.get("registrationID");
//						Object reg2 = nextDocument.get("latitude");
//						Object reg3 = nextDocument.get("longitude");
//						System.out.println(reg1.toString());
//						System.out.println("Value of 2 is: "+reg2.toString());
//						System.out.println("Value of 3 is: "+reg3.toString());
					}
					//Location curLoc = new Location("99",99,99,99,"N",99,"99");
					//System.out.println("Is it safe to make a move? " + isSafe(curLoc, locMap));
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					ObjectMapper mapper = new ObjectMapper();
					//mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
					String response ="";
					
						try {
//							mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
//						    mapper.setSerializationInclusion(Include.NON_NULL);
//						    mapper.getSerializerProvider().setNullKeySerializer(new MyNullKeySerializer());
							response = "callbackMethod (" +mapper.writeValueAsString(locMap.toArray(new Location[locMap.size()]))  + ");";
						} catch (JsonGenerationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JsonMappingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//response = "callbackMethod ( {\"success\" : \"true\"});";
					
					
					// sending the registered client information to the client ObjectMapper
					return response;

					//result = "callbackMethod ( {\"success\" : \"true\"});";
					
					
			// sending the updated records back to the client
			//return Response.status(201).entity(result).build();
					
			
			//return response;
		}
	
	@DELETE
	@Path("/delete/{clientID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deRegister(@PathParam("clientID") String clientID) {
		
		String result = "";
		
		// Creating serverside mongoDB which is listening on port 27017
			MongoClient mongoClient;
			try {
				mongoClient = new MongoClient("54.174.9.45", 27017);
			
			@SuppressWarnings("deprecation")
			DB db = mongoClient.getDB("event");
			// gets collection user..similar to table
			DBCollection table = db.getCollection("clientLocation");
		BasicDBObject deleteQuery = new BasicDBObject().append("clientID", clientID);

		table.remove(deleteQuery);
		
		// sending the response of deRegister function to the client
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		
		return Response.status(201).entity(result).build();
	}
	
	// ND + Distance calculator
	/*
	public static boolean isSafe(Location currentLoc, Map<String, Location> locMap) {
		double safeDistance=6;
		boolean status=true;
		
		
		for(String iterLocation: locMap.keySet()){
			
			if(!iterLocation.equals(currentLoc.getRegistrationID())){
				double dis = distance(currentLoc.getLatitude(), currentLoc.getLongitude(),locMap.get(iterLocation).getLatitude() , locMap.get(iterLocation).getLatitude());
				System.out.println(dis);
				if(dis>safeDistance && dis > 1){
					status = true;
				}
				else {
					status = false;
					return status;
				}
			}
			
			
		}
		
		return status;
		
		
	}*/
	
	public boolean isNear(double lat1, double lon1, double lat2, double lon2) {
		if((distance(lat1, lon1, lat2, lon2))<10) {
			return true;
		}
		return false;
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}
	
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	// ND -- Distance
}

