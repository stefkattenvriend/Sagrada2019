package controller;

import databeest.DbChatCollector;
import databeest.DbDieCollector;
import databeest.DbDieUpdater;
import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DbPlayerCollector;
import databeest.DbPlayerStatsCollector;
import databeest.DataBaseApplication;
import databeest.DbCardCollector;
import databeest.DbUserInfoCollector;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MyScene;

public class MasterController extends Application{//een controller die alle andere aanmaakt? ~Rens

	private DbUserInfoCollector dbUserInfoCollector;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbCardCollector dbCardCollector;
	private DbChatCollector dbChatCollector;
	private DbGameCollector dbGameCollector;
	private DbPlayerCollector dbPlayerCollector;
	private DbPlayerStatsCollector dbPlayerStatsCollector;
	private DbDieCollector dbDieCollector;
	private DbDieUpdater dbDieUpdater;
	private DataBaseApplication databeest;
	
	private LoginController lc;
	private PlayerController pc;
	private GameController gc;
//	private ChatController chat;
	private MyScene myScene;
	private Stage stage;
	private MenuController mnController;
	private StatsController sc;
	private UpdateTimerController utc;
	private GameUpdateController guc;
	private MenuUpdateController muc;
	
	public void startup(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.startMasterController();
		this.stage = stage;
		myScene = new MyScene(this);
		mnController = new MenuController(myScene, this, dbGameCollector);
		stage.setResizable(false);
		stage.setScene(myScene);
		
		stage.setOnCloseRequest(e -> closeApp());
		stage.show();
	}
	
	private void closeApp() {
		System.out.println("Program stopped!");
		System.exit(0);
	}

	private void startMasterController() {
		databeest = new DataBaseApplication();
		dbUserInfoCollector = new DbUserInfoCollector(databeest);
		DatabasePTCCollector = new DbPatternCardInfoCollector(databeest);
		dbChatCollector = new DbChatCollector(databeest);
		dbGameCollector = new DbGameCollector(databeest);
		dbPlayerCollector = new DbPlayerCollector(databeest);
		dbCardCollector = new DbCardCollector(databeest);
		dbDieCollector = new DbDieCollector(databeest);
		dbPlayerStatsCollector = new DbPlayerStatsCollector(databeest);
		dbDieUpdater = new DbDieUpdater(databeest);
		
		
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		
			//Game refresher/checker
		this.muc = new MenuUpdateController(this);
		this.utc = new UpdateTimerController(null, muc);
		
				
		Thread t1 = new Thread(utc);
		t1.start();
		
		this.lc = new LoginController(dbUserInfoCollector);
		this.pc = new PlayerController(dbPlayerCollector);
		this.gc = new GameController(DatabasePTCCollector, dbGameCollector, lc, dbChatCollector, dbCardCollector, guc, dbPlayerCollector, dbDieCollector, dbDieUpdater);
		this.sc = new StatsController(dbPlayerStatsCollector);
//		this.chat = new ChatController(dbChatCollector);
		
		
		
		//make the GamePane
		
		// testen game
//		gc.newGame(); //dit maakt een nieuwe game aan (milan)
		
		//testen player
//		pc.setPlayerId(2);
//		System.out.println("Amount of paystones: " + pc.getPayStones());
	}
	
	public GameController getGameController()
	{
		return this.gc;
	}
	
	public LoginController getLoginController()
	{
		return this.lc;
	}
	
//	public ChatController getChatController()
//	{
//		return this.chat;
//	}
	
	public Stage getStage() {
		return this.stage;
	}

	public MenuController getMenuController() {
	
		return this.mnController;
	}
	
	public GameUpdateController getGameUpdateController() {
		
		return this.guc;
	}
	
	public void setGuc(GameUpdateController guc) {
		this.guc = guc;
		utc.setGuc(guc);
	}

	public UpdateTimerController getUtc() {
		return utc;
	}

	public PlayerController getPlayerController()
	{
		return this.pc;
	}
	
	public DbDieCollector getDbDieCollector() {
		return dbDieCollector;
	}

	public DataBaseApplication getDatabaseApplication() {
		return databeest;
	}
	
	

}