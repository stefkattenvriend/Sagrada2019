package controller;

import databeest.DBPatternCardInfoCollector;
import javafx.scene.layout.BorderPane;
import view.GamePanes.CardDisplayPane;
import view.GamePanes.EnemyPane;
import view.GamePanes.GamePane;
import view.GamePanes.PlayerPane;

public class GameController {//deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook de andere controllers aan ~Rens
	//Jami wat is dit? ~Rens
	
	public DiceHolderController dhc;
	public PatterncardController pcc;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	public LayerController lc;
	
	//Cards

	public GameController(DBPatternCardInfoCollector DatabasePTCCollector) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		dhc = new DiceHolderController();
		pcc = new PatterncardController(DatabasePTCCollector);
		lc = new LayerController(pcc);
		
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
	}
	
}
