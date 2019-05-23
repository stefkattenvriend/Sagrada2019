package model;

public class PlayerModel {
	
	// instance variables

	private int playerid;
	private String username;
	private int seqnr; //dit is later voor de volgorde van de beurtenprivate String username;
	private int gameid;
	private String status;
	private boolean isCurrentPlayer;
	private int payStones;
	private int patid; //patterncard_idpatterncard
	private String color;
	private int score;
	public PlayerModel()
	{
		
	}
	
	
	public int getPlayerId()
	{
		return playerid;
	}
	
	public void setPlayerId(int playerid)
	{
		this.playerid = playerid;
	}
	
	public void setPayStones(int payStones)
	{
		this.payStones = payStones;
	}
	
	public int getPayStones()
	{
		return payStones;
	}

	public boolean isCurrentPlayer() 
	{
		return isCurrentPlayer;
	}

	public void setCurrentPlayer(boolean isCurrentPlayer) 
	{
		this.isCurrentPlayer = isCurrentPlayer;
	}
	
	public String getObjectiveColor()
	{
		return color;
	}


	public int getSeqnr() {
		return seqnr;
	}


	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;
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
