package databeest;

import java.sql.Date;

public class DbChatCollector {
	
	private DataBaseApplication dataBaseApplication;
	
	public DbChatCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public void pushChat(String message, int playerid, Date time) {
		String query = "INSERT INTO `chatline` (`player_idplayer`, `time`, `message`) VALUES('" + playerid + "', '" + time + "', '" + message + "');";
	dataBaseApplication.insertQuery(query);
	}
}
