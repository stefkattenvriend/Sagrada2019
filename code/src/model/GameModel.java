package model;

import databeest.DbGameCollector;

public class GameModel {
	
	//instance 
	private int gameRound = 0; //nu hardcoded, in toekomst wordt dit uit database gehaald.
	private int gameid;
	private boolean inGame;
	private DbGameCollector dgc;
	
	public GameModel(int gameid, DbGameCollector dgc) {
		this.gameid = gameid;
		this.dgc = dgc;
		inGame = true;
	}
	
	public void leaveGame() {
		inGame = false;
	}
	
	public int getGameRound() {
		return gameRound;
	}

	public void updateRound() {
		System.out.println("gameID: " + gameid);
		dgc.getRoundNumber(gameid);
		
	}
}
