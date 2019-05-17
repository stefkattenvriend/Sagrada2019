package controller;

import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import javafx.scene.layout.BorderPane;
import view.GamePanes.EnemyPane;
import view.GamePanes.GamePane;
import view.GamePanes.PlayerPane;

public class GameController {//deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook de andere controllers aan ~Rens

	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbGameCollector dbGameCollector;
	private LayerController lyc;
	private LoginController lc;
	
	private int gameid;
	
	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector, LoginController lc) {

		this.DatabasePTCCollector = DatabasePTCCollector;
		this.lc = lc;
		dhc = new DiceHolderController();
		pcc = new PatterncardController(DatabasePTCCollector);
		lyc = new LayerController(pcc);
		
		gamePaneMaker();
	}
	
	private void gamePaneMaker() {
		BorderPane gamePane1 = new BorderPane();
		CardsController cardsController = new CardsController();
		PlayerPane playerPane = new PlayerPane(dhc, pcc);
		EnemyPane enemyPane = new EnemyPane();
		GamePane gamePane = new GamePane(gamePane1, cardsController.getcardDisplayPane(), playerPane, enemyPane, this);
		
		//gamePane.getChildren().addAll(gamePane1, cardDisplayPane, playerPane, enemyPane);
		CardsController cardController = new CardsController();
		EnemyPaneController enemyPaneController = new EnemyPaneController();
		PlayerPaneController playerPaneController = new PlayerPaneController();
		lyc = new LayerController(pcc);
		this.dbGameCollector = dbGameCollector;
		
	}
	
	public DiceHolderController getDiceHolderController()
	{
		return dhc;
	}
	
	public PatterncardController getPatterncardController()
	{
		return pcc;
	}
	
	public LayerController getLayerController()
	{
		return lyc;
	}
	
	public void createPrivateObjective() {
		
	}
	
	public void newGame()
	{
		dbGameCollector.pushGame();
		String username = lc.getCurrentAccount();
		dbGameCollector.pushFirstPlayer(username);
	}
	
	public int getGameid() {
		return gameid;
	}
	
}
