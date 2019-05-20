package controller;

import java.sql.Date;

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



	public void getchat() {
		// TODO Auto-generated method stub
		
	}
	
	
	//methode om chat te lezen en door te geven naar model. (playerid, time, message)
	
	
}
