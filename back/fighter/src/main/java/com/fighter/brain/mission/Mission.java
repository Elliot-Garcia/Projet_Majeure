package com.fighter.brain.mission;

import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public abstract class Mission implements InterfaceMission, Runnable {

	private InterfaceVehicule vehicule;
	private Path chemin;
	private int deltaTemps;
	
	public Mission(InterfaceVehicule v, double lonDestination, double latDestination) {
		this.vehicule = v;
		this.chemin = initPath(lonDestination, latDestination);
		this.deltaTemps = chemin.time();
	}
	
	protected abstract boolean missionContinue();
	
	private void missionStart() {
		this.vehicule.missionTrue();
	}
	
	private void missionEnd() {
		this.vehicule.missionFalse();
	}
	
	private Path initPath(double lonDestination, double latDestination) {
		Path chemin = new Path(vehicule.getVehiculeDto().getLon(),
				vehicule.getVehiculeDto().getLat(),
				lonDestination,
				latDestination);
		return chemin;
	}
	
	private void missionDeplacement() {
		double newLon = this.chemin.pathMap().get(0);
		double newLat = this.chemin.pathMap().get(1);
		this.vehicule.deplacement(newLon, newLat);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("[ Thread ]" + "-- Debut Mission --" + this.vehicule.getVehiculeDto());
		System.out.println("[ Thread ]" + "-- Debut Mission --" + this.vehicule);
		this.missionStart();
		
		while ( this.missionContinue() )  {
			System.out.println("[Thread]-- Turn()");
			
			try {
				this.missionDeplacement();
			    Thread.sleep(750);
			} catch(InterruptedException e) {
			    System.out.println("got interrupted!");
			}
		}
		
		this.missionEnd();
		System.out.println("[ Thread ]" + "-- Fin Mission --" + this.vehicule);
	}
	
}
