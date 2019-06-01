package databeest;

import java.util.ArrayList;

//milan
public class DbGameCollector {

	private DataBaseApplication dataBaseApplication;

	public DbGameCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public void pushGame(int gameid) {
		// maak game aan en zet in database en haal gameid op
		
		String query = "INSERT INTO `mwmastbe_db2`.`game` (`idgame`, `creationdate`) VALUES ('" + gameid + "', NOW());";
		dataBaseApplication.insertQuery(query);
	
	}
	
	public int getRound(int gameid) {
		int round = 0;
		round = dataBaseApplication.getRoundNumber(gameid);
//		System.out.println("dit is het ronde nummer" + round);   // syso om rondenummer te checken 
		return round;
	}
	
	public void updateStatusAccept(int idplayer) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `playstatus_playstatus` = 'geaccepteerd' WHERE (`idplayer` = '" + idplayer + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	public void updateStatusIgnore(int idplayer) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `playstatus_playstatus` = 'geweigerd' WHERE (`idplayer` = '" + idplayer + "');";
		dataBaseApplication.insertQuery(query);
	}

	public void pushFirstPlayer(String username, String color, int gameid) {
		// voeg user toe aan game
		String query = "INSERT INTO `mwmastbe_db2`.`player` (`username`, `game_idgame`, `playstatus_playstatus`, `seqnr`, `isCurrentPlayer`, `private_objectivecard_color`) VALUES ('"
				+ username + "', '" + gameid + "', 'uitdager', '1', '1', '" + color + "');";
		dataBaseApplication.insertQuery(query);

	}

	// voegt player toe aan een game
	public void addPlayer(String username, int idgame, String color, int seq) {
		int gameid = getHighestGameID();// idgame
		String query = "INSERT INTO `mwmastbe_db2`.`player` (`username`, `game_idgame`, `playstatus_playstatus`, `seqnr`, `isCurrentPlayer`, `private_objectivecard_color`) VALUES ('"
				+ username + "', '" + gameid + "', 'uitgedaagde', " + seq + ", '0', '" + color + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	//voeg playerframefield toe aan game
	public void addPlayerFrameField(String username, int gameid) {
		for (int y = 1; y < 5; y++) {
			for (int x = 1; x < 6; x++) {
				String query = "INSERT INTO playerframefield(player_idplayer, position_x, position_y, idgame) VALUES ((SELECT idplayer FROM player WHERE username = '" + username + "'AND game_idgame = " + gameid + "), " + x + ", "+ y + "," + gameid + ");";
				dataBaseApplication.insertQuery(query);
			}
		}
		
		
	}

	// return highest gameid(van degene die dus net is aangemaakt in menucontroller)
	public int getHighestGameID() {
		int gameid = 0;
		gameid = dataBaseApplication.getHighestGameID();
		return gameid;
	}

	public void addGameDie(int gameid) {
		ArrayList<String> colors = getColors();
		for (String color : colors) { // loop door de 5 kleuren
			for (int dienumber = 1; dienumber < 19; dienumber++) { // for loop om 18 nummers te maken per color
				String query = "INSERT INTO `mwmastbe_db2`.`gamedie` (`idgame`, `dienumber`, `diecolor`) VALUES ('"
						+ gameid + "', '" + dienumber + "', '" + color + "');";
				dataBaseApplication.insertQuery(query);
			}
		}

	}

	public void insertPublicObjectiveCards(int x, int gameid) {
		String query = "INSERT INTO `mwmastbe_db2`.`sharedpublic_objectivecard` (`idgame`, `idpublic_objectivecard`) VALUES ('"
				+ gameid + "', '" + x + "');";
		dataBaseApplication.insertQuery(query);
	}

	public void insertToolCards(int x, int gameid) {
		String query = "INSERT INTO `mwmastbe_db2`.`gametoolcard` (`idtoolcard`, `idgame`) VALUES ('" + x + "', '"
				+ gameid + "');";
		dataBaseApplication.insertQuery(query);

	}

	public int getRoundNumber(int gameID) {
		int round;
		round = dataBaseApplication.getRoundNumber(gameID);
		return round;
	}

	public ArrayList<String> getColors() {
		ArrayList<String> colors = new ArrayList<>();
		colors = dataBaseApplication.getColor();
		return colors;
	}
	
	public int getAmountOfPlayers(int gameID)
	{
		int amount = dataBaseApplication.getAmountOfPlayers(gameID);
		return amount;
	}
	
	public int[] getPlayers(int gameID) {
		return dataBaseApplication.GetPlayerIDs(gameID);
	}
	
	public ArrayList<Integer> startedGames(String username) {
		return dataBaseApplication.getStartedGames(username);
	}
	
	public ArrayList<Integer> waitedGames(String username) {

		return dataBaseApplication.getWaitedGames(username);
	}
	
	public String getUsername(int playerid) {
		String username = dataBaseApplication.getplayerUsername(playerid);
		return username;
	}
	
	public int[] getPatternCardChoice(int playerid) {
		return dataBaseApplication.getPcChoiche(playerid);
	}

	public int getPlayerID(int gameid, String username) {
		int playerid = dataBaseApplication.getPlayerID(username, gameid);
		return playerid;
	}
	
	public int getSeqnr(int playerid) {
		int seqnr = dataBaseApplication.getSeqnr(playerid);
		return seqnr;
	}
	
}
