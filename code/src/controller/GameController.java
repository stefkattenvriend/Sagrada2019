package controller;

import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;


import java.util.ArrayList;
import java.util.Collections;



import databeest.DataBaseApplication;

public class GameController {// deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook
								// de andere controllers aan ~Rens
	CardsController cards = new CardsController();// Jami wat is dit? ~Rens

	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbGameCollector dbGameCollector;
	private LayerController lyc;
	private LoginController lc;

	private int gameid;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.lc = lc;
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
		lyc = new LayerController(pcc);
		this.dbGameCollector = dbGamecollector;

	}

	public DiceHolderController getDiceHolderController() {
		return dhc;
	}

	public PatterncardController getPatterncardController() {
		return pcc;
	}

	public LayerController getLayerController() {
		return lyc;
	}

	public void createPrivateObjective() {

	}

	// milan
	public void newGame() {
		dbGameCollector.pushGame();
		String username = lc.getCurrentAccount();
//		String username = "123";
		dbGameCollector.pushFirstPlayer(username);
		insertPublicObjectiveCards();
		insertToolCards();
		

	}

	private void insertToolCards() {
		ArrayList<Integer> randomkaarten = generateThreeRandomUniqueNumbers(12); // van 12 nummers(aantal toolcards), geef mij er drie at random.
		for(int i = 0; i < 3; i++) {
			int x = randomkaarten.get(i);
			dbGameCollector.insertToolCards(x);
		}
	}

	public void insertPublicObjectiveCards() {//set to private later
		ArrayList<Integer> randomkaarten = generateThreeRandomUniqueNumbers(10); // van 10 nummers, geef mij er drie at random.
		for(int i = 0; i < 3; i++) {
			int x = randomkaarten.get(i);
			dbGameCollector.insertPublicObjectiveCards(x);
		}
	}

	private ArrayList<Integer> generateThreeRandomUniqueNumbers(int aantalkaarten) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= aantalkaarten; i++) {
			list.add(i);

		}

		Collections.shuffle(list);

		int x = list.size() - 3;
		for (int i = 0; i < x; i++) {
			list.remove(0);

		}
		System.out.println(list);//syso to check which numbers are added to database
		return list;
	}

	public int getGameid() {
		return gameid;
	}

}
