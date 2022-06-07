package com.fighter.model.calcul;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.dto.Coord;
import com.project.tools.GisTools;

public class Path {
	private double debut_lon;
	private double debut_lat;
	private double arrivee_lon;
	private double arrivee_lat;
	
	private double distance_lat = 0.0;
	private double distance_lon = 0.0;	
	private int i = 0;
	
	public Path(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		this.debut_lon = debut_lon;
		this.debut_lat = debut_lat;
		this.arrivee_lon = arrivee_lon;
		this.arrivee_lat = arrivee_lat;
	}
	

	public List<Double> pathMap() {
		
		MapBoxPath map = new MapBoxPath();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = map.requestMapBoxPath(debut_lon, debut_lat, arrivee_lon, arrivee_lat);
		JsonNode node = mapper.valueToTree(json);
		JsonNode list = node.get("routes").findValue("geometry").findValues("coordinates").get(0);
		int taille = list.size();
		List<Double> newpoint = new ArrayList<Double>();		
		if(i<taille) {
			newpoint.add(list.get(i).get(0).asDouble());
			newpoint.add(list.get(i).get(1).asDouble());
			i+=1;
		}else {
			//Plus rien dans la liste à traiter, on renvoie de nouveau la derniere position
			newpoint.add(-1.0);
			newpoint.add(-1.0);
		}

		return newpoint;
	}
	
	public List<Double> pathNewPoint() {
		if(distance_lat == 0.0 && distance_lon == 0.0) {
			int distance_metre = distancePoint(); //OK
			distance_lat = arrivee_lat - debut_lat;
			distance_lon = arrivee_lon - debut_lon;
			double distance_deg = Math.sqrt(Math.pow(distance_lat,2)+Math.pow(distance_lon, 2));
			double coeff = (distance_deg/distance_metre);
		}
		
		 List<Double> newpoint = new ArrayList<Double>();
		 double chemin_lat = debut_lat + distance_lat*ConstantCalcul.getCoeffkm2coord()*13.8889;
		 double chemin_lon = debut_lon + distance_lon*ConstantCalcul.getCoeffkm2coord()*13.8889;
	
		//TODO: faire avec la range des feux
		 if((chemin_lat >= arrivee_lat-ConstantCalcul.getCoeffkm2coord()*50 && chemin_lat <= arrivee_lat+ConstantCalcul.getCoeffkm2coord()*50))  {
			newpoint.add(this.debut_lat);
		 	newpoint.add(this.debut_lon);

			setDebut_lat(debut_lat);
		 	setDebut_lon(debut_lon);
		}
		if((chemin_lon >= arrivee_lon-ConstantCalcul.getCoeffkm2coord()*50 && chemin_lon <= arrivee_lon+ConstantCalcul.getCoeffkm2coord()*50)) {
		 	newpoint.add(this.debut_lon);
			newpoint.add(this.debut_lat);

		 	setDebut_lon(debut_lon);
		 	setDebut_lat(debut_lat);
		}
	 	else {
	 		 newpoint.add(chemin_lon);
			 newpoint.add(chemin_lat);
			 setDebut_lon(chemin_lon);
			 setDebut_lat(chemin_lat);

	 	}
		 
		return newpoint;
	}
	
	public int time() {
		double temps_tot = distancePoint()/ConstantCalcul.getVitessevehicule();
		MapBoxPath map = new MapBoxPath();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = map.requestMapBoxPath(debut_lon, debut_lat, arrivee_lon, arrivee_lat);
		JsonNode node = mapper.valueToTree(json);
		JsonNode list = node.get("routes").findValue("geometry").findValues("coordinates").get(0);
		double temps = temps_tot/list.size();
		return (int)temps*1000;
	}
	
	private void setDebut_lon(double debut_lon) {
		this.debut_lon = debut_lon;
	}

	private void setDebut_lat(double debut_lat) {
		this.debut_lat = debut_lat;
	}

	public int distancePoint() {
		Coord depart = new Coord(debut_lon, debut_lat);
		Coord arrivee = new Coord(arrivee_lon, arrivee_lat);
		int distance = GisTools.computeDistance2(depart, arrivee);
		return distance;
	}
	
	public static void main(String[] args) throws InterruptedException  {
		Path p = new Path(4.792258384694939,45.721839937555565,4.784500833959483,45.760286520753304);
		p.pathNewPoint();
		p.pathMap();
		p.time();
/**
		while(true) {
			TimeUnit.MILLISECONDS.sleep(1);
			p.pathMap();
		}
*/

	}
	
}


	






