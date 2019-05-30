package controller;

import databeest.DbTurnCollector;
import view.GamePanes.PlayerPane;

public class TurnAdmissionChecker implements Runnable {

	private boolean myTurn;
	private DbTurnCollector dtc;
	private String username;
	private int gameId;
	private DiceHolderController dhc;
	private PlayerPane pp;
	
	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId, DiceHolderController dhc, PlayerPane pp) {
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
		this.dhc = dhc;
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
			dhc.switchTurnInteractable(true);
			
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		} else {
			//dont allow something
			System.out.println("not my turn");
			dhc.switchTurnInteractable(false);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("TurnAdmissionController checkMyTurn sleep error");
				e.printStackTrace();
			}
		}
	}
	
	

}
