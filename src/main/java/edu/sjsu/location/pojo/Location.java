package edu.sjsu.location.pojo;

public class Location {
	
	private String clientID;
	private double latitude;
	private double longitude;
	private boolean isAgree;
	private String contactInfo;
	private String eventID;
	
	public Location() {
		super();
	}
	
	public Location(String clientID, double latitude, double longitude, boolean isAgree, String contactInfo,
			String eventID) {
		super();
		this.clientID = clientID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isAgree = isAgree;
		this.contactInfo = contactInfo;
		this.eventID = eventID;
	}

	public boolean isAgree() {
		return isAgree;
	}

	public void setAgree(boolean isAgree) {
		this.isAgree = isAgree;
	}

	public String getContactInfo() {
		return contactInfo;
	}
	
	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getClientID() {
		return clientID;
	}


	public void setClientID(String clientID) {
		this.clientID = clientID;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [clientID=" + clientID + ", latitude=" + latitude + ", longitude=" + longitude + ", isAgree="
				+ isAgree + ", contactInfo=" + contactInfo + ", eventID=" + eventID + "]";
	}
	
}
