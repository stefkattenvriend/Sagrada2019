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

	public ArrayList<String> getChat(int amountOfPlayers, int playerid1, int playerid2, int playerid3, int playerid4) {
		String query = "";
		if(amountOfPlayers == 4) {
		query = "SELECT * FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' OR player_idplayer = '" +playerid4+ "' ORDER BY time;";
		} else if (amountOfPlayers == 3) {
		query = "SELECT * FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' ORDER BY time;";
		} else if (amountOfPlayers == 2) {
		query = "SELECT * FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' ORDER BY time;";
		}
		ArrayList<String> chat = dataBaseApplication.getChat(query);
		return chat;
	}
	
	public ArrayList<String> getChatDate(int amountOfPlayers, int playerid1, int playerid2, int playerid3, int playerid4) {
		String query = "";
		if(amountOfPlayers == 4) {
		query = "SELECT RIGHT(time, 8) FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' OR player_idplayer = '" +playerid4+ "' ORDER BY time;";
		} else if (amountOfPlayers == 3) {
		query = "SELECT RIGHT(time, 8) FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' ORDER BY time;";
		} else if (amountOfPlayers == 2) {
		query = "SELECT RIGHT(time, 8) FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' ORDER BY time;";
		}
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

	public ArrayList<Integer> getPlayers(int amountOfPlayers, int playerid1, int playerid2, int playerid3,
			int playerid4) {
		String query = "";
		if(amountOfPlayers == 4) {
		query = "SELECT player_idplayer FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' OR player_idplayer = '" +playerid4+ "' ORDER BY time;";
		} else if (amountOfPlayers == 3) {
		query = "SELECT player_idplayer FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' OR player_idplayer = '" +playerid3+ "' ORDER BY time;";
		} else if (amountOfPlayers == 2) {
		query = "SELECT player_idplayer FROM chatline WHERE player_idplayer = '" +playerid1+ "' OR player_idplayer = '" +playerid2+ "' ORDER BY time;";
		}
		ArrayList<Integer> chatPlayers = dataBaseApplication.getChatPlayers(query); 
		return chatPlayers;
	}
}
