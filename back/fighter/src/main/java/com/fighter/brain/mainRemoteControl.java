package com.fighter.brain;

import java.util.concurrent.TimeUnit;

public class mainRemoteControl {

	public static void main(String[] args) {
		remoteControl ctr = new remoteControl();
		
		
		
		while(true) {
			
			ctr.compare();
			System.out.println(ctr);
			
			try {
			    Thread.sleep(750);
			} catch(InterruptedException e) {
			    System.out.println("got interrupted!");
			}
		}
	}

}
