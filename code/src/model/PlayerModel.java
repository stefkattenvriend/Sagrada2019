package model;

import databeest.DbPlayerCollector;
import helpers.DiceHolderType;
import javafx.scene.paint.Color;

public class PlayerModel {

	// instance variables

	private int playerid;
	private String username;
	private int seqnr; // dit is later voor de volgorde van de beurtenprivate String username;
	private int gameid;
	private String status;
	private boolean isCurrentPlayer;
	private int payStones;// moeten wss paystone models worden ~ Rens
	private int patid; // patterncard_idpatterncard
	private String stringcolor;
	private Color color;
	private int score;

	private DiceHolderType dht;// welke diceholder er bij deze speler hoort dus welke speler is het ~ Rens
	private DbPlayerCollector dpc;
	
	
	public PlayerModel(DbPlayerCollector dpc) {
	this.dpc = dpc;
	}

	public void getDatabaseInfo(DbPlayerCollector dpc) {
//		playerid = dpc.getPlayerID(username, gameid);
		username = dpc.getUsername(playerid);
		seqnr = dpc.getSeqnr(playerid);
		status = dpc.getStatus(playerid);
		isCurrentPlayer = dpc.getIfCurrentPlayer(playerid);
		patid = dpc.getPatternCardID(playerid);
		score = dpc.getScore(playerid);
		stringcolor = dpc.getColor(playerid);
		System.out.println("my color = " + stringcolor);
		if (stringcolor != null) {
			switch (stringcolor) {
			case "geel":
				color = Color.YELLOW;
				break;
			case "groen":
				color = Color.GREEN;
				break;
			case "rood":
				color = Color.RED;
				break;
			case "blauw":
				color = Color.BLUE;
				break;

			case "paars":
				color = Color.PURPLE;
				break;
			}
		}
		else {
			System.out.println("huh??");
			color = Color.WHITE;
		}
	}

	public int getPlayerId() {
		return playerid;
	}

	public void setPlayerId(int playerid) {
		this.playerid = playerid;
		System.out.println("player id set to:" + playerid);
	}

	public void setPayStones(int payStones) {
		this.payStones = payStones;
	}

	public int getPayStones() {
		return payStones;
	}

	public boolean isCurrentPlayer() {
		return isCurrentPlayer;
	}
	
	//milan
	//updates the database with current player
	public void setCurrentPlayer(boolean isCurrentPlayer) {
		this.isCurrentPlayer = isCurrentPlayer;
		int i = 0;
		if (isCurrentPlayer) {
			i = 1;
			System.out.println("Player: " + username + " is now current player of game: " + gameid);
			dpc.setGameTurn(gameid, playerid);
		} else {
			i = 0;
			System.out.println("Player: " + username + " is no longer current player of game: "+ gameid);
		}
		
		dpc.setCurrentPlayer(playerid, i);
	}

	public Color getObjectiveColor() {
		System.out.println("kleurtje model: " + color);
		return color;
	}

	public int getSeqnr() {
		return seqnr;
	}

	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;
		System.out.println("Setting " + playerid + "'s seqnr to: " + seqnr);
		dpc.setSeqnr(playerid, seqnr);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getGameid() {
		return gameid;
	}

	public void setGameid(int gameid) {
		this.gameid = gameid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DiceHolderType getDht() {
		return dht;
	}

	public void setDht(DiceHolderType dht) {
		this.dht = dht;
	}

	public int getPatid() {
		return patid;
	}

	public void setPatid(int patid) {
		this.patid = patid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
