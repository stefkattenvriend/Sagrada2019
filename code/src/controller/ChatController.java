package controller;

import databeest.DBChatCollector;
import model.ChatModel;

//Gemaakt door milan.
public class ChatController {

	private DBChatCollector chatdb;
	private ChatModel chatMd;
	
	public ChatController(DBChatCollector chatDB) {
		chatdb = chatDB;
		chatMd = new ChatModel(chatDB);
		
	}
	
	//methode om chat te lezen en door te geven naar model. (playerid, time, message)
	
	
}
