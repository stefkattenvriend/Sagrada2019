package model;

import databeest.DbGameCollector;
import databeest.DbPlayerCollector;
import helpers.DiceHolderType;

public class GameModel {
	
	//instance 
	private int gameRound = 0; //nu hardcoded, in toekomst wordt dit uit database gehaald.
	private int gameid;
	private boolean inGame;
	private int amountOfPlayers;
	private DbGameCollector dgc;
	private DbPlayerCollector dpc;
	private PlayerModel[] pma;
	
	public GameModel(int gameid, DbGameCollector dgc, String username, DbPlayerCollector dpc) {
		this.gameid = gameid;
		this.dgc = dgc;
		this.dpc = dpc;
		this.amountOfPlayers = amountOfPlayers;
		inGame = true;
		pma = new PlayerModel[4];
//		addPlayerModelP1(username);
		//TODO add enemies
		
	}
	
	public void leaveGame() {
		inGame = false;
	}
	
	public int getGameRound() {
		return gameRound;
	}

	public void updateRound() {
		dgc.getRoundNumber(gameid);
	}
	
//	public void addPlayerModelP1(String username) {
//		pma[0] = new PlayerModel();
//		pma[0].setDht(DiceHolderType.PLAYERWINDOW);
//		pma[0].setUsername(username);
//		pma[0].setGameid(gameid);
//		pma[0].getDatabaseInfo(dpc);
//	}
	
	public void addPlayer(int i, String username)
	{
		pma[i] = new PlayerModel();
		pma[i].setDht(DiceHolderType.PLAYERWINDOW);
		pma[i].setUsername(username);
		pma[i].setGameid(gameid);
		pma[i].getDatabaseInfo(dpc);
	}
	
}
