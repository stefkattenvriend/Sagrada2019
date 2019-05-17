package controller;

import databeest.DbChatCollector;
import model.ChatModel;

//Gemaakt door milan.
public class ChatController {

	private DbChatCollector chatdb;
	private ChatModel chatMd;
	
	public ChatController(DbChatCollector chatDB) {
		chatdb = chatDB;
		chatMd = new ChatModel(chatDB);
		
	}
	
	//methode om chat te lezen en door te geven naar model. (playerid, time, message)
	
	
}
