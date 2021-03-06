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
	
	/**
	 * Initialisation du chemin à suivre
	 * @param debut_lon
	 * @param debut_lat
	 * @param arrivee_lon
	 * @param arrivee_lat
	 * @return liste des points de l'itinéraire
	 */
	private JsonNode initPoints(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		MapBoxPath map = new MapBoxPath();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = map.requestMapBoxPath(debut_lon, debut_lat, arrivee_lon, arrivee_lat);
		JsonNode node = mapper.valueToTree(json);
		JsonNode points = node.get("routes").findValue("geometry").findValues("coordinates").get(0);
		return points;
	}
	
	/**
	 * Parcours la liste des points du chemin pour les envoyer un à un à chaque nouvelle appel
	 * @return les prochaines coordonnée de notre véhicule
	 */
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

	/**
	 * Calcul le pas de temps à attendre avant chaque envoie des nouvelles coordonnées du véhicule
	 * @return temps en millisecondes
	 */
	public int time() {
		double temps_tot = distancePoint()/ConstantCalcul.getVitessevehicule();
		double temps = temps_tot/this.points.size();
		return (int)temps*1000;
	}

	/**
	 * Calcul de la distance entre chaque points
	 * @return distance en mètre
	 */
	public int distanceBetweenPoint() {
		int distance = 0;
		if(i<this.points.size()-1) {
			Coord depart = new Coord(this.points.get(i).get(0).asDouble(), this.points.get(i).get(1).asDouble());
			Coord arrivee = new Coord(this.points.get(i+1).get(0).asDouble(), this.points.get(i+1).get(1).asDouble());
			distance = GisTools.computeDistance2(depart, arrivee);
		}
		return distance;
	}
	
	/**
	 * Calcul de la distance total
	 * @return distance en mètre entre le véhicule et l'objectif final
	 */
	public int distancePoint() {
		Coord depart = new Coord(debut_lon, debut_lat);
		Coord arrivee = new Coord(arrivee_lon, arrivee_lat);
		int distance = GisTools.computeDistance2(depart, arrivee);
		return distance;
	}
	
	/**
	 * 
	 * @return liste des points
	 */
	public JsonNode getPoints() {
		return this.points;

	}
	
}


	






