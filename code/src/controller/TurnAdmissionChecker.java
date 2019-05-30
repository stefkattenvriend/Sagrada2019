package controller;

import databeest.DbTurnCollector;

public class TurnAdmissionChecker implements Runnable {
	boolean myTurn;
	DbTurnCollector dtc;
	String username;
	int gameId;
	
	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId) {
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
	}
	
	public void run() {
		while(true) {
			checkMyTurn();
		}
	}
	
	private void checkMyTurn() {
		if (dtc.myTurn(username, gameId)) {
			//allow something
			System.out.println("myturn");
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		} else {
			//dont allow something
			System.out.println("not my turn");
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		}
	}
	
	

}
