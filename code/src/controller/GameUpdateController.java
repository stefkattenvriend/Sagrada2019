package controller;

import java.util.ArrayList;

import databeest.DbDieCollector;
import helpers.DiceHolderType;
import model.DiceHolderModel;
import model.DiceModel;
import model.GameModel;
import model.PlayerFieldFrameModel;

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
			// gm.updateRound();
		}

	}

	public void setGameModel(GameModel gm) {
		this.gm = gm;
	}

	// deze methode checkt op dice movement op andere spelers
	public void checkDiceMovementPlayerFields() {// dit hoeft niet gedaan te worden als jij aan de beurt bent, maar
													// maakt wss
		// niet uit(testen)
		ArrayList<DiceHolderModel> dhma = new ArrayList<DiceHolderModel>();
		dhma.addAll(dhc.getDhmodels());

		ArrayList<PlayerFieldFrameModel> pffa = ddc.getPlayerFrame(gmc.getGameId());

		ArrayList<DiceHolderModel> dhmad = new ArrayList<DiceHolderModel>(dhma);
		// arraylist met alle diceholder die een dice hebben

		if (pffa.size() == 0) {
			return;
		}

		for (int z = 0; z < dhmad.size(); z++) {
			if (dhmad.get(z).getDie() == null) {
				dhmad.remove(z);
				z--;
			}
		}

		for (int i = 0; i < dhma.size(); i++) {// loopt door alle diceholders

			for (int j = 0; j < pffa.size(); j++) {// loopt door alle opgehaalde playerframefields, dus alle waar iets
													// in zit

				for (int j2 = 0; j2 < gm.getAmountOfPlayers(); j2++) {// loopt door alle spelers

					if (pffa.get(j).getPlayerid() == gm.getPma()[j2].getPlayerId()) {// checkt van wie de diceholder is
																						// vergeleken met
						// van wie het playerframefield is

						if (dhma.get(i).getType() == gm.getPma()[j2].getDht()// vergelijkt of het de DiceHolder van het
																				// type is dat verwacht wordt
								&& dhma.get(i).getX() == pffa.get(j).getX()// en checkt vervolgens of de x en y ook
																			// overeen komen
								&& dhma.get(i).getY() == pffa.get(j).getY()) {// mvc aanpassen
							// loop door de dhma, zoek een dobbelsteen in de andere dhm die overeen komt met
							// die van de pffa,
							// haal dat model daar weg en plaats deze in de juiste dhm
							if (dhmad.size() == 0 || gmc.getNewRound()) {// wss mis
								ArrayList<DiceModel> dicelist = dhc.getDiceController().getDMAL();
								for (int k = 0; k < dicelist.size(); k++) {
									if (dicelist.get(k).getDieNumber() == pffa.get(j).getDienumber()
											&& dicelist.get(k).getDieColor() == pffa.get(j).getDiecolor()) {
										dhma.get(i).setDie(dicelist.get(k));
									}

								}
								gmc.setNewRound(false);
							} else {
								for (int k = 0; k < dhmad.size(); k++) {

									if (k == dhmad.size()) {
										System.out.println("Mistake on k:" + k);
										break;
									}

									if (dhmad.get(k).getDie().getDieNumber() == pffa.get(j).getDienumber()
											&& dhmad.get(k).getDie().getDieColor() == pffa.get(j).getDiecolor()) {

										dhma.get(i).setDie(dhmad.get(k).getDie());

										/*
										 * for (int k2 = 0; k2 < dhma.size(); k2++) { if (dhma.get(k2).getDie() ==
										 * dhmad.get(k).getDie() && dhma.get(k2).getType() != dhmad.get(k).getType() &&
										 * dhma.get(k2).getX() != dhmad.get(k).getX() && dhma.get(k2).getY() !=
										 * dhmad.get(k).getY()) { dhma.get(k2).setDie(null); break; }
										 * 
										 * }
										 */

									}
								}
							}
							gmc.setUpdateDice(true);

						}

						for (int k = 0; k < dhma.size(); k++) {
							if (dhma.get(k).getDie() == null) {
								continue;
							}
							for (int k2 = 0; k2 < pffa.size(); k2++) {
								if (dhma.get(k).getDie().getDieNumber() == pffa.get(k2).getDienumber()
										&& dhma.get(k).getDie().getDieNumber() == pffa.get(k2).getDienumber()) {
									if (pffa.get(k2).getX() == dhma.get(k).getX()
											&& pffa.get(k2).getY() == dhma.get(k).getY()) {
										if (dhma.get(k).getType() == DiceHolderType.OFFER
												|| dhma.get(k).getType() == DiceHolderType.ROUNDTRACK) {
											dhma.get(k).setDie(null);
										}

									}
								}
							}
						}

					}
				}
			}
		}
		dhc.setDiceHolderModels(dhma);
	}

	public void getDiceOffer(int round) {
		ArrayList<DiceModel> dmnew = ddc.getDiceOffer(gmc.getGameId(), round);
		ArrayList<DiceModel> dm = new ArrayList<DiceModel>();
		dm.addAll(dhc.getDiceController().getDMAL());
		ArrayList<DiceModel> offer = new ArrayList<DiceModel>();

		// loop door alle opgehaalde dice, update de dice models, plaats de geupdate
		// models in offer

		for (int i = 0; i < dmnew.size(); i++) {
			for (int j = 0; j < dm.size(); j++) {
				if (dmnew.get(i).getDieNumber() == dm.get(j).getDieNumber()
						&& dmnew.get(i).getDieColor() == dm.get(j).getDieColor()) {
					dm.get(j).setEyes(dmnew.get(i).getEyes());
					offer.add(dm.get(j));
					break;
				}
			}
		}

		ArrayList<DiceHolderModel> dhm = dhc.getDhmodels();

		int z = 0;

		for (int i = 0; i < dhm.size(); i++) {
			if (z < offer.size()) {
				if (dhm.get(i).getType() == DiceHolderType.OFFER) {
					dhm.get(i).setDie(offer.get(z));
					z++;
				}

			}

		}
	}

	public void reloadRoundTrack() {
		ArrayList<DiceModel> roundtrackdb = ddc.getRoundTrack(gmc.getGameId());
		// kijk naar de opgehaalde dice, plaats deze op de juiste plek in de roundtrack
		int count = 0;
		ArrayList<DiceHolderModel> dhm = dhc.getDhmodels();

		for (int i = 0; i < dhm.size(); i++) {
			if (count == roundtrackdb.size()) {
				dhc.setDiceHolderModels(dhm);
				return;
			}
			if (dhm.get(i).getType() == DiceHolderType.ROUNDTRACK) {
				for (int z = 0; z < roundtrackdb.size(); z++) {
					if (dhm.get(i).getX() == roundtrackdb.get(z).getRoundtrack()) {
						DiceModel die = roundtrackdb.get(z);
						dhm.get(i).setDie(die);// zet op moment max 1 die
						count++;
						gmc.setUpdateDice(true);
						break;
						
					}
				}
			}
		}
	}
}
