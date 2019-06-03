package databeest;

//Stef
public class DbPlayerCollector {

	// instance variables
	private DataBaseApplication dbApplication;

	public DbPlayerCollector(DataBaseApplication dbApplication) {
		this.dbApplication = dbApplication;
	}

	public int amountOfPaystones(int playerId) {
		int amount = 0;
		amount = dbApplication.getPlayerPayStones(playerId);

		return amount;
	}

	public int getPlayerID(String username, int gameID) {
		int PlayerID = dbApplication.getPlayerID(username, gameID);
		return PlayerID;
	}

	public int getSeqnr(int playerID) {
		int seqnr = dbApplication.getSeqnr(playerID);
		return seqnr;
	}

	public String getStatus(int playerID) {
		String status = dbApplication.getStatus(playerID);
		return status;

	}

	public boolean getIfCurrentPlayer(int playerID) {
		boolean isCurrentPlayer = false;
		int getCurrentPlayer = dbApplication.getIsCurrentPlayer(playerID);
		if (getCurrentPlayer == 1) {
			isCurrentPlayer = true;
		}
		return isCurrentPlayer;
	}

	public int getPatternCardID(int playerID) {
		int pcid = dbApplication.getPaternCardNumber(playerID);
		return pcid;
	}

	public int getScore(int playerID) {
		int score = dbApplication.getScore(playerID);
		return score;
	}

	public String getColor(int playerID) {
		String color = dbApplication.getPlayerColor(playerID);
		return color;
	}

	public String getUsername(int playerid) {
		String username = dbApplication.getplayerUsername(playerid);
		return username;
	}

	public void setSeqnr(int playerid, int seqnr) {
		// System.out.println("Updating database so " + playerid + "'s seqnr becomes: "
		// + seqnr);
		String query = "UPDATE `player` SET `seqnr` = '" + seqnr + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setCurrentPlayer(int playerid, int i) {
		String query = "UPDATE `player` SET `isCurrentPlayer` = '" + i + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public void setGameTurn(int gameid, int playerid) {
		String query = "UPDATE `game` SET `turn_idplayer` = '" + playerid + "' WHERE (`idgame` = '" + gameid + "');";
		dbApplication.insertQuery(query);
	}

	public int getDiceAmountOnFrame(int playerid) {
		int amount = dbApplication.getDiceAmountOnFrame(playerid);
		return amount;
	}

	public void setScore(int playerid, int score) {
		String query = "UPDATE `player` SET `score` = '" + score + "' WHERE (`idplayer` = '" + playerid + "');";
		dbApplication.insertQuery(query);
	}

	public int getEyes(int dienumber, int idgame, String color) {
		int i = dbApplication.getEyes(dienumber, idgame, color);
		return i;
	}

	public int getDieNumberinos(int playerid, int x, int y) {
		int dienumber = dbApplication.getDieNumberinos(playerid, x, y);
		return dienumber;
	}

	public String getDieColorinos(int playerid, int x, int y) {
		String diecolor = dbApplication.getDieColorinos(playerid, x, y);
		return diecolor;
	}

}
