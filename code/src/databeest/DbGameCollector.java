package databeest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void pushFirstPlayer(String username) {
		//voeg user toe aan game
		int gameid = getGameid();
		String query = "INSERT INTO `mwmastbe_db2`.`player` (`username`, `game_idgame`, `playstatus_playstatus`, `isCurrentPlayer`, `private_objectivecard_color`) VALUES ('" + username + "', '" + gameid + "', 'uitdager', '1', 'rood');";
		dataBaseApplication.insertQuery(query);	
		
	}
	//return highest gameid(van degene die dus net is aangemaakt in gamecontroller)
	public int getGameid() {
		int gameid = 0;
		String query = "SELECT max(idgame) FROM game;";
		gameid = dataBaseApplication.getGameid(query);
		System.out.println(gameid);
		return gameid;
	}
	
	
}
