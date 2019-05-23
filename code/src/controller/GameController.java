package controller;

import java.util.ArrayList;
import java.util.Collections;

import databeest.DbCardCollector;
import databeest.DbChatCollector;
import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;

public class GameController {// deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook
								// de andere controllers aan ~Rens

	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbGameCollector dbGameCollector;
	private LayerController lyc;
	private LoginController lc;
	private CardsController crc;
	private ChatController cc;

	private int gameid;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.lc = lc;
		pcc = new PatterncardController(DatabasePTCCollector);
		dhc = new DiceHolderController(pcc);
		lyc = new LayerController(pcc);
		cc = new ChatController(dbChat);
		crc = new CardsController(dbCardCollector, dhc.getDiceController().getDMAL());
		this.dbGameCollector = dbGamecollector;
		
		//Game refresher/checker
		UpdateTimerController checker = new UpdateTimerController();
		
		Thread t1 = new Thread(checker);
		t1.start();
	}
	
	
	
	public CardsController getCardsController() {
		return crc;
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
	
	public ChatController getChatController() {
		return cc;
	}
	public void createPrivateObjective() {

	}
	
	public LoginController getLoginController() {
		return lc;
	}

	// milan
	public void newGame() {
		dbGameCollector.pushGame();
//		String username = lc.getCurrentAccount();
		String username = "123";
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
