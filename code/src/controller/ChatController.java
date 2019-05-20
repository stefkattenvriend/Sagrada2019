package controller;

import java.sql.Date;
import java.util.ArrayList;

import databeest.DbChatCollector;


//Gemaakt door milan.
public class ChatController {

	private DbChatCollector chatdb;

	
	public ChatController(DbChatCollector chatDB) {
		
		chatdb = chatDB;

		
	}
	
	

	public void sendChatToDatabase(int playerid, String date, String message) {
		chatdb.sendChat(playerid, date, message);
		
	}



	public ArrayList<String> getchat() {
		ArrayList<String> chat = chatdb.getChat();
		return chat;
	}
	
	public ArrayList<String> getchatDate() {
		ArrayList<String> chatdate = chatdb.getChatDate();
		return chatdate;
	}
	
	//methode om chat te lezen en door te geven naar model. (playerid, time, message)
	
	
}
