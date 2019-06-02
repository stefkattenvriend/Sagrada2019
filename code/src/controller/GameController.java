package controller;

import java.util.ArrayList;

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
import model.PlayerPayStoneModel;
import view.GamePanes.CardPane;
import view.GamePanes.ChatPane;
import view.GamePanes.GamePane;
import view.GamePanes.PersonalAttributes;
import view.GamePanes.PlayerPane;

public class GameController {// deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook
								// de andere controllers aan ~Rens

	private ArrayList<CardPane> CardPanes = new ArrayList<CardPane>(); 
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
	private boolean currentPlayer;
	private boolean updateDice;
//	private boolean generateOffer;

	private PlayerController pc;
	private ChatPane chatPane;
	private PlayerPane pp;
	private PersonalAttributes pa;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector,
			DbPlayerCollector dpc, DbDieCollector ddc, DbDieUpdater ddu, DbTurnCollector dtc, DbPayStoneRuler psr,
			DbToolCardCollector tcc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		this.dbCardCollector = dbCardCollector;
		cc = new ChatController(dbChat, this);
		this.dbDieCollector = ddc;
		
		this.dbDieUpdater = ddu;

		ppsm = new PlayerPayStoneModel();

		pc = new PlayerController(dpc, gm);
		this.dbGameCollector = dbGamecollector;

		this.dtc = dtc;
		dtcc = tcc;
		this.psr = psr;
//		this.generateOffer = true;
		this.gameRunning = false;
		this.allPatternCards = false;
		this.currentPlayer = false;
		this.updateDice = false;
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

	public DbDieCollector getDbDieCollector() {
		return dbDieCollector;
	}

	public void createGameModel(int gameID) {
		
		String username = lc.getCurrentAccount();
		int amountOfPlayers = dbGameCollector.getAmountOfPlayers(gameID);
		GameModel gm = new GameModel(gameID, dbGameCollector, username, dpc, amountOfPlayers);
		this.gm = gm;

		int[] playerIDs = dbGameCollector.getPlayers(gameID);

		for (int i = 0; i < amountOfPlayers; i++) {
			gm.addPlayer(i, playerIDs[i], username);
			pc.setPlayerId(playerIDs[i]);
//			System.out.println("playerIds[i]" + playerIDs[i]);
		}
		pcc = new PatterncardController(DatabasePTCCollector, gm);
		lyc = new LayerController(pcc, this);
//		System.out.println("Player id in create game model: " + pc.getPlayerID());
		this.dhc = new DiceHolderController(pcc, dbDieCollector, gm.getGameId(), gm);
		createCardsController();
		guc.setGameModel(gm);
		
	}

	public void createCardsController() {
		psc = new PayStoneController(psr, DatabasePTCCollector.getPlayerID(gm.getGameId(), lc.getCurrentAccount()), gm.getGameId());
		tcc = new ToolCardController(psc, dtcc, gm.getGameId(), dhc);
		crc = new CardsController(dbCardCollector, gm.getGameId(), tcc, dhc.getDiceController().getDMAL());
		this.guc = new GameUpdateController(this);
		this.tc = new TurnController(this, dhc, dbDieUpdater, gm, dtc, lc.getCurrentAccount(), gm.getGameId(), tcc);
//		System.out.println("should be gameId: " + gm.getGameId());
	}

	public PayStoneController getPayStoneController() {
		return psc;
	}

	public void updatePaystones() {
		if (gameRunning) {
			int amount = psc.getPlayerStones();
//			System.out.println("amount: " + amount);
			if (amount != ppsm.getStones()) {
//				System.out.println("ppsm amount: " + ppsm.getStones());
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
				pcc.updateCardType(gm.getPcIdMainPlayer(lc.getUsername()));
				gamepane.updatePC();
				allPatternCards = pcc.checkAllPatternCards();
			}
		}
	}
	
	public void updateCardPane() {
		if (gameRunning) {
			int i = 0;
//			System.out.println();
//			System.out.println("now running update card pane");
//			System.out.println("Stones on card: " + CardPanes.get(i).getStonesAmount());
//			System.out.println("get stones on database: " + psc.getStonesOnCard(CardPanes.get(i).getCardNr()));
			while(i < CardPanes.size()) {
				if(CardPanes.get(i).getStonesAmount() != psc.getStonesOnCard(CardPanes.get(i).getCardNr())) {
					System.out.println("refresh the stones!");
					CardPanes.get(i).refresh(psc.getStonesOnCard(CardPanes.get(i).getCardNr()));
				}
				i++;
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
	
//	public void setGenerateOffer(boolean generateOffer) {
//		this.generateOffer = generateOffer;
//	}

	public void setMyColor() {
		if (gameRunning) {
			int playerid = pcc.getPlayerID(pcc.getGameid(), lc.getCurrentAccount());
			gamepane.setMyColor(pcc.getColor(playerid));
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

	public void addCardPane(CardPane cardpane) {
		CardPanes.add(cardpane);
	}

	public void updateFirstDice(int amountOfPlayers, int gameid) {
		dhc.getDiceController().generateOffer(amountOfPlayers, gameid);

	}

	public void updateDicePlacement() {
		if (gameRunning) {
			if (allPatternCards) {
				if(currentPlayer == false) {
					if (dhc.getDhmodels().size() == 99) {
						guc.checkDiceMovement();// update de dice models
						if (updateDice) {
							dhc.reloadDiceHolderPanes();// reload de panes van dice en diceholder die izjn opgeslagen
							gamepane.redrawDice();
							setUpdateDice(false);
						}
					}
					else {
						System.out.println("uncomplete model");
					}
					
				
					}
				
			}
			
		}
		
		
	}

	public int getGameId() {
		return gm.getGameId();
	}

	public void setCurrentPlayer(boolean b) {
		this.currentPlayer = b;
	}

	public void setUpdateDice(boolean b) {
		this.updateDice = b;
		
	}
}
