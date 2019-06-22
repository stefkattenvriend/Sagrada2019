package controller;

import java.util.ArrayList;
import java.util.Collections;

import databeest.DataBaseApplication;
import databeest.DbGameCollector;
import databeest.DbPayStoneRuler;
import model.MenuModel;
import view.MyScene;
import view.MenuPanes.MenuGamesPane;
import view.MenuPanes.MenuInvitePane;
import view.MenuPanes.MenuPane;
import view.MenuPanes.MenuPlayersPane;
import view.MenuPanes.MenuWaitingPane;

public class MenuController {

	// instance
	private MyScene myScene;
	private MasterController mc;
	private ArrayList<String> colors;
	private DbGameCollector dbGameCollector;
	private MenuUpdateController menuUpdateController;
	private MenuPane menuPane;
	private DataBaseApplication databeest;
	private ArrayList<String> invitedGamesID_OLD;
	private ArrayList<String> invitedGames_NEW;
	private ArrayList<Integer> gameIDs_NEW;
	private ArrayList<Integer> gameIDs_OLD;
	private ArrayList<Integer> waitedGames_OLD;
	private ArrayList<Integer> waitedGames_NEW;
	private ArrayList<String> playerStatus_OLD;
	private ArrayList<String> playerStatus_NEW;
	private MenuInvitePane menuInvitePane;
	private boolean newInvite = false;
	private MenuGamesPane menuGamesPane;
	private MenuWaitingPane menuWaitingPane;
	private MenuPlayersPane menuPlayersPane;
	private MenuModel menuModel;
	private int[] randomPat;
	private DbPayStoneRuler psr;
	private boolean nextCheck = true;
	private boolean offerGenerated;
	private boolean opened;

	public MenuController(MyScene myScene, MasterController mc, DbGameCollector dbGameCollector,
			MenuUpdateController menuUpdateController, DbPayStoneRuler psr) {
		this.menuModel = new MenuModel(mc);
		this.psr = psr;
		this.myScene = myScene;
		this.mc = mc;
		this.dbGameCollector = dbGameCollector;
		this.menuUpdateController = menuUpdateController;
		databeest = mc.getDatabaseApplication();
		invitedGamesID_OLD = getInvitedGamesID();
		gameIDs_OLD = getActiveGames();
		waitedGames_OLD = getWaitedGames();
	}

	public ArrayList<String> getChallengers() {
		return menuModel.getChallengers();
	}

	public ArrayList<String> getNewChallengers() {
		return menuModel.getChallengersUpdate();
	}

	public ArrayList<String> getInvitedGamesID() {
		return menuModel.getInvitedGameIDs();
	}

	public ArrayList<String> getNewInvitedGamesID() {
		return menuModel.getInvitedGameIDsUpdate();
	}

	public ArrayList<Integer> getActiveGames() {
		return menuModel.getActiveGames();
	}

	public ArrayList<Integer> getNewActiveGames() {
		return menuModel.getActiveGamesUpdate();
	}

	public ArrayList<Integer> getWaitedGames() {
		return menuModel.getWaitedGames();
	}

	public ArrayList<Integer> getNewWaitedGames() {
		return menuModel.getWaitedGamesUpdate();
	}

	public ArrayList<String> getPlayerStatus(String gameID) {
		return menuModel.getPlayerStatus(gameID);
	}
	
	public ArrayList<String> getPlayersInGame(int gameID){
		return menuModel.getPlayersInGame(gameID);
	}
	
	public int getPlayerID(String gameID) {
		return menuModel.getPlayerID(gameID);
	}

	public ArrayList<String> getPlayers(){
		return menuModel.getPlayers();
	}
	
	public String getWinner(int gameID) {
		return menuModel.getWinner(gameID);
	}
	
	public ArrayList<Integer> getFinishedGames(String username){
		return menuModel.getFinishedGames(username);
	}
	
	public String getCurrentUsername() {
		return menuModel.getCurrentUsername();
	}
	
	public void getMenuPlayersPane(MenuPlayersPane menuPlayersPane) {
		this.menuPlayersPane = menuPlayersPane;
	}

	public void loadGame(String gID) {
		int gameID = Integer.parseInt(gID);
		
		mc.getGameController().createGameModel(gameID);
		offerGenerated = dbGameCollector.getOffer(gameID);
		
		int round = dbGameCollector.getRound(gameID);
		System.out.println("dit is het ronde nummer: " + round);// syso om ronde te checke
		String username = mc.getLoginController().getCurrentAccount();
		int playerid = databeest.getPlayerID(username, gameID);
		int patcardid = databeest.getPaternCardNumber(playerid);	//TODO mvc
		int[] choice = databeest.getPcChoiche(playerid);
		System.out.println("Loading game");
		if(!offerGenerated && mc.getGameController().checkFirstPlayer()) {
			int amountOfPlayers = dbGameCollector.getAmountOfPlayers(gameID);
			generateOffer(amountOfPlayers, gameID);
			System.out.println("Generating offer");
			offerGenerated = true;
		}
		LayerController lyc = mc.getGameController().getLayerController();

		if (choice[0] == 0) {
			lyc.generateRdmPatternCards();
			randomPat = lyc.getRandomPat();
			for (int i = 0; i < randomPat.length; i++) {
				lyc.insertChoice(randomPat[i], playerid); // zet keuzes in database
				// System.out.println("patterncardID = : " + randomPat[i]); //syso welke
				// patterncards kunnen gekozen worden

			}
			myScene.setLayerPane();
		} else {
			lyc.setRandomID(choice);
		}
		if (round == 1 && patcardid == 0) {
			myScene.setLayerPane();
		} else {
			myScene.setGamePane();
			lyc.setGameRunning(true);
			//mc.getGameController().setCurrentPlayer(false);//zorgt ervoor dat je game een keer update als je aan de beurt bent
			mc.getGameController().updateDiceOffer();
		}
	}

	public void acceptInvite(int playerid) {
		dbGameCollector.updateStatusAccept(playerid);

	}

	public void declineInvite(int playerid) {
		dbGameCollector.updateStatusIgnore(playerid);
	}

	public DataBaseApplication getDataBaseApplication() {
		return mc.getDatabaseApplication();
	}

	// milan
	public void newGame(ArrayList<String> playerList) {
		colors = getColors(); // maakt 5 kleuren
		int gameid = getGameid() + 1;
		// System.out.println(gameid);
		dbGameCollector.pushGame(gameid);
		System.out.println("dit is de gameid" + gameid);
		String challenger = playerList.get(0);
		dbGameCollector.pushFirstPlayer(challenger, colors.get(0), gameid);
		insertPublicObjectiveCards(gameid);
		insertToolCards(gameid);
		createGameDie(gameid);
		psr.addStonesToGame(gameid);
		for (int i = 1; i < playerList.size(); i++) {
			// System.out.println(playerList.get(i));
			addPlayer(playerList.get(i), gameid, colors.get(i), i + 1);
		}

		for (int i = 0; i < playerList.size(); i++) {
			addPlayerFrameField(playerList.get(i), gameid);
		}

		ArrayList<Integer> cardIds = new ArrayList<Integer>(dbGameCollector.getNormalPatternCardIds());
		Collections.shuffle(cardIds);
		ArrayList<Integer> playerIDs = new ArrayList<Integer>();
		for (int i = 0; i < playerList.size(); i++) {
			playerIDs.add(dbGameCollector.getPlayerID(gameid, playerList.get(i)));
		}

		for (int i = 0; i < playerIDs.size(); i++) {
			for (int z = 0; z < 4; z++) {
				dbGameCollector.giveCard(playerIDs.get(i), cardIds.get(0));
				cardIds.remove(0);
			}
		}

	}

	private void generateOffer(int size, int gameid) {
		mc.getGameController().updateFirstDice(size, gameid);		
	}

	private ArrayList<String> getColors() {
		ArrayList<String> colors = new ArrayList<>();
		colors = dbGameCollector.getColors();
		Collections.shuffle(colors);
		return colors;
	}

	public int getGameid() {
		int gameid = dbGameCollector.getHighestGameID();
		return gameid;
	}

	public void addPlayer(String username, int gameid, String color, int seq) {
		dbGameCollector.addPlayer(username, gameid, color, seq);
	}

	private void addPlayerFrameField(String username, int gameid) {
		System.out.println(username);
		dbGameCollector.addPlayerFrameField(username, gameid);

	}

	private void createGameDie(int gameid) {
		dbGameCollector.addGameDie(gameid);
	}

	private void insertToolCards(int gameid) {
		ArrayList<Integer> randomkaarten = generateThreeRandomUniqueNumbers(12); // van 12 nummers(aantal toolcards),
																					// geef mij er drie at random.
		for (int i = 0; i < 3; i++) {
			int x = randomkaarten.get(i);
			dbGameCollector.insertToolCards(x, gameid);
		}
	}

	public void insertPublicObjectiveCards(int gameid) {// set to private later
		ArrayList<Integer> randomkaarten = generateThreeRandomUniqueNumbers(10); // van 10 nummers, geef mij er drie at
																					// random.
		for (int i = 0; i < 3; i++) {
			int x = randomkaarten.get(i);
			dbGameCollector.insertPublicObjectiveCards(x, gameid);
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
		// System.out.println(list);//syso to check which numbers are added to database
		return list;
	}

	public void updateIncomingInvite() {
		invitedGames_NEW = getNewInvitedGamesID();

		if (menuInvitePane != null) {
			if (invitedGamesID_OLD.size() != invitedGames_NEW.size()) {
				newInvite = true;

				if (newInvite) {
					menuInvitePane.setNewInput(invitedGames_NEW, getNewChallengers());
					invitedGamesID_OLD.clear();
					invitedGamesID_OLD = invitedGames_NEW;
					System.out.println("nieuwe uitnodiging");
					newInvite = false;
				}
			}
		}
	}

	public void setInvitePane(MenuInvitePane menuInvitePane) {
		this.menuInvitePane = menuInvitePane;
	}

	public void updateActiveGames() {
		gameIDs_NEW = getNewActiveGames();
		if (menuGamesPane != null) {
			if (gameIDs_OLD.size() != gameIDs_NEW.size()) {
				newInvite = true;

				if (newInvite) {
					menuGamesPane.newActiveGames(gameIDs_NEW);
					gameIDs_OLD.clear();
					gameIDs_OLD = gameIDs_NEW;
					System.out.println("Nieuwe actieve game");
					newInvite = false;
				}

			}
		}
	}

	public void setActiveGamesPane(MenuGamesPane menuGamesPane) {
		this.menuGamesPane = menuGamesPane;
	}

	public void updateWaitedGames() {
		waitedGames_NEW = getNewWaitedGames();

		if (menuWaitingPane != null) {
			menuWaitingPane.newWaitedGames(waitedGames_NEW);
			waitedGames_OLD.clear();
			waitedGames_OLD = waitedGames_NEW;
		}

	}

	public void setWaitedGamesPane(MenuWaitingPane menuWaitingPane) {
		this.menuWaitingPane = menuWaitingPane;
	}

	public MenuModel getMenuModel() {
		return menuModel;
	}
	
	public void updatePlayerlist() {
		menuPlayersPane.updatePlayerlist();
	}
	
	public void opened(boolean opened) {
		this.opened = opened;
	}
	
	public boolean getOpened() {
		return this.opened;
	}
}
