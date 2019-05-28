package databeest;

import java.util.ArrayList;

//milan
public class DbGameCollector {

	private DataBaseApplication dataBaseApplication;

	public DbGameCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public void pushGame() {
		// maak game aan en zet in database
		String query = "INSERT INTO `mwmastbe_db2`.`game` (`creationdate`) VALUES ((SELECT NOW()));";
		dataBaseApplication.insertQuery(query);

	}
	
	public void updateStatusAccept(int idplayer) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `playstatus_playstatus` = 'geaccepteerd' WHERE (`idplayer` = '" + idplayer + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	public void updateStatusIgnore(int idplayer) {
		String query = "UPDATE `mwmastbe_db2`.`player` SET `playstatus_playstatus` = 'geweigerd' WHERE (`idplayer` = '" + idplayer + "');";
		dataBaseApplication.insertQuery(query);
	}

	public void pushFirstPlayer(String username, String color) {
		// voeg user toe aan game
		int gameid = getHighestGameID();
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

	// return highest gameid(van degene die dus net is aangemaakt in gamecontroller)
	public int getHighestGameID() {
		int gameid = 0;
		gameid = dataBaseApplication.getHighestGameID();
		return gameid;
	}

	public void addGameDie() {
		int gameid = getHighestGameID();
		ArrayList<String> colors = getColors();
		for (String color : colors) { // loop door de 5 kleuren
			for (int dienumber = 1; dienumber < 19; dienumber++) { // for loop om 18 nummers te maken per color
				String query = "INSERT INTO `mwmastbe_db2`.`gamedie` (`idgame`, `dienumber`, `diecolor`) VALUES ('"
						+ gameid + "', '" + dienumber + "', '" + color + "');";
				dataBaseApplication.insertQuery(query);
			}
		}

	}

	public void insertPublicObjectiveCards(int x) {

		int gameid = getHighestGameID();

		String query = "INSERT INTO `mwmastbe_db2`.`sharedpublic_objectivecard` (`idgame`, `idpublic_objectivecard`) VALUES ('"
				+ gameid + "', '" + x + "');";
		dataBaseApplication.insertQuery(query);
	}

	public void insertToolCards(int x) {
		int gameid = getHighestGameID();
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
	
	public ArrayList<Integer> startedGames() {
		return dataBaseApplication.getStartedGames();
	}
	
	public ArrayList<Integer> waitedGames() {
		return dataBaseApplication.getWaitedGames();
	}
	
	public String getUsername(int playerid) {
		String username = dataBaseApplication.getplayerUsername(playerid);
		return username;
	}

}
