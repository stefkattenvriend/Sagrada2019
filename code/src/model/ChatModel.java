package model;

import java.util.ArrayList;

import databeest.DbChatCollector;

//milan
public class ChatModel {

	//Instance Variables
	private String message;
	private ArrayList<String> messages = new ArrayList<>();
	private DbChatCollector chatdatabase;
	
	//Constants
	
	//Constructor
	public ChatModel(DbChatCollector chatdb) {
		chatdatabase = chatdb;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
//	public void pushChat(String message, int playerid, Date time) {
//		chatdatabase.pushChat(message, playerid, time);
//	}
	
}
