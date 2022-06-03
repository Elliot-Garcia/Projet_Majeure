package com.fighter.model.calcul;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private double debut_lon;
	private double debut_lat;

	private double arrivee_lon;
	private double arrivee_lat;

	public Path(double debut_lon, double debut_lat, double arrivee_lon, double arrivee_lat) {
		this.debut_lon = debut_lon;
		this.debut_lat = debut_lat;
		this.arrivee_lon = arrivee_lon;
		this.arrivee_lat = arrivee_lat;
	}
	
	public List<Double> pathNewPoint() {
		 List<Double> newpoint = new ArrayList<Double>();
		 System.out.println("Longi_deb = "+this.debut_lon+", Lat_deb = "+ this.debut_lat+", arrive_long= " + this.arrivee_lon + ", arriver_lat= "+this.arrivee_lat);
		 double chemin_lat = debut_lat + (arrivee_lat - debut_lat)*ConstantCalcul.coeffKm2Coord;
		 double chemin_lon = debut_lon + (arrivee_lon - debut_lon)*ConstantCalcul.coeffKm2Coord;
		 
		 newpoint.add(chemin_lon);
		 newpoint.add(chemin_lat);
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

	
	public static void main(String[] args) {
		Path p = new Path(48.0,4.0, 47.0, 5.0);
		p.pathNewPoint();
		p.pathNewPoint();
		p.pathNewPoint();

	}
	
}


