package com.fighter.brain;

import java.util.Arrays;

import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.communicator.Requester;

public class mainRemoteControl {

	public static void main(String[] args) {
		RemoteControl ctr = new RemoteControl();
		
		LogMission.initOwnVehicle( Arrays.asList( Requester.requestVehicule() ) );
				
		while(true) {
			
			ctr.compare();
			//System.out.println(ctr);
			
			//System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
		
			try {
			    Thread.sleep(750);
			} catch(InterruptedException e) {
			    System.out.println("got interrupted!");
			}
		}
	}

}
