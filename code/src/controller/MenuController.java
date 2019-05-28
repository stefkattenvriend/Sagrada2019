package controller;

import java.util.ArrayList;
import java.util.Collections;

import databeest.DataBaseApplication;
import databeest.DbGameCollector;
import view.MyScene;

public class MenuController {
	
	//instance
	private MyScene myScene;
	private MasterController mc;
	private ArrayList<String> colors; 
	private DbGameCollector dbGameCollector;
	private int gameid;
	
	public MenuController(MyScene myScene, MasterController mc, DbGameCollector dbGameCollector) {
		this.myScene = myScene;
		this.mc = mc;
		this.dbGameCollector = dbGameCollector;
		
	}
	
	public void loadGame(String gID) {
		int gameID = Integer.parseInt(gID);
		mc.getGameController().createGameModel(6);//gehardcode, moet later anders zijn aan game ID gebonden aan button
		myScene.setGamePane();
	}
	
	public void createGame() {
		//neemt username mee
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
		colors = getColors(); //maakt 5 kleuren
		dbGameCollector.pushGame();
		String challenger = playerList.get(0);
		dbGameCollector.pushFirstPlayer(challenger, colors.get(0));
		insertPublicObjectiveCards();
		insertToolCards();
		createGameDie();
		System.out.println("zise playerlist = " + playerList.size());

		for (int i = 1; i < playerList.size(); i++) {
			System.out.println(playerList.get(i));
			addPlayer(playerList.get(i), gameid, colors.get(i), i+1);
		}		
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
	
	public void addPlayer(String username, int gameid, String color, int seq) {
		dbGameCollector.addPlayer(username, gameid, color, seq);
	}

	private void createGameDie() {
		dbGameCollector.addGameDie();
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
	
	public ArrayList<Integer> getActivePlayerGames(String username)
	{
		ArrayList<Integer> activeGames = new ArrayList<>();
		
		for (int i = 0; i < dbGameCollector.startedGames().size(); i++) {
			
			int gameid = dbGameCollector.startedGames().get(i);
			for (int j = 0; j < dbGameCollector.getPlayers(gameid).length; j++) {
				
				if (dbGameCollector.getUsername(dbGameCollector.getPlayers(gameid)[j]).equals(username)) {
					activeGames.add(dbGameCollector.startedGames().get(i));
					System.out.println("active game: " + dbGameCollector.startedGames().get(i));
				}
			}
		}
		return activeGames;
	}
	
	public ArrayList<Integer> getWaitedPlayerGames(String username)
	{
		ArrayList<Integer> waitedGames = new ArrayList<>();
		
		for (int i = 0; i < dbGameCollector.waitedGames().size(); i++) {
			
			int gameid = dbGameCollector.waitedGames().get(i);
			for (int j = 0; j < dbGameCollector.getPlayers(gameid).length; j++) {
				
				if (dbGameCollector.getUsername(dbGameCollector.getPlayers(gameid)[j]).equals(username)) {
					waitedGames.add(dbGameCollector.startedGames().get(i));
					System.out.println("waited game: " + dbGameCollector.waitedGames().get(i));
				}
			}
		}
		return waitedGames;
	}
	
}

