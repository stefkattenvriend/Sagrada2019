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
	private int[] pcChoiche;

	private DiceHolderType dht;// welke diceholder er bij deze speler hoort dus welke speler is het ~ Rens
	private DbPlayerCollector dpc;
	
	
	public PlayerModel(DbPlayerCollector dpc) {
	this.dpc = dpc;
	int[] pcChoiche = new int[4];
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
			getPcChoiche();
		}
	}
	
	public void getPcChoiche() {
		pcChoiche = dpc.getPatternCardChoice(playerid);
	}

	public int getPlayerId() {
		return playerid;
	}

	public void setPlayerId(int playerid) {
		this.playerid = playerid;
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

	public void setCurrentPlayer(boolean isCurrentPlayer) {
		this.isCurrentPlayer = isCurrentPlayer;
	}

	public Color getObjectiveColor() {
		return color;
	}

	public int getSeqnr() {
		return seqnr;
	}

	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;
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
