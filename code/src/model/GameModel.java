package model;

import controller.DiceController;
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
	private DiceController dc;
	private DiceModel[] dm;
	
	public GameModel(int gameid, DbGameCollector dgc, String username, DbPlayerCollector dpc, int amountOfPlayers) {
		this.gameid = gameid;
		this.dgc = dgc;
		this.dpc = dpc;
		this.amountOfPlayers = amountOfPlayers;
		inGame = true;
		pma = new PlayerModel[amountOfPlayers];
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
		pma[i-1] = new PlayerModel();
		pma[i-1].setDht(DiceHolderType.PLAYERWINDOW);
		pma[i-1].setUsername(username);
		pma[i-1].setGameid(gameid);
		pma[i-1].getDatabaseInfo(dpc);

	}
	
	public PlayerModel getPlayerModel(DiceHolderType type) {
		PlayerModel pm = null;
		for (int i = 0; i < pma.length; i++) {
			if(pma[i].getDht() == type) {
				pm = pma[i];
				return pm;
			}
		}
		
		return pm;
	}
	
	public int getGameId() {
		return gameid;
	}
	
	public void addPlayerModelP1(String username) {
		pma[0] = new PlayerModel();
		pma[0].setDht(DiceHolderType.PLAYERWINDOW);
		pma[0].setUsername(username);
		pma[0].setGameid(gameid);
		pma[0].getDatabaseInfo(dpc);
	}
	
	public void addDiceModels() {
		for (int i = 1; i < 91; i++) {
			
		}
	}
}
