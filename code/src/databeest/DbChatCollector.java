package databeest;

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
	
	public String getUsername(int playerID) {
		String name = dataBaseApplication.getplayerUsername(playerID);
		
		return name;
	}

	public ArrayList<String> getChat(int playerid) {
		String query = "SELECT * FROM chatline WHERE player_idplayer = '" + playerid + "' ORDER BY time;";
		ArrayList<String> chat = dataBaseApplication.getChat(query);
		return chat;
	}
	public ArrayList<String> getChatDate(int playerid) {
		String query = "SELECT RIGHT(time, 8) FROM chatline WHERE player_idplayer = '" + playerid + "' ORDER BY time;";
		ArrayList<String> chatdate = dataBaseApplication.getChatDate(query);
		return chatdate;
	}
	
	public int getPlayerID(int gameid, String username) {
		int id = 0;
		id = dataBaseApplication.getPlayerID(username, gameid);
		return id;
	}
	
	public ArrayList<Integer> getPlayerIDs() {
		String query = "SELECT player_idplayer FROM chatline ORDER BY time;";
		ArrayList<Integer> chatIDs = dataBaseApplication.getChatIDs(query); 
		return chatIDs;
	}

	public int[] whichPlayers(int gameid) {
		int[] players = dataBaseApplication.GetPlayerIDs(gameid);
		return players;
	}
}
