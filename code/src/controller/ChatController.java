package controller;

import databeest.DBChatCollector;

//Gemaakt door milan.
public class ChatController {

	private DBChatCollector chatdb;
	
	public ChatController(DBChatCollector chatDB) {
		chatdb = chatDB;
	}
	
	public void insertChatIntoDatabase(String message) {
//		Ik ben in de war met hoe het zit met model, view, controller, database enzo. 
//		Ik wil de chat pakken en omzetten in een string en die in de database proppen.
//		Moet ik dan een chatmodel maken of een controller aan de chatview meegeven, hoe zit het.
	}
	
	
}
