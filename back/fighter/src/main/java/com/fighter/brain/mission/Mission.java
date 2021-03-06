package com.fighter.brain.mission;

import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.calcul.Path;
import com.fighter.model.communicator.Requester;
import com.fighter.model.dto.FireDto;
import com.fighter.model.dto.VehiculeDto;
import com.fighter.model.modelVehicule.InterfaceVehicule;

public abstract class Mission implements Runnable {

	protected InterfaceVehicule vehicule;
	private Path chemin;
	private int deltaTemps;
	
	public Mission(InterfaceVehicule v, double lonDestination, double latDestination) {
		this.vehicule = v;
		this.chemin = initPath(lonDestination, latDestination);
		this.deltaTemps = chemin.time();
	}
	
	protected abstract boolean missionContinue();
	protected abstract void missionAction(boolean deplacement);
	
	/**
	 * Setup the begining of the mission
	 */
	private void missionStart() {
		this.vehicule.missionTrue();
		LogMission.debutMissionPath( vehicule.getVehiculeDto(), chemin);
	}
	
	/**
	 * Setup the end of the mission
	 */
	private void missionEnd() {
		this.vehicule.missionFalse();
		LogMission.finMissionPath( vehicule.getVehiculeDto());
	}
	
	/**
	 * Init the path to the target
	 * @param lonDestination
	 * @param latDestination
	 * @return the path
	 */
	private Path initPath(double lonDestination, double latDestination) {
		Path chemin = new Path(vehicule.getVehiculeDto().getLon(),
				vehicule.getVehiculeDto().getLat(),
				lonDestination,
				latDestination);
		return chemin;
	}
	
	/**
	 * Move to the target following the path
	 * @param deplacement
	 * @return true : done
	 */
	private boolean missionDeplacement(boolean deplacement) {
		boolean res = false;
		if (deplacement) {
			double newLon = this.chemin.pathMap().get(0);
			double newLat = this.chemin.pathMap().get(1);
			if (newLon >= 0 && newLat >= 0) {
				this.vehicule.deplacement(newLon, newLat, (float) (chemin.distanceBetweenPoint()/1000.0));
				res = true;
			}
		}
		return res;
	}

	/**
	 * main methode while the thread is launched
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("[ Thread ] " + "-- Debut Mission -- " + this.vehicule.getVehiculeDto().getId());
		this.missionStart();
		boolean deplacement = true;
		
		while ( this.missionContinue() )  {

			try {
				deplacement = this.missionDeplacement(deplacement);
				this.missionAction(deplacement);
			    Thread.sleep(this.deltaTemps/4);
			} catch(InterruptedException e) {
			    System.out.println("got interrupted!");
			}
		}
		
		this.missionEnd();
		System.out.println("[ Thread ] " + "-- Fin Mission -- " + this.vehicule.getVehiculeDto().getId());
	}
	
}
