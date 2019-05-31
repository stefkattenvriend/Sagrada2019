package controller;

import databeest.DbCardCollector;
import databeest.DbChatCollector;
import databeest.DbDieCollector;
import databeest.DbDieUpdater;
import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DbPayStoneRuler;
import databeest.DbPlayerCollector;
import databeest.DbToolCardCollector;
import databeest.DbTurnCollector;
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
	private DbTurnCollector dtc;
	private DbPayStoneRuler psr;
	private DbToolCardCollector dtcc;
	private ToolCardController tcc;
	private PayStoneController psc;
	
	private PlayerController pc;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector, LoginController lc, DbChatCollector dbChat, 
			DbCardCollector dbCardCollector, GameUpdateController guc, DbPlayerCollector dpc, DbDieCollector ddc, DbDieUpdater ddu, 
			DbTurnCollector dtc, DbPayStoneRuler psr, DbToolCardCollector tcc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		this.dbCardCollector = dbCardCollector;
		
		cc = new ChatController(dbChat, this);
		this.dbDieCollector = ddc;
		this.guc = guc;
		this.dbDieUpdater = ddu;
		
		pc = new PlayerController(dpc);
		this.dbGameCollector = dbGamecollector;
		
		this.dtc = dtc;
		dtcc = tcc;
		this.psr = psr;
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
		
		int[] playerIDs = dbGameCollector.getPlayers(gameID);

		for (int i = 0; i < amountOfPlayers; i++) {
			//kijk welke spelers er meedoen en maak ze

			pc.setPlayerId(playerIDs[i]);
			gm.addPlayer(i, playerIDs[i], username);
		}
		pcc = new PatterncardController(DatabasePTCCollector, gm);
		lyc = new LayerController(pcc);
		this.dhc = new DiceHolderController(pcc, dbDieCollector, gm.getGameId());
		this.tc = new TurnController(dhc, dbDieUpdater, gm, dtc, username, gm.getGameId());
		createCardsController();
	}
	
	public void createCardsController() {
		psc = new PayStoneController(psr, pc.getPlayerID(), gm.getGameId());
		tcc = new ToolCardController(dhc.getDiceController().getDMAL(), psc, dtcc, gm.getGameId());
		psc = new PayStoneController(psr, pc.getPlayerID(), gm.getGameId());
		crc = new CardsController(dbCardCollector, gm.getGameId(), tcc);
	}

	public PayStoneController getPayStoneController() {
		return psc;
	}

}
