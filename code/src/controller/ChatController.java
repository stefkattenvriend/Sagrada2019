package controller;

import java.util.ArrayList;


import databeest.DbChatCollector;
import model.GameModel;


//Gemaakt door milan.
public class ChatController {

	private DbChatCollector chatdb;
	private GameController gameController;
	
	public ChatController(DbChatCollector chatDB, GameController gameController) {
		this.gameController = gameController;
		chatdb = chatDB;

		
	}
	
	public int getGameid() {
		int gameid = 0;
		GameModel gameModel = gameController.getGm();
		gameid = gameModel.getGameId();
		return gameid;
	}
	
	public int getPlayerID(int gameid, String username) {
		int playerid = 0;
		playerid = chatdb.getPlayerID(gameid, username);
		return playerid;
	}
	

	public void sendChatToDatabase(int playerid, String date, String message) {
		chatdb.sendChat(playerid, date, message);
		
	}

	public String getUsername(int playerID) {
		String name = chatdb.getUsername(playerID);
		return name;
	}

	public ArrayList<String> getchat() {
		ArrayList<String> chat = chatdb.getChat();
		return chat;
	}
	
	public ArrayList<String> getchatDate() {
		ArrayList<String> chatdate = chatdb.getChatDate();
		return chatdate;
	}



	public ArrayList<Integer> getPlayerIDs() {
		
		ArrayList<Integer> iDs = chatdb.getPlayerIDs();		
		return iDs;
	}
	
	//methode om chat te lezen en door te geven naar model. (playerid, time, message)
	
	
}
