package controller;

import databeest.DbDieUpdater;
import helpers.DiceHolderType;
import model.DiceHolderModel;
import model.GameModel;

public class TurnController {

	DiceHolderController dhc;
	DbDieUpdater ddu;
	GameModel gm;

	public TurnController(DiceHolderController dhc, DbDieUpdater ddu, GameModel gm) {
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

				}
			}

		}
	}

}
