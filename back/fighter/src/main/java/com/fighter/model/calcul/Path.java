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
	private JsonNode points;
	
	public Path(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		this.debut_lon = debut_lon;
		this.debut_lat = debut_lat;
		this.arrivee_lon = arrivee_lon;
		this.arrivee_lat = arrivee_lat;
		this.points = initPoints(debut_lon, debut_lat,arrivee_lon,arrivee_lat);
	}
	
	private JsonNode initPoints(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		MapBoxPath map = new MapBoxPath();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = map.requestMapBoxPath(debut_lon, debut_lat, arrivee_lon, arrivee_lat);
		JsonNode node = mapper.valueToTree(json);
		return node.get("routes").findValue("geometry").findValues("coordinates").get(0);
	}
	
	public List<Double> pathMap() {
		int taille = this.points.size();
		List<Double> newpoint = new ArrayList<Double>();		
		if(i<taille) {
			newpoint.add(this.points.get(i).get(0).asDouble());
			newpoint.add(this.points.get(i).get(1).asDouble());
			i+=1;
		}else if(i==taille) {
			newpoint.add(this.arrivee_lon);
			newpoint.add(this.arrivee_lat);
			i+=1;
		}
		else {
			//Plus rien dans la liste à traiter, on renvoie de nouveau la derniere position
			newpoint.add(-1.0);
			newpoint.add(-1.0);
		}

		return newpoint;
	}

	
	public int time() {
		double temps_tot = distancePoint()/ConstantCalcul.getVitessevehicule();
		double temps = temps_tot/this.points.size();
		return (int)temps*1000;
	}

	public int distanceBetweenPoint() {
		int distance = 0;
		if(i<this.points.size()-1) {
			Coord depart = new Coord(this.points.get(i).get(0).asDouble(), this.points.get(i).get(1).asDouble());
			Coord arrivee = new Coord(this.points.get(i+1).get(0).asDouble(), this.points.get(i+1).get(1).asDouble());
			distance = GisTools.computeDistance2(depart, arrivee);
		}
		return distance;
	}
	
	public int distancePoint() {
		Coord depart = new Coord(debut_lon, debut_lat);
		Coord arrivee = new Coord(arrivee_lon, arrivee_lat);
		int distance = GisTools.computeDistance2(depart, arrivee);
		return distance;
	}
	
	public static void main(String[] args) throws InterruptedException  {
		Path p = new Path(4.792258384694939,45.721839937555565,4.784500833959483,45.760286520753304);
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


	






