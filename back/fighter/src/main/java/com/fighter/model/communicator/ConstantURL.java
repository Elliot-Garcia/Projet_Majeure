package com.fighter.model.communicator;

public class ConstantURL {
	
	private static final String FireURL = "http://vps.cpe-sn.fr:8081/fire/";
	private static final String VehiculeURL = "http://vps.cpe-sn.fr:8081/fire/";
	private static final String FacilityURL = "http://vps.cpe-sn.fr:8081/fire/";
	
	
	public static String getFireurl() {
		return FireURL;
	}
	public static String getVehiculeurl() {
		return VehiculeURL;
	}
	public static String getFacilityurl() {
		return FacilityURL;
	}
	
}
