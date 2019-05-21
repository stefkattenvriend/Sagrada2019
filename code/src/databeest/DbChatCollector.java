package databeest;

import java.sql.Date;
import java.util.ArrayList;

public class DbChatCollector {
	
	private DataBaseApplication dataBaseApplication;
	
	public DbChatCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public void sendChat(int playerid, String date, String message) {
		String query = "INSERT INTO `chatline` (`player_idplayer`, `time`, `message`) VALUES('" + playerid + "', " + date + ", '" + message + "');";
	dataBaseApplication.insertQuery(query);
	}

	public ArrayList<String> getChat() {
		String query = "SELECT * FROM `chatline`;";
		ArrayList<String> chat = dataBaseApplication.getChat(query);
		return chat;
	}
	public ArrayList<String> getChatDate() {
		String query = "SELECT RIGHT(time, 8) FROM chatline;";
		ArrayList<String> chatdate = dataBaseApplication.getChatDate(query);
		return chatdate;
	}
}
