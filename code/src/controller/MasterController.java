package controller;

import databeest.DBChatCollector;
import databeest.DBPatternCardInfoCollector;
import databeest.DataBaseApplication;
import databeest.DbUserInfoCollector;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MyScene;

public class MasterController extends Application{//een controller die alle andere aanmaakt? ~Rens

	private DbUserInfoCollector dbUserInfoCollector;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	private DBChatCollector dbChatCollector;
	private DataBaseApplication databeest = new DataBaseApplication();
	
	private LoginController lc;//laat de controllers voor nu op public staan. later get en set maken
	private GameController gm;
	private ChatController chat;
	private MyScene myScene;
	private Stage stage;
	
	public void startup(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.startMasterController();
		this.stage = stage;
		myScene = new MyScene(this);
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.show();
	}
	
	private void startMasterController() {
		dbUserInfoCollector = new DbUserInfoCollector(databeest);
		DatabasePTCCollector = new DBPatternCardInfoCollector(databeest);
		dbChatCollector = new DBChatCollector(databeest);
		
		this.gm = new GameController(DatabasePTCCollector);
		this.lc = new LoginController(dbUserInfoCollector);
		this.chat = new ChatController(dbChatCollector);
		
		
		
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

	}
	
	public GameController getGameController()
	{
		return this.gm;
	}
	
	public LoginController getLoginController()
	{
		return this.lc;
	}
	
	public ChatController getChatController()
	{
		return this.chat;
	}
	
	public Stage getStage() {
		return this.stage;
	}

}
