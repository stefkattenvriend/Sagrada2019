package controller;

import databeest.DbCardCollector;
import databeest.DbChatCollector;
import databeest.DbDieCollector;
import databeest.DbDieUpdater;
import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DbPlayerCollector;
import model.GameModel;

public class GameController {// deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook
								// de andere controllers aan ~Rens

	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbCardCollector dbCardCollector;
	private DbGameCollector dbGameCollector;
	private DbDieCollector dbDieCollector;
	private DbDieUpdater dbDieUpdater;
	private DbPlayerCollector dpc;
	private LayerController lyc;
	private LoginController lc;
	private CardsController crc;
	private ChatController cc;
	private PointsController ptsc;
	private TurnController tc;
	private GameUpdateController guc;
	private GameModel gm;
	
	private PlayerController pc;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector, GameUpdateController guc, DbPlayerCollector dpc, DbDieCollector ddc, DbDieUpdater ddu) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		this.dbCardCollector = dbCardCollector;
		lyc = new LayerController(pcc);
		cc = new ChatController(dbChat, this);
		this.dbDieCollector = ddc;
		this.guc = guc;
		this.dbDieUpdater = ddu;
		
		pc = new PlayerController(dpc);
		this.dbGameCollector = dbGamecollector;
	}
	
	public CardsController getCardsController() {
		return crc;
	}

	public DiceHolderController getDiceHolderController() {
		return dhc;
	}

	public TurnController getTurnController() {
		return tc;
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
	
	public PointsController getPointsController() {
		return ptsc; }
	
	public GameModel getGm() {
		return gm;
	}

	public void createGameModel(int gameID) {
		String username = lc.getCurrentAccount();
		int amountOfPlayers = dbGameCollector.getAmountOfPlayers(gameID);
		GameModel gm = new GameModel(gameID, dbGameCollector, username, dpc, amountOfPlayers);
		this.gm = gm;
		guc.setGameModel(gm);
		int[] playerIDs = dbGameCollector.getPlayers(gameID);

		for (int i = 0; i < amountOfPlayers; i++) {
			//kijk welke spelers er meedoen en maak ze

			pc.setPlayerId(playerIDs[i]);
			gm.addPlayer(i, playerIDs[i], username);
		}
		pcc = new PatterncardController(DatabasePTCCollector, gm);
		this.dhc = new DiceHolderController(pcc, dbDieCollector, gm.getGameId());
		this.tc = new TurnController(dhc, dbDieUpdater, gm);
		
		this.createCardsController();
	}
	
	public void createCardsController() {
		crc = new CardsController(dbCardCollector, dhc.getDiceController().getDMAL(), gm.getGameId());
	}

}
