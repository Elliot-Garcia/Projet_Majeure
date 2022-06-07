package com.fighter;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fighter.brain.RemoteControl;
import com.fighter.brain.mainRemoteControl;
import com.fighter.brain.mission.LogMission.LogMission;
import com.fighter.model.communicator.Requester;

@SpringBootApplication
public class FighterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FighterApplication.class, args);
		
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
