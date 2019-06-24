package model;

import java.util.ArrayList;

import controller.GameController;
import controller.ToolCardController;
import databeest.DbPlayerCollector;
import helpers.DiceHolderType;
import javafx.scene.paint.Color;

public class PlayerModel {

	// instance variables

	private int playerid;
	private String username;
	private int seqnr; 
	private int gameid;
	private String status;
	private boolean isCurrentPlayer;
	private int payStones;
	private int patid;
	private String stringcolor;
	private Color color;
	private int score;
	private int movesAllowed1 = 1;
	private int movesAllowed2 = 1;
	private int turnPlace;
	private GameModel gm;
	private int amountOfDiceOnFrame;
	private int publicPoints;
	private int totalPoints;
	
	
	private DiceHolderType dht;
	private DbPlayerCollector dpc;
	private ToolCardController tcc;
	private GameController gc;
	
	
	public PlayerModel(DbPlayerCollector dpc, GameModel gm, ToolCardController tcc, GameController gc) {
		this.tcc = tcc;
		this.gc = gc;
		this.gm = gm;
		this.dpc = dpc;
		publicPoints = -20;
		totalPoints = -20;
	}

	public void getDatabaseInfo(DbPlayerCollector dpc) {
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
	}
	
	public int getMovesAllowed1() {
		return movesAllowed1;
	}
	
	public void resetMoves() {
		movesAllowed1 = 1;
		movesAllowed2 = 1;
	}
	
	public void doMove1() {		
		movesAllowed1 = movesAllowed1 -1;
		tcc.doMove();
	}
	
	public void giveMove1() {		
		movesAllowed1 = movesAllowed1 +1;
	}
	
	public int getMovesAllowed2() {
		return movesAllowed2;
	}
	
	public void doMove2() {		
		movesAllowed2 = movesAllowed2 -1;
		tcc.doMove();
	}
	
	public void giveMove2() {		
		movesAllowed2 = movesAllowed2 +1;
	}
	
	public void setPayStones(int payStones) {
		this.payStones = payStones;
	}

	public int getPayStones() {
		payStones = dpc.amountOfPaystones(playerid);
		setPayStones(payStones);
		return payStones;
	}

	public boolean isCurrentPlayer() {
		return isCurrentPlayer;
	}
	
	public void setCurrentPlayer(boolean isCurrentPlayer) {
		this.isCurrentPlayer = isCurrentPlayer;
		int i = 0;
		if (isCurrentPlayer) {
			i = 1;
			System.out.println("Player: " + username + " is now current player of game: " + gameid);
			dpc.setGameTurn(gameid, playerid);
		} else {
			i = 0;
		}
		
		dpc.setCurrentPlayer(playerid, i);
	}

	public Color getObjectiveColor() {
		return color;
	}

	public int getSeqnr() {
		seqnr = dpc.getSeqnr(playerid);
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
	
	public void reloadPcID() {
		patid = dpc.getPatternCardID(playerid);
	}
	
	public int getDiceAmountOnFrame()
	{
		amountOfDiceOnFrame = dpc.getDiceAmountOnFrame(playerid);
		return amountOfDiceOnFrame;

	}
	
	public int getTurn() {
		System.out.println("isCurrentPlayer: " + isCurrentPlayer);
		if(seqnr <= gm.getPma().length) {
			return 1;
		} else {
			return 2;
		}
	}

	public void setPlaceInArrayList(int i) {
		turnPlace = i;
	}

	public int getSharedObjectivePoints() {	
	int points = 0;
	points = gc.getCardsController().getSharedObjectivePoints();
	return points;
	}
	
	public void setPublicPoints(int publicPoints)
	{
		this.publicPoints = publicPoints;
	}
	
	public int getPublicPoints()
	{
		return publicPoints;
	}

	public void setTotalPoints(int totalPoints) 
	{
		this.totalPoints = totalPoints;
	}
	
	public int getTotalPoints()
	{
		return totalPoints;
	}

}
