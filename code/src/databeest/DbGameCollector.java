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
	
	public void pushFirstPlayer(String username, String color) {
		//voeg user toe aan game
		int gameid = getGameid();
		String query = "INSERT INTO `mwmastbe_db2`.`player` (`username`, `game_idgame`, `playstatus_playstatus`, `isCurrentPlayer`, `private_objectivecard_color`) VALUES ('" + username + "', '" + gameid + "', 'uitdager', '1', '" + color + "');";
		dataBaseApplication.insertQuery(query);	
		
	}
	
	//voegt player toe aan een game
	public void addPlayer(String username, int idgame, String color) {
		int gameid = getGameid();//idgame
		String query = "INSERT INTO `mwmastbe_db2`.`player` (`username`, `game_idgame`, `playstatus_playstatus`, `isCurrentPlayer`, `private_objectivecard_color`) VALUES ('" + username + "', '" + gameid + "', 'uitgedaagde', '0', '" + color + "');";
		dataBaseApplication.insertQuery(query);
	}
	
	//return highest gameid(van degene die dus net is aangemaakt in gamecontroller)
	public int getGameid() {
		int gameid = 0;
		gameid = dataBaseApplication.getGameid();
		return gameid;
	}

	public void insertPublicObjectiveCards(int x) {
		
		int gameid = getGameid();
		
		String query = "INSERT INTO `mwmastbe_db2`.`sharedpublic_objectivecard` (`idgame`, `idpublic_objectivecard`) VALUES ('" + gameid + "', '" + x + "');";
		dataBaseApplication.insertQuery(query);
	}

	public void insertToolCards(int x) {
		int gameid = getGameid();
		String query = "INSERT INTO `mwmastbe_db2`.`gametoolcard` (`idtoolcard`, `idgame`) VALUES ('" + x + "', '" + gameid + "');";
		dataBaseApplication.insertQuery(query);
		
	}
	
	public int getRoundNumber(int gameID) {
		int round;
		
		round = dataBaseApplication.getRoundNumber(gameID);
		
		System.out.println(round);
		
		return round;
	}
	
	
	public ArrayList<String> getColors() {
		ArrayList<String> colors = new ArrayList<>(); 
		colors = dataBaseApplication.getColor();
		return colors;
	}

	
}
