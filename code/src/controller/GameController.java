package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import databeest.DbCardCollector;
import databeest.DbChatCollector;
import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DbPlayerCollector;
import model.GameModel;
import model.PlayerModel;

public class GameController {// deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook
								// de andere controllers aan ~Rens

	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbGameCollector dbGameCollector;
	private DbPlayerCollector dpc;
	private LayerController lyc;
	private LoginController lc;
	private CardsController crc;
	private ChatController cc;
	private GameUpdateController guc;
	private GameModel gm;
	
	private PlayerController pc;
	
	private int gameid;
	private ArrayList<String> colors; 

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector, GameUpdateController guc, DbPlayerCollector dpc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		pcc = new PatterncardController(DatabasePTCCollector);
		dhc = new DiceHolderController(pcc);
		lyc = new LayerController(pcc);
		cc = new ChatController(dbChat);
		this.guc = guc;
		pc = new PlayerController(dpc);
		crc = new CardsController(dbCardCollector, dhc.getDiceController().getDMAL(), gm.getGameId());
		this.dbGameCollector = dbGamecollector;

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
	
	public GameModel getGm() {
		return gm;
	}

	// milan
	public void newGame() {
		colors = getColors(); //maakt 5 kleuren
		dbGameCollector.pushGame();
		String username = lc.getCurrentAccount();
//		String username = "123";
		dbGameCollector.pushFirstPlayer(username, colors.get(0));
		insertPublicObjectiveCards();
		insertToolCards();
		createGameDie();
		getPlayer("kees", gameid);//deze actie wordt uitgevoerd wanneer uitnodiging geaccepteerd is
		
	}
	
	private void createGameDie() {
		dbGameCollector.addGameDie();
	}



	public void getPlayer(String username, int gameid) {
		int x = 4;
		int i = (int)(Math.random() * ((x - 1) + 1)) + 1;
		addPlayer(username, gameid, colors.get(i));
		colors.remove(i);
		x--;
	}
	
	public void addPlayer(String username, int gameid, String color) {
		dbGameCollector.addPlayer(username, gameid, color);
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
//		System.out.println(list);//syso to check which numbers are added to database
		return list;
	}
	
	private ArrayList<String> getColors() {
		ArrayList<String> colors = new ArrayList<>(); 
		colors = dbGameCollector.getColors();
		Collections.shuffle(colors);
		return colors;
	}
	
	
	public int getGameid() {
		gameid = dbGameCollector.getHighestGameID();
		return gameid;
	}

	public void createGameModel(int gameID) {
		String username = lc.getCurrentAccount();
		int amountOfPlayers = dbGameCollector.getAmountOfPlayers(gameID);
		GameModel gm = new GameModel(gameID, dbGameCollector, username, dpc, amountOfPlayers);
		this.gm = gm;
		guc.setGameModel(gm);
		Integer[] playerIDs = dbGameCollector.getPlayers(gameID);

		for (int i = 0; i < amountOfPlayers; i++) {
			//kijk welke spelers er meedoen en maak ze

			pc.setPlayerId(playerIDs[i]);
			gm.addPlayer(i, pc.getPlayerName());
		}
		
	}

}
