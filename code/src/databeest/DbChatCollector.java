package databeest;

import java.sql.Date;

public class DbChatCollector {
	
	private DataBaseApplication dataBaseApplication;
	
	public DbChatCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public void sendChat(int playerid, String date, String message) {
		String query = "INSERT INTO `chatline` (`player_idplayer`, `time`, `message`) VALUES('" + playerid + "', " + date + ", '" + message + "');";
	dataBaseApplication.insertQuery(query);
	}
}
