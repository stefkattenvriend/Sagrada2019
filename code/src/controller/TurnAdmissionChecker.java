package controller;

import databeest.DbTurnCollector;
import view.GamePanes.PlayerPane;

public class TurnAdmissionChecker implements Runnable {
	boolean myTurn;
	DbTurnCollector dtc;
	String username;
	int gameId;
	private PlayerPane pp;
	
	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId, PlayerPane pp) {
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
		this.pp = pp;
	}
	
	public void run() {
		while(true) {
			checkMyTurn();
		}
	}
	
	private void checkMyTurn() {
		if (dtc.myTurn(username, gameId)) {
			pp.yourTurn();
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
