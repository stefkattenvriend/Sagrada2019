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
import javafx.stage.Stage;
import model.GameModel;
import view.MyScene;

public class MasterController extends Application {// een controller die alle andere aanmaakt? ~Rens

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
	private GameController gc;
	// private ChatController chat;
	private MyScene myScene;
	private Stage stage;
	private MenuController mnController;
	private StatsController sc;
	private DbToolCardCollector tcc;
	private GameModel gameModel;

	public void startup(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.startMasterController();
		this.stage = stage;
		gameModel = gc.getGm();
		myScene = new MyScene(this, gameModel);
		stage.setResizable(false);
		stage.setScene(myScene);
		stage.setOnCloseRequest(e -> closeApp());
		stage.show();
	}

	private void closeApp() {

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

		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver")) && (databeest.makeConnection()))

			this.lc = new LoginController(dbUserInfoCollector, this);
		this.gc = new GameController(DatabasePTCCollector, dbGameCollector, lc, dbChatCollector, dbCardCollector,
				dbPlayerCollector, dbDieCollector, dbDieUpdater, dbTurnCollector, psr, tcc, this);
		this.sc = new StatsController(dbPlayerStatsCollector);

	}

	private void startUpdate() {

		MasterRunnable masterRunnable = new MasterRunnable(this.getMenuController(), this.getGameController());

		Thread t1 = new Thread(masterRunnable);
		t1.start();

	}

	public void makeMenuController() {
		mnController = new MenuController(myScene, this, dbGameCollector, psr);
		startUpdate();
	}

	public GameController getGameController() {
		return this.gc;
	}

	public MyScene getMyScene() {
		return myScene;
	}

	public LoginController getLoginController() {
		return this.lc;
	}

	// public ChatController getChatController()
	// {
	// return this.chat;
	// }

	public Stage getStage() {
		return this.stage;
	}

	public MenuController getMenuController() {

		return this.mnController;
	}
	//
	// public UpdateTimerController getUtc() {
	// return utc;
	// }

	public DbDieCollector getDbDieCollector() {
		return dbDieCollector;
	}

	public DbMenuCollector getDbMenuCollecter() {
		return dbMenuCollector;
	}

	public DbGameCollector getDbGameCollector() {
		return dbGameCollector;
	}

	public DataBaseApplication getDatabaseApplication() {
		return databeest;
	}

	public PayStoneController getPayStoneController() {
		return gc.getPayStoneController();
	}
}