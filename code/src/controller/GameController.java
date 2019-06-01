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
import javafx.scene.paint.Color;
import helpers.PatterncardType;
import model.GameModel;
import view.GamePanes.ChatPane;
import model.PlayerPayStoneModel;
import view.GamePanes.PersonalAttributes;
import view.GamePanes.PlayerPane;
import view.GamePanes.GamePane;

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
	private PlayerPayStoneModel ppsm;
	private GamePane gamepane;
	private boolean gameRunning;
	private boolean allPatternCards;

	private PlayerController pc;
	private ChatPane chatPane;
	private PlayerPane pp;
	private PersonalAttributes pa;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector, GameUpdateController guc,
			DbPlayerCollector dpc, DbDieCollector ddc, DbDieUpdater ddu, DbTurnCollector dtc, DbPayStoneRuler psr,
			DbToolCardCollector tcc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		this.dbCardCollector = dbCardCollector;

		cc = new ChatController(dbChat, this);
		this.dbDieCollector = ddc;
		this.guc = guc;
		this.dbDieUpdater = ddu;

		ppsm = new PlayerPayStoneModel();

		pc = new PlayerController(dpc);
		this.dbGameCollector = dbGamecollector;

		this.dtc = dtc;
		dtcc = tcc;
		this.psr = psr;

		this.gameRunning = false;
		this.allPatternCards = false;
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
		return ptsc;
	}

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
			// kijk welke spelers er meedoen en maak ze
			pc.setPlayerId(playerIDs[i]);
			gm.addPlayer(i, playerIDs[i], username);
			System.out.println("playerIds[i]" + playerIDs[i]);
		}
		pcc = new PatterncardController(DatabasePTCCollector, gm);
		lyc = new LayerController(pcc, this);
		System.out.println("Player id in create game model: " + pc.getPlayerID());
		this.dhc = new DiceHolderController(pcc, dbDieCollector, gm.getGameId());
		this.tc = new TurnController(this, dhc, dbDieUpdater, gm, dtc, username, gm.getGameId());
		createCardsController();
	}

	public void createCardsController() {
		psc = new PayStoneController(psr, pc.getPlayerID(), gm.getGameId());
		tcc = new ToolCardController(dhc.getDiceController().getDMAL(), psc, dtcc, gm.getGameId());
		crc = new CardsController(dbCardCollector, gm.getGameId(), tcc);
		System.out.println("should be gameId: " + gm.getGameId());
	}

	public PayStoneController getPayStoneController() {
		return psc;
	}

	public void updatePaystones() {
		if (gameRunning) {
			int amount = psc.getPlayerStones();
			System.out.println("amount: " + amount);
			if (amount != ppsm.getStones()) {
				System.out.println("ppsm amount: " + ppsm.getStones());
				ppsm.setStones(amount);
				pa.refresh();
			}
		}
	}

	public void setPersonalAttributes(PersonalAttributes pa) {
		this.pa = pa;
	}

	public void updatePC() {
		if (gameRunning) {
			if (allPatternCards == false) {
				gm.updateEnemyPCid();
				pcc.getPcModels(gm);
				gamepane.updatePC();
				allPatternCards = pcc.checkAllPatternCards();
			}
		}

	}

	public void setGamepane(GamePane gamepane) {
		this.gamepane = gamepane;
		allPatternCards = false;//zorgt ervoor dat patterncards opnieuw worden opgehaald als je een tweede game opent
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public void setMyColor() {
		if (gameRunning) {
			gamepane.setMyColor(gm.getMyColor());
		}
	}

	public void updatePCid(int i) {
		// pcc.updatePCa(i, PatterncardType.PLAYER);
		// gm.updatePCa(i);
		gamepane.updatePCid(i);
		this.allPatternCards = false;

	}

	public void giveChatPane(ChatPane chatPane) {
		this.chatPane = chatPane;
	}

	public ChatPane getChatPane() {
		return chatPane;
	}

	public void updateChatPane() {
		if (chatPane != null) {
			chatPane.updateChat();
		}
	}

}
