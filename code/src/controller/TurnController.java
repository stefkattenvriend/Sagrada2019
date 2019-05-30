package controller;

import databeest.DbDieUpdater;
import databeest.DbTurnCollector;
import helpers.DiceHolderType;
import model.DiceHolderModel;
import model.GameModel;
import model.PlayerModel;

public class TurnController {

	DiceHolderController dhc;
	DbDieUpdater ddu;
	GameModel gm;
	DbTurnCollector dtc;
	PlayerModel currentplayer;
	String username;
	int gameId;

	public TurnController(DiceHolderController dhc, DbDieUpdater ddu, GameModel gm, DbTurnCollector dtc, String username, int gameId) {
		this.gameId = gameId;
		this.username = username;
		this.dtc = dtc;
		this.dhc = dhc;
		this.ddu = ddu;
		this.gm = gm;
	}

	public void updatePass() {// update na pas knop
		System.out.println("Test");

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
		updateSeqnr();
	}
	
	//milan
	private void updateSeqnr() {
		PlayerModel[] players = gm.getPma();
		int amountOfPlayers = players.length;
		int seqnr = 8;
		if (amountOfPlayers == 4) {
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
				break;
			case 2:
				currentplayer.setSeqnr(7);
				break;
			case 3: 
				currentplayer.setSeqnr(6);
				break;
			case 4:
				currentplayer.setSeqnr(5);
				break;
			case 8:
				if (currentplayer == players[0]) { //verandert de volgorde van startspeler wanner de laatste persoon past.
					players[0].setSeqnr(4);
					players[1].setSeqnr(1);
					players[2].setSeqnr(2);
					players[3].setSeqnr(3);
				} else if (currentplayer == players[1]) {
					players[0].setSeqnr(3);
					players[1].setSeqnr(4);
					players[2].setSeqnr(1);
					players[3].setSeqnr(2);
				} else if (currentplayer == players[2]) {
					players[0].setSeqnr(2);
					players[1].setSeqnr(3);
					players[2].setSeqnr(4);
					players[3].setSeqnr(1);
				} else if (currentplayer == players[3]) {
					players[0].setSeqnr(1);
					players[1].setSeqnr(2);
					players[2].setSeqnr(3);
					players[3].setSeqnr(4);
				} 
				break;
			}
		}
	}
	
	public void TurnAdmissionGiving() {
		TurnAdmissionChecker tac = new TurnAdmissionChecker(dtc, username, gameId);

			Thread t1 = new Thread(tac);
			t1.start();
	}

}
