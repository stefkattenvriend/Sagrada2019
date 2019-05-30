package controller;

import databeest.DataBaseApplication;
import databeest.DbCardCollector;
import databeest.DbChatCollector;
import databeest.DbDieCollector;
import databeest.DbDieUpdater;
import databeest.DbGameCollector;
import databeest.DbMenuCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DbPayStoneRuler;
import databeest.DbPlayerCollector;
import databeest.DbPlayerStatsCollector;
import databeest.DbToolCardCollector;
import databeest.DbTurnCollector;
import databeest.DbUserInfoCollector;
import javafx.application.Application;
import javafx.application.Platform;
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
	private DbMenuCollector dbMenuCollector;
	private DataBaseApplication databeest;
	private DbPayStoneRuler psr;
	
	private DbTurnCollector dbTurnCollector;
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
	private DbToolCardCollector tcc;
	
	public void startup(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.startMasterController();
		this.stage = stage;
		myScene = new MyScene(this);
		mnController = new MenuController(myScene, this, dbGameCollector, muc);
		startUpdate();
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
		dbMenuCollector = new DbMenuCollector(databeest);
		dbDieUpdater = new DbDieUpdater(databeest);
		dbTurnCollector = new DbTurnCollector(databeest);
		psr = new DbPayStoneRuler(databeest);
		tcc = new DbToolCardCollector(databeest);
		
		
		
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		
		this.lc = new LoginController(dbUserInfoCollector);
		this.pc = new PlayerController(dbPlayerCollector);
		this.gc = new GameController(DatabasePTCCollector, dbGameCollector, lc, dbChatCollector, dbCardCollector, guc, dbPlayerCollector, dbDieCollector, dbDieUpdater, dbTurnCollector, psr, tcc);
		this.sc = new StatsController(dbPlayerStatsCollector);
//		this.chat = new ChatController(dbChatCollector);
		
		
		
		//make the GamePane
		
		// testen game
//		gc.newGame(); //dit maakt een nieuwe game aan (milan)
		
		//testen player
//		pc.setPlayerId(2);
//		System.out.println("Amount of paystones: " + pc.getPayStones());
	}
	
	
	private void startUpdate() {
		//Game refresher/checker
//	this.guc = new GameUpdateController(this);
//	this.muc = new MenuUpdateController(this);
//	this.utc = new UpdateTimerController(guc, muc);
	
//			
//	Thread t1 = new Thread(utc);
//	t1.start();
		
	MasterRunnable masterRunnable = new MasterRunnable(this.getMenuController(), this.getGameController());	
	
	Thread t1 = new Thread(masterRunnable);
	t1.start();
		
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
//		utc.setGuc(guc);
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
	
	public DbMenuCollector getDbMenuCollecter() {
		return dbMenuCollector;
	}

	public DataBaseApplication getDatabaseApplication() {
		return databeest;
	}
	
	

}