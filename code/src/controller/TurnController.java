package controller;


import databeest.DbDieUpdater;
import databeest.DbTurnCollector;
import helpers.DiceHolderType;
import javafx.scene.layout.Pane;
import model.DiceHolderModel;
import model.GameModel;
import model.PlayerModel;
import view.GamePanes.PlayerPane;

public class TurnController {

	private DiceHolderController dhc;
	private DbDieUpdater ddu;
	private GameModel gm;
	private DbTurnCollector dtc;
	PlayerModel currentplayer;
	String username;
	int gameId;
	private PlayerPane pp;
	private TurnAdmissionChecker tac;
	
	public TurnController(DiceHolderController dhc, DbDieUpdater ddu, GameModel gm, DbTurnCollector dtc, String username, int gameId) {
		this.gameId = gameId;
		this.username = username;
		this.dtc = dtc;
		this.dhc = dhc;
		this.ddu = ddu;
		this.gm = gm;
	}
	
	public void givePane(PlayerPane pane) {
		this.pp = pane;
	}

	public void updatePass() {// update na pas knop
		
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
					currentplayer.setCurrentPlayer(false);
					if (currentplayer == players[0]) { //verandert de volgorde van startspeler wanner de laatste persoon past.
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
					currentplayer.setSeqnr(6);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 2) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 2:
					currentplayer.setSeqnr(5);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 3) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 3: 
					currentplayer.setSeqnr(4);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 4) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 4:
					currentplayer.setSeqnr(9);
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
					currentplayer.setCurrentPlayer(false);
					if (currentplayer == players[0]) { //verandert de volgorde van startspeler wanner de laatste persoon past.
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
				System.out.println(amountOfPlayers + " players detected, passing the turn to the next!");
				for (int i = 0; i < players.length; i++) {
					int x = players[i].getSeqnr();
//					System.out.println("this is value x: " + x);
					if (x < seqnr) {
						seqnr = x; 
						System.out.println("this is value seqnr: " + seqnr);
						currentplayer = players[i]; 
//						System.out.println("This is its actual seqnr: " + currentplayer.getSeqnr());
					}
				}
				switch (seqnr) {
				case 1:
					
					currentplayer.setSeqnr(4);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 2) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 2:
					currentplayer.setSeqnr(3);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 3) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 3: 
					currentplayer.setSeqnr(9);
					currentplayer.setCurrentPlayer(false);
					for (int i = 0; i < players.length; i++) {
						if (players[i].getSeqnr() == 4) {
							players[i].setCurrentPlayer(true);
						} 
					}
					break;
				case 4:
					currentplayer.setCurrentPlayer(false);
					if (currentplayer == players[0]) { //verandert de volgorde van startspeler wanner de laatste persoon past.
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
					break;
				default: System.out.println("something went wrong here...");
				}
			}
		}

	
	public void TurnAdmissionGiving() {
		tac = new TurnAdmissionChecker(dtc, username, gameId, dhc, pp, this);

			Thread t1 = new Thread(tac);
			t1.start();
	}

	public void startThread() {
		tac.start();
	}
	
	public void stopThread() {
		tac.stop();
	}


}
