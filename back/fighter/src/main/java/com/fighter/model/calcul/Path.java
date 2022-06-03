package com.fighter.model.calcul;

import java.util.ArrayList;
import java.util.List;

import com.project.model.dto.Coord;
import com.project.tools.GisTools;

public class Path {
	private double debut_lon;
	private double debut_lat;
	private double arrivee_lon;
	private double arrivee_lat;
	
	private double distance_lat = 0.0;
	private double distance_lon = 0.0;	
	
	public Path(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		this.debut_lon = debut_lon;
		this.debut_lat = debut_lat;
		this.arrivee_lon = arrivee_lon;
		this.arrivee_lat = arrivee_lat;
	}
	

	
	public List<Double> pathNewPoint() {
		if(distance_lat == 0.0 && distance_lon == 0.0) {
			int distance_metre = distancePoint();
			distance_lat = arrivee_lat - debut_lat;
			distance_lon = arrivee_lon - debut_lon;
			double distance_deg = Math.sqrt(Math.pow(distance_lat,2)+Math.pow(distance_lon, 2));
			System.out.println(distance_deg);
			System.out.println(distance_metre);
			double coeff = (distance_deg/distance_metre);
			System.out.println("coef" + coeff);
		}
		
		 List<Double> newpoint = new ArrayList<Double>();
		 System.out.println("Longi_deb = "+this.debut_lon+", Lat_deb = "+ this.debut_lat+", arrive_long= " + this.arrivee_lon + ", arriver_lat= "+this.arrivee_lat);
		 double chemin_lat = debut_lat + distance_lat*ConstantCalcul.getCoeffkm2coord()*13.8889;
		 double chemin_lon = debut_lon + distance_lon*ConstantCalcul.getCoeffkm2coord()*13.8889;
		 
		 	System.out.println(chemin_lat);

		 	System.out.println(chemin_lon);

		 if((chemin_lat >= arrivee_lat-0.01 && chemin_lat <= arrivee_lat+0.01) && (chemin_lon >= arrivee_lon-0.01 && chemin_lat <= arrivee_lon+0.01)) {
			newpoint.add(debut_lon);
		 	newpoint.add(debut_lat);
		 	System.out.println("dans le feu");
		}
	 	else {
	 		 newpoint.add(chemin_lon);
			 newpoint.add(chemin_lat);
			 System.out.println("go au feu");

	 	}
		
		 setDebut_lon(chemin_lon);
		 setDebut_lat(chemin_lat);
		 
		System.out.println("newpoint= "+ newpoint);
		return newpoint;
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
		System.out.println(distance);
		return distance;
	}
	
	public static void main(String[] args)  {
		Path p = new Path(5.0,46.0,4.0,45.0);
		
		while(true) {
			
			p.pathNewPoint();
		}

	}
	
}


