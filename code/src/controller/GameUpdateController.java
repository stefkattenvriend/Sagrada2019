package controller;

import java.util.ArrayList;

import databeest.DbDieCollector;
import helpers.DiceHolderType;
import javafx.scene.paint.Color;
import model.DiceHolderModel;
import model.DiceModel;
import model.GameModel;
import model.PlayerFieldFrameModel;
import view.GamePanes.DicePane;

public class GameUpdateController {
	// beurt
	// chat
	//

	private MasterController mc;
	private GameModel gm;
	private DiceHolderController dhc;
	private DbDieCollector ddc;

	public GameUpdateController(MasterController mc) {
		this.mc = mc;
		this.ddc = mc.getDbDieCollector();
		this.dhc = mc.getGameController().getDiceHolderController();
	}

	public void testRun() {
		if (gm != null) {
			gm.updateRound();
		}

	}

	public void setGameModel(GameModel gm) {

		this.gm = gm;
	}

	public void checkDiceMovement() {
		
		ArrayList<DiceHolderModel> dhma = dhc.getDhmodels();
		ArrayList<PlayerFieldFrameModel> pffa = ddc.getPlayerFrame(mc.getGameController().getGm().getGameId());

		
		
		for (int i = 0; i < dhma.size(); i++) {
			if (dhma.get(i).getType() == DiceHolderType.ENEMY1) {
				int playerid = mc.getGameController().getGm().getPlayerModel(DiceHolderType.ENEMY1).getPlayerId();
				PlayerFieldFrameModel pffm = null;
				if (pffa.isEmpty()) {
							return;
						}
				for (int j = 0; j < pffa.size(); j++) {

					if (pffa.get(j).getX() == dhma.get(i).getX() && pffa.get(j).getY() == dhma.get(i).getY()
							&& pffa.get(j).getPlayerid() == playerid) {
						pffm = pffa.get(j);
					}
					else if (j == pffa.size() - 1) {
						return;
					}
				}
				int dienumber;
				Color diecolor;
				if (dhma.get(i).getDie() == null) {
					dienumber = 0;
					diecolor = Color.WHITE;
				}else {
					dienumber = dhma.get(i).getDie().getDieNumber();
					diecolor = dhma.get(i).getDie().getDieColor();
				}
				if (dienumber != pffm.getDienumber()
						|| diecolor != pffm.getDiecolor()) {
					DiceModel die = null;
					DicePane dp = null;
					for (int j = 0; j < mc.getGameController().getDiceHolderController().getDiceController().getDMAL()
							.size(); j++) {
						if (mc.getGameController().getDiceHolderController().getDiceController().getDiceModel(j)
								.getDieNumber() == pffm.getDienumber()
								&& mc.getGameController().getDiceHolderController().getDiceController().getDiceModel(j)
										.getDieColor() == pffm.getDiecolor()) {
							
							die = mc.getGameController().getDiceHolderController().getDiceController().getDiceModel(j);
							
							dp = mc.getGameController().getDiceHolderController().getDiceController().getDicePane(j);
							
							for (int j2 = 0; j2 < mc.getGameController().getDiceHolderController().getDhmodels().size(); j2++) {
								if (mc.getGameController().getDiceHolderController().getDhmodels().get(j2).getDie() == die) {
									mc.getGameController().getDiceHolderController().getDhmodels().get(j2).setDie(null);
									if (mc.getGameController().getDiceHolderController().getDhpanes().get(j2).getCenter() != null) {
										mc.getGameController().getDiceHolderController().changeDie(j2, null);
									}
									
								}
							}
							
							
							break;
						}
					}

					dhma.get(i).setDie(die);
					mc.getGameController().getDiceHolderController().changeDie(i, dp);
				}
			}
		}
	}
}
