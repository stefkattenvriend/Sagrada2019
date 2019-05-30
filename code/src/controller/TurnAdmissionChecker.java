package controller;

import databeest.DbTurnCollector;

public class TurnAdmissionChecker implements Runnable {
	private boolean myTurn;
	private DbTurnCollector dtc;
	private String username;
	private int gameId;
	private DiceHolderController dhc;
	
	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId, DiceHolderController dhc) {
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
		this.dhc = dhc;
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
			dhc.switchTurnInteractable(true);
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		} else {
			//dont allow something
			System.out.println("not my turn");
			dhc.switchTurnInteractable(false);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		}
	}
	
	

}
