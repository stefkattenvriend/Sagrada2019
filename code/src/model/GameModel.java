package model;

import controller.GameController;
import controller.ToolCardController;
import databeest.DbGameCollector;
import databeest.DbPlayerCollector;
import helpers.DiceHolderType;
import javafx.scene.paint.Color;

public class GameModel {

	// instance
	private int gameRound;
	private int gameid;
	private boolean inGame;
	private int amountOfPlayers;
	private DbGameCollector dgc;
	private DbPlayerCollector dpc;
	private PlayerModel[] pma;
	private GameController gc;
	private int enemies = 1;
	private ToolCardController tcc;

	public GameModel(int gameid, DbGameCollector dgc, String username, DbPlayerCollector dpc, int amountOfPlayers,
			ToolCardController tcc, GameController gc) {
		this.gameid = gameid;
		this.dgc = dgc;
		this.dpc = dpc;
		this.amountOfPlayers = amountOfPlayers;
		this.tcc = tcc;
		this.gc = gc;
		inGame = true;
		pma = new PlayerModel[amountOfPlayers];

	}

	public void leaveGame() {
		inGame = false;
	}

	public int getGameRound() {
		return gameRound;
	}

	public void updateRound(GameController gc) {

		int newRound = dgc.getRoundNumber(gameid);

		if (gameRound < newRound) {
			gameRound = newRound;
			gc.updateNewRound(true);
			// gc.updateRoundtrack(newRound - 1);
		}

	}

	public PlayerModel[] getPma() {
		return pma;
	}

	public void addPlayer(int i, int playerID, String username) {
		// System.out.println("i=" + i);
		// System.out.println("playerId" + playerID);
		pma[i] = new PlayerModel(dpc, this, tcc, gc);
		pma[i].setGameid(gameid);
		pma[i].setPlayerId(playerID);
		pma[i].getDatabaseInfo(dpc);

		if (pma[i].getUsername().equals(username)) { // username is gelijk
			pma[i].setDht(DiceHolderType.PLAYERWINDOW);
			pma[i].setPlaceInArrayList(i);
		} else if (enemies == 1) {
			pma[i].setDht(DiceHolderType.ENEMY1);
			enemies++;
		} else if (enemies == 2) {
			pma[i].setDht(DiceHolderType.ENEMY2);
			enemies++;
		} else if (enemies == 3) {
			pma[i].setDht(DiceHolderType.ENEMY3);
		} else {
			System.out.println("kan niet meer dan ");
		}
	}

	public PlayerModel getPlayerModel(DiceHolderType type) {
		PlayerModel pm = null;
		for (int i = 0; i < pma.length; i++) {
			if (pma[i].getDht() == type) {
				pm = pma[i];
				return pm;
			}
		}

		return pm;
	}

	public void addPlayerModel(String username) {
		pma[0] = new PlayerModel(dpc, this, tcc, gc);
		pma[0].setDht(DiceHolderType.PLAYERWINDOW);
		pma[0].setUsername(username);
		pma[0].setGameid(gameid);
		pma[0].getDatabaseInfo(dpc);
	}

	public void addDiceModels() {
		for (int i = 1; i < 91; i++) {

		}
	}

	public int getAmountOfPlayers() {
		return amountOfPlayers;
	}

	public int getGameId() {
		return gameid;
	}

	public Color getMyColor() {
		Color myColor = pma[0].getObjectiveColor();
		return myColor;
	}

	public void updatePCa(int i) {
		pma[0].setPatid(i);

	}

	public void updateEnemyPCid() {
		for (int i = 0; i < pma.length; i++) {
			if (pma[i].getPatid() == 0) {
				pma[i].reloadPcID();
			}
		}
	}

	public int getPcID(int i) {
		return pma[i].getPatid();
	}

	public int getPcIdMainPlayer(String username) {// krijgt de pcid voor de speler die het programma draait
		for (int i = 0; i < pma.length; i++) {
			if (pma[i].getUsername().equals(username)) { // username is gelijk
				return pma[i].getPatid();
			}
		}
		return 0;
	}

	public void setCurPlayer(boolean b) {

		for (int i = 0; i < pma.length; i++) {
			if (pma[i].getDht() == DiceHolderType.PLAYERWINDOW) {
				pma[i].setCurrentPlayer(b);
			}
		}

	}
}
