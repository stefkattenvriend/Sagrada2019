package controller;


import databeest.DbDieUpdater;
import databeest.DbTurnCollector;
import helpers.DiceHolderType;
import model.DiceHolderModel;
import model.GameModel;
import model.PlayerModel;
import view.MyScene;
import view.GamePanes.PlayerPane;

public class TurnController {

	private DiceHolderController dhc;
	private DbDieUpdater ddu;
	private GameModel gm;
	private DbTurnCollector dtc;
	private PlayerModel currentplayer;
	private String username;
	private int gameId;
	private PlayerPane pp;
	private TurnAdmissionChecker tac;
	private GameController gController;
	private DiceController diceController;
	private ToolCardController tcc;
	private MyScene myScene;

	
	public TurnController(GameController gc, DiceHolderController dhc, DbDieUpdater ddu, GameModel gm, DbTurnCollector dtc, String username, int gameId, ToolCardController tcc, MyScene myScene) {
		this.tcc = tcc;
		this.gameId = gameId;
		this.username = username;
		this.dtc = dtc;
		this.dhc = dhc;
		this.ddu = ddu;
		this.gm = gm;
		this.gController = gc;
		this.myScene = myScene;
		diceController = dhc.getDiceController();
	}
	
	public void givePane(PlayerPane pane) {
		this.pp = pane;
	}
	
	
	
	public void updateChat() {
		gController.updateChatPane();
	}

	public void updatePass() {// update na pas knop
		
		gController.setCurrentPlayer(false);
		tac.setMyTurn(false);
		dhc.switchTurnInteractable(false);
		
		DiceHolderModel dm = null;

		for (int i = 0; i < dhc.getMovedDice().size(); i++) {
			for (int j = 0; j < dhc.getDhmodels().size(); j++) {
				if (dhc.getMovedDice().get(i) == dhc.getDhmodels().get(j).getDie()) {
					dm = dhc.getDhmodels().get(j);
				}
				if (dm != null) {
					int x = dm.getX();
					int y = dm.getY();

					ddu.updateDieLocation(x, y, dhc.getMovedDice().get(i),
							gm.getPlayerModel(DiceHolderType.PLAYERWINDOW).getPlayerId(), gm.getGameId());
					dm = null;

				}
			}

		}
//		updateSeqnrAndTurn();
	}
	
	//milan
	public void updateSeqnrAndTurn() {
		PlayerModel[] players = gm.getPma();
		int amountOfPlayers = players.length;
		int seqnr = 10;
		if (amountOfPlayers == 4) {
			System.out.println(amountOfPlayers + " players detected, passing the turn to the next!");
			for (int i = 0; i < players.length; i++) {
				int x = players[i].getSeqnr();
				if (x < seqnr) {
					seqnr = x; // pakt de laagste seqnr, deze is van de huidige speler die op pass drukt.
					currentplayer = players[i]; // slaat op welke speler uit players[] de huidige speler is
				}
			}
			switch (seqnr) {
			case 1:
				currentplayer.setSeqnr(8); // eerste aan de beurt, laatste aan de beurt
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 2) {
						players[i].setCurrentPlayer(true);
					} 
					
				}
				
				break;
			case 2:
				currentplayer.setSeqnr(7);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 3) {
						players[i].setCurrentPlayer(true);
					} 
					
				}
				break;
			case 3: 
				currentplayer.setSeqnr(6);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 4) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 4:
				currentplayer.setSeqnr(5);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 5) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 5:
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 6) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 6:
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 7) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 7:
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 8) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 8:
				tcc.setAllowed(true);
				currentplayer.setCurrentPlayer(false);
				dhc.putDieOnRoundTrack();
				diceController.generateOffer(amountOfPlayers, gameId);
				gController.updateDiceOffer();
				
				if (currentplayer == players[0]) { 
					players[0].setSeqnr(4);
					players[1].setSeqnr(1);
					players[1].setCurrentPlayer(true);
					players[2].setSeqnr(2);
					players[3].setSeqnr(3);
				} else if (currentplayer == players[1]) {
					players[0].setSeqnr(3);
					players[1].setSeqnr(4);
					players[2].setSeqnr(1);
					players[2].setCurrentPlayer(true);
					players[3].setSeqnr(2);
				} else if (currentplayer == players[2]) {
					players[0].setSeqnr(2);
					players[1].setSeqnr(3);
					players[2].setSeqnr(4);
					players[3].setSeqnr(1);
					players[3].setCurrentPlayer(true);
				} else if (currentplayer == players[3]) {
					players[0].setSeqnr(1);
					players[0].setCurrentPlayer(true);
					players[1].setSeqnr(2);
					players[2].setSeqnr(3);
					players[3].setSeqnr(4);
				} 
				break;
			default: System.out.println("something went wrong here...");
			}
		}
		if (amountOfPlayers == 3) {
			System.out.println(amountOfPlayers + " players detected, passing the turn to the next player!");
			for (int i = 0; i < players.length; i++) {
				int x = players[i].getSeqnr();
				if (x < seqnr) {
					seqnr = x; // pakt de laagste seqnr, deze is van de huidige speler die op pass drukt.
					currentplayer = players[i]; // slaat op welke speler uit players[] de huidige speler is
				}
			}
			switch (seqnr) {
			case 1:
				currentplayer.setSeqnr(8);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 2) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 2:
				currentplayer.setSeqnr(7);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 3) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 3: 
				currentplayer.setSeqnr(6);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 6) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 6:
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 7) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 7:
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 8) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 8:
				tcc.setAllowed(true);
				currentplayer.setCurrentPlayer(false);
				dhc.putDieOnRoundTrack();
				diceController.generateOffer(amountOfPlayers, gameId);
				if (currentplayer == players[0]) {
					players[0].setSeqnr(3);
					players[1].setSeqnr(1);
					players[1].setCurrentPlayer(true);
					players[2].setSeqnr(2);

				} else if (currentplayer == players[1]) {
					players[0].setSeqnr(2);
					players[1].setSeqnr(3);
					players[2].setSeqnr(1);
					players[2].setCurrentPlayer(true);
				} else if (currentplayer == players[2]) {
					players[0].setSeqnr(1);
					players[0].setCurrentPlayer(true);
					players[1].setSeqnr(2);
					players[2].setSeqnr(3);
				}
				break;
			default: System.out.println("something went wrong here...");
			}
		}
		if (amountOfPlayers == 2) {
			System.out.println();
			System.out.println(amountOfPlayers + " players detected, passing the turn to the next!");
			for (int i = 0; i < players.length; i++) {
				int x = players[i].getSeqnr();
//					System.out.println("this is value x: " + x);
				if (x < seqnr) {
					seqnr = x; 
//						System.out.println("this is value seqnr: " + seqnr);
					currentplayer = players[i]; 
//						System.out.println("This is its actual seqnr: " + currentplayer.getSeqnr());
				}
			}
			switch (seqnr) {
			case 1:
				currentplayer.setSeqnr(8);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 2) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 2:
				currentplayer.setSeqnr(7);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 7) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 7: 
				currentplayer.setSeqnr(9);
				currentplayer.setCurrentPlayer(false);
				for (int i = 0; i < players.length; i++) {
					if (players[i].getSeqnr() == 8) {
						players[i].setCurrentPlayer(true);
					} 
				}
				break;
			case 8:
				tcc.setAllowed(true);
				currentplayer.setCurrentPlayer(false);
				System.out.println("--Ending the round--");
				dhc.putDieOnRoundTrack();
			
				if (currentplayer == players[0]) {
					players[0].setSeqnr(2);
					players[1].setSeqnr(1);
					players[1].setCurrentPlayer(true);

				} else if (currentplayer == players[1]) {
					players[0].setSeqnr(1);
					players[1].setSeqnr(2);
					players[0].setCurrentPlayer(true);
					
					
				} else {
					System.out.println("Something went wrong, check turncontroller 300~");
				}
				int round = diceController.getRound(gameId);
				gController.getPointsController().allowCounting();
				
				if(round == 11) {
//						System.out.println("---THE GAME HAS ENDED, YOU WIN!!!!---");
					gController.getPointsController().setEnd(true);
					gController.getPointsController().allowCounting();
					
					System.out.println("JOJOJOJOJOJOJOJOJOJO");
					myScene.setEndPane();
					
					break;
				} else {
				System.out.println("Now starting round number: " + round + ".");
				diceController.generateOffer(amountOfPlayers, gameId);
				gController.updateDiceOffer();
				}
				
				break;
	
			default: System.out.println("something went wrong here...");
			}
		}
		
		for (PlayerModel pm : players) {
			System.out.println("Player " + pm.getUsername() + " public points: " + pm.getPublicPoints());
		}
		
	}

	
	public void TurnAdmissionGiving() {
		tac = new TurnAdmissionChecker(dtc, username, gameId, dhc, pp, this, tcc, gController);

			Thread t1 = new Thread(tac);
			t1.start();
	}
	
	
	
	public PlayerModel getCurrentplayer() {
		return currentplayer;
	}

	public void startThread() {
		tac.start();
	}
	
	public void stopThread() {
		tac.stop();
	}


}
