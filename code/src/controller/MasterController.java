package controller;

import databeest.DBChatCollector;
import databeest.DBPatternCardInfoCollector;
import databeest.DataBaseApplication;
import databeest.DbUserInfoCollector;
import javafx.scene.layout.BorderPane;
import view.LayerPane;
import view.GamePanes.CardDisplayPane;
import view.GamePanes.EnemyPane;
import view.GamePanes.GamePane;
import view.GamePanes.PlayerPane;

public class MasterController {//een controller die alle andere aanmaakt? ~Rens
	
	
	
	private DbUserInfoCollector dbUserInfoCollector;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	private DBChatCollector dbChatCollector;
	private DataBaseApplication databeest = new DataBaseApplication();
	
	public LoginController lc;//laat de controllers voor nu op public staan. later get en set maken
	public GameController gm;
	public ChatController chat;
	
	public MasterController() {
		dbUserInfoCollector = new DbUserInfoCollector(databeest);
		DatabasePTCCollector = new DBPatternCardInfoCollector(databeest);
		dbChatCollector = new DBChatCollector(databeest);
		
		gm = new GameController(DatabasePTCCollector);
		lc = new LoginController(dbUserInfoCollector);
		chat = new ChatController(dbChatCollector);
		
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		{
//			databeest.doSomeQuerying();
//			databeest.getPaternCard(1, 1, 1);
//			databeest.getPaternCard(1, 2, 1);
//			databeest.getPaternCard(1, 3, 1);
//			databeest.getPaternCard(1, 4, 1);
//			databeest.getPaternCard(1, 5, 1);
			
//			databeest.doSomeUpdating();
		}
		
		//make the GamePane
		
	}

}