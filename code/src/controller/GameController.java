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
import helpers.DiceHolderType;
import model.GameModel;
import model.PlayerModel;
import model.PlayerPayStoneModel;
import view.GamePanes.CardPane;
import view.GamePanes.ChatPane;
import view.GamePanes.GamePane;
import view.GamePanes.PersonalAttributes;

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
	private PlayerPaneController ppc;
	private boolean newRound;
	// private boolean generateOffer;
	// private boolean generateOffer;
	private int old_round;
	private boolean forceUpdate;

	private PlayerController pc;
	private ChatPane chatPane;
	private PersonalAttributes pa;
	private MasterController master;

	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector,
			LoginController lc, DbChatCollector dbChat, DbCardCollector dbCardCollector, DbPlayerCollector dpc,
			DbDieCollector ddc, DbDieUpdater ddu, DbTurnCollector dtc, DbPayStoneRuler psr, DbToolCardCollector tcc,
			MasterController masterController) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.dpc = dpc;
		this.lc = lc;
		this.dbCardCollector = dbCardCollector;
		cc = new ChatController(dbChat, this);
		this.dbDieCollector = ddc;
		this.dbDieUpdater = ddu;
		this.master = masterController;

		ppc = new PlayerPaneController();
		ppsm = new PlayerPayStoneModel();

		this.dbGameCollector = dbGamecollector;

		this.dtc = dtc;
		dtcc = tcc;
		this.psr = psr;
		// this.generateOffer = true;
		this.gameRunning = false;
		this.allPatternCards = false;
		this.currentPlayer = false;
		this.updateDice = false;
		this.forceUpdate = false;

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

	public PlayerPaneController getPlayerPaneController() {
		return ppc;
	}

	public LayerController getLayerController() {
		return lyc;
	}

	public ChatController getChatController() {
		return cc;
	}

	public void createPrivateObjective() { // TODO wat gebeurt hiermee?

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
		tcc = new ToolCardController(psc, dtcc, dhc, this);
		pc = new PlayerController(dpc, gm, tcc, this);

		String username = lc.getCurrentAccount();
		int amountOfPlayers = dbGameCollector.getAmountOfPlayers(gameID);
		GameModel gm = new GameModel(gameID, dbGameCollector, username, dpc, amountOfPlayers, tcc, this);
		this.gm = gm;

		int[] playerIDs = dbGameCollector.getPlayers(gameID);

		for (int i = 0; i < amountOfPlayers; i++) {
			gm.addPlayer(i, playerIDs[i], username);
			pc.setPlayerId(playerIDs[i]);

		}
		pcc = new PatterncardController(DatabasePTCCollector, gm);
		lyc = new LayerController(pcc, this);
		this.dhc = new DiceHolderController(pcc, dbDieCollector, gm.getGameId(), gm, dbDieUpdater, this);
		createCardsController();
		guc.setGameModel(gm);
		this.ptsc = new PointsController(this);

		old_round = gm.getGameRound() - 1;

	}

	public void createCardsController() {
		psc = new PayStoneController(psr, DatabasePTCCollector.getPlayerID(gm.getGameId(), lc.getCurrentAccount()),
				gm.getGameId());
		tcc = new ToolCardController(psc, dtcc, dhc, this);
		crc = new CardsController(dbCardCollector, gm.getGameId(), tcc, dhc.getDhmodels());
		this.guc = new GameUpdateController(this);
		this.tc = new TurnController(this, dhc, dbDieUpdater, gm, dtc, lc.getCurrentAccount(), gm.getGameId(), tcc);
	}

	public PayStoneController getPayStoneController() {
		return psc;
	}

	public void updatePaystones() {
		if (gameRunning) {
			int amount = psc.getPlayerStones();
			if (amount != ppsm.getStones()) {
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

			while (i < CardPanes.size()) {
				if (CardPanes.get(i).getStonesAmount() != psc.getStonesOnCard(CardPanes.get(i).getCardNr())) {
					CardPanes.get(i).refresh(psc.getStonesOnCard(CardPanes.get(i).getCardNr()));
				}
				i++;
			}
		}
	}

	public void setGamepane(GamePane gamepane) {
		this.gamepane = gamepane;
		allPatternCards = false;// zorgt ervoor dat patterncards opnieuw worden opgehaald als je een tweede game
								// opent
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	// public void setGenerateOffer(boolean generateOffer) {
	// this.generateOffer = generateOffer;
	// }

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
				if (currentPlayer == false || forceUpdate == true) {
					if (dhc.getDhmodels().size() == 99) {
						guc.checkDiceMovementPlayerFields();// update de dice models

						dhc.reloadDiceHolderPanes();// reload de panes van dice en diceholder die izjn opgeslagen
						gamepane.redrawDice();
						setUpdateDice(false);
						dhc.reloadInteractability(); // zorgt ervoor dat niet alle panes met dobbelstenen erin enzo
														// interactable zijn
						if (forceUpdate) {
							forceUpdate = false;
							forcedUpdateDice();
						}

					} else {

					}
				}
			}
		}
	}

	public int getGameId() {
		return gm.getGameId();
	}

	public boolean isCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(boolean b) {
		this.currentPlayer = b;
		gm.setCurPlayer(b);
		if (gamepane != null && b) {
			gamepane.yourTurn(); // hoort de playerpane groen of rood te zetten als je aan de beurt bent of niet
		} else if (gamepane != null && !b) {
			gamepane.notYourTurn();
		}
		if (b == true) {
			forceUpdate = true;
		}
	}

	public void setUpdateDice(boolean b) {
		this.updateDice = b;
	}

	public ToolCardController getToolCardController() {
		return tcc;
	}

	public MasterController getMasterController() {
		return master;
	}

	public void updateDiceOffer() {
		if (gameRunning) {
			if (allPatternCards) {
				if (dhc.getDhmodels().size() == 99) {
					if (old_round < gm.getGameRound()) {
						guc.getDiceOffer(gm.getGameRound());// update de dice in de offerpane
						old_round++;
						dhc.reloadDiceHolderPanes();
						gamepane.redrawDice();
						// updateRoundtrack(old_round);
						forcedUpdateDice();
					}
				} else {

				}
			}
		}

	}

	public void forcedUpdateDice() {
		if (gameRunning) {
			if (allPatternCards) {
				if (dhc.getDhmodels().size() == 99) {

					guc.checkDiceMovementPlayerFields();
					guc.reloadRoundTrack();
					if (updateDice) {
						dhc.reloadDiceHolderPanes();// reload de panes van dice en diceholder die izjn opgeslagen
						gamepane.redrawDice();
						if (currentPlayer) {
							dhc.reloadInteractability();
						}
					}
				}
			}
		}
	}

	public void createRoundOffer() {
		dhc.getDiceController().generateOffer(gm.getAmountOfPlayers(), gm.getGameId());
	}

	public void updateGameRound() {
		if (gameRunning) {
			if (allPatternCards) {
				gm.updateRound(this);

			}
		}
	}

	public PlayerModel getPlayerModel() {
		return pc.getPM();
	}

	// public void updateColors() {
	// if(gamepane != null && currentPlayer) {
	// gamepane.yourTurn(); //hoort de playerpane groen of rood te zetten als je aan
	// de beurt bent of niet
	// } else if (gamepane != null && !currentPlayer) {
	// gamepane.notYourTurn();
	// }
	//
	// }
	public void updateRoundtrack(int oldRoundId) {

		guc.reloadRoundTrack();
		this.forcedUpdateDice();

	}

	public DbPlayerCollector getdbPlayerCollector() {
		return dpc;
	}

	public boolean checkFirstPlayer() {
		if (gm.getPlayerModel(DiceHolderType.PLAYERWINDOW).getSeqnr() == 1) {
			return true;
		} else {
			return false;
		}

	}

	public void updateNewRound(boolean b) {
		this.newRound = b;

	}

	public boolean getNewRound() {
		return newRound;
	}

	public void setNewRound(boolean b) {
		this.newRound = false;
	}

	public void putDieOnRoundTrack() {
		dbDieUpdater.putDieOnRoundtrack(gm.getGameRound(), gm.getGameId(), dhc.getDiceController().getDMAL());

	}

}
