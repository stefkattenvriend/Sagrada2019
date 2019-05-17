package model;

import java.sql.Date;
import java.util.ArrayList;

import databeest.DBChatCollector;

//milan
public class ChatModel {

	//Instance Variables
	private String message;
	private ArrayList<String> messages = new ArrayList<>();
	private DBChatCollector chatdatabase;
	
	//Constants
	
	//Constructor
	public ChatModel(DBChatCollector chatdb) {
		chatdatabase = chatdb;
	}
	
	public void addMessage(String message) {
		messages.add(message);
	}
	
	public void pushChat(String message, int playerid, Date time) {
		chatdatabase.pushChat(message, playerid, time);
	}
	
}
