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

	private GameController gmc;
	private GameModel gm;
	private DiceHolderController dhc;
	private DbDieCollector ddc;
	private boolean gameUpdate;

	public GameUpdateController(GameController gmc) {
		this.gmc = gmc;
		this.ddc = gmc.getDbDieCollector();
		this.dhc = gmc.getDiceHolderController();
		this.gameUpdate = false;
	}

	public void testRun() {
		if (gm != null) {
			gm.updateRound();
		}

	}

	public void setGameModel(GameModel gm) {
		this.gm = gm;
	}

	// deze methode checkt op dice movement op andere spelers
	public void checkDiceMovement() {// dit hoeft niet gedaan te worden als jij aan de beurt bent, maar maakt wss
										// niet uit(testen)
		ArrayList<DiceHolderModel> dhma = new ArrayList<DiceHolderModel>();
				dhma.addAll(dhc.getDhmodels());
		ArrayList<PlayerFieldFrameModel> pffa = ddc.getPlayerFrame(gmc.getGameId());
		ArrayList<DiceHolderModel> dhmad = new ArrayList<DiceHolderModel>();
				dhmad.addAll(dhc.getDhmodels());
				//arraylist met alle diceholder die een dice hebben
		

		if (pffa.size() == 0) {
			return;
		}

		for (int i = 0; i < dhmad.size(); i++) {
			if (dhmad.get(i).getDie() == null) {
				dhmad.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < dhma.size(); i++) {// loopt door alle diceholders

			for (int j = 0; j < pffa.size(); j++) {// loopt door alle opgehaalde playerframefields, dus alle waar iets
													// in zit

				for (int j2 = 0; j2 < gm.getAmountOfPlayers(); j2++) {// loopt door alle spelers

					if (pffa.get(j).getPlayerid() == gm.getPma()[j2].getPlayerId()) {// checkt van wie de diceholder is vergeleken met
																		// van wie het playerframefield is

						if (dhma.get(i).getType() == gm.getPma()[j2].getDht()// vergelijkt of het de DiceHolder van het
																				// type is dat verwacht wordt
								&& dhma.get(i).getX() == pffa.get(j).getX()// en checkt vervolgens of de x en y ook
																			// overeen komen
								&& dhma.get(i).getY() == pffa.get(j).getY()) {// mvc aanpassen
							// loop door de dhma, zoek een dobbelsteen in de andere dhm die overeen komt met
							// die van de pffa,
							// haal dat model daar weg en plaats deze in de juiste dhm
							for (int k = 0; k < dhmad.size(); k++) {

								if (dhmad.get(k).getDie().getDieNumber() == pffa.get(j).getDienumber()
										&& dhmad.get(k).getDie().getDieColor() == pffa.get(j).getDiecolor()) {// mvc fix

									dhma.get(i).setDie(dhmad.get(k).getDie());
									for (int k2 = 0; k2 < dhma.size(); k2++) {
										if (dhma.get(i).getDie() == dhmad.get(k).getDie()) {
											dhma.get(k2).setDie(null);
											break;
										}
									}
									
									gmc.setUpdateDice(true);

								}

							}
						}
					}
				}
			}
		}
		dhc.setDiceHolderModels(dhma);
	}

	/*
	 * public void checkDiceMovement() { oud
	 * 
	 * ArrayList<DiceHolderModel> dhma = dhc.getDhmodels();
	 * ArrayList<PlayerFieldFrameModel> pffa =
	 * ddc.getPlayerFrame(gmc.getGm().getGameId());// ombouwen met tussen //
	 * methodes
	 * 
	 * if (pffa.size() == 0) { return; }
	 * 
	 * for (int i = 0; i < dhma.size(); i++) { if (dhma.get(i).getType() ==
	 * DiceHolderType.ENEMY1) { int playerid =
	 * gmc.getGm().getPlayerModel(DiceHolderType.ENEMY1).getPlayerId();
	 * PlayerFieldFrameModel pffm = null; if (pffa.isEmpty()) { return; } for (int j
	 * = 0; j < pffa.size(); j++) {
	 * 
	 * if (pffa.get(j).getX() == dhma.get(i).getX() && pffa.get(j).getY() ==
	 * dhma.get(i).getY() && pffa.get(j).getPlayerid() == playerid) { pffm =
	 * pffa.get(j); }
	 * 
	 * else if (j == pffa.size() - 1) { break; }
	 * 
	 * } int dienumber; Color diecolor; if (dhma.get(i).getDie() == null) {
	 * dienumber = 0; diecolor = Color.WHITE; } else { dienumber =
	 * dhma.get(i).getDie().getDieNumber(); diecolor =
	 * dhma.get(i).getDie().getDieColor(); } if (dienumber != pffm.getDienumber() ||
	 * diecolor != pffm.getDiecolor()) { DiceModel die = null; for (int j = 0; j <
	 * gmc.getDiceHolderController().getDiceController().getDMAL().size(); j++) { if
	 * (gmc.getDiceHolderController().getDiceController().getDiceModel(j).
	 * getDieNumber() == pffm .getDienumber() &&
	 * gmc.getDiceHolderController().getDiceController().getDiceModel(j)
	 * .getDieColor() == pffm.getDiecolor()) {
	 * 
	 * die = gmc.getDiceHolderController().getDiceController().getDiceModel(j);
	 * 
	 * for (int j2 = 0; j2 < gmc.getDiceHolderController().getDhmodels().size();
	 * j2++) { if (gmc.getDiceHolderController().getDhmodels().get(j2).getDie() ==
	 * die) { gmc.getDiceHolderController().getDhmodels().get(j2).setDie(null);
	 * gmc.getDiceHolderController().getDhpanes().get(j2).setCenter(null); } } } }
	 * 
	 * dhma.get(i).setDie(die); gmc.getDiceHolderController().getDhpanes().get(i)
	 * .setCenter(gmc.getDiceHolderController().getDiceController().getDicePane(i));
	 * this.gameUpdate = true; } } } }
	 */
}
