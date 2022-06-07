package com.fighter.model.communicator;

public class ConstantURL {
	
	private static final String FireURL = "http://vps.cpe-sn.fr:8081/fire/";
	private static final String VehiculeURL = "http://vps.cpe-sn.fr:8081/vehicle/";
	private static final String FacilityURL = "http://vps.cpe-sn.fr:8081/facility/";

	public static final String teamCode = "38621c5b-ce36-4c3d-b36b-31a2fcbea44d";
	
	
	
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
