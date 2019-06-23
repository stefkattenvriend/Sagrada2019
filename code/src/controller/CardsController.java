package controller;

import java.util.ArrayList;

import databeest.DbCardCollector;
import helpers.DiceHolderType;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.CardBackgroundModel;
import model.DiceHolderModel;
import model.DiceModel;
import view.GamePanes.CardPane;

public class CardsController {

	private DbCardCollector dbCardCollector;
	private CardBackgroundModel cardBackgroundModel;
	private ToolCardController tcc;
	private ArrayList<DiceHolderModel> dhmodels;

	public CardsController(DbCardCollector dbCardCollector, int gameId, ToolCardController tcc,
			ArrayList<DiceHolderModel> dhmodels) {
		this.dhmodels = dhmodels;
		this.tcc = tcc;
		this.dbCardCollector = dbCardCollector;
		cardBackgroundModel = new CardBackgroundModel(this.dbCardCollector, gameId);
	}

	public void useCard(CardPane cardPane) {
		tcc.useCard(cardPane);
	}

	public ImageView getTc1() {
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard1());
	}

	public ImageView getTc2() {
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard2());
	}

	public ImageView getTc3() {
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard3());
	}

	public ImageView getTgc1() {
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard1());
	}

	public ImageView getTgc2() {
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard2());
	}

	public ImageView getTgc3() {
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard3());
	}

	public Integer getTc1Nr() {
		return cardBackgroundModel.getToolCard1();
	}

	public Integer getTc2Nr() {
		return cardBackgroundModel.getToolCard2();
	}

	public Integer getTc3Nr() {
		return cardBackgroundModel.getToolCard3();
	}

	public Integer getTgc1Nr() {
		return cardBackgroundModel.TargetCard1();
	}

	public Integer getTgc2Nr() {
		return cardBackgroundModel.TargetCard2();
	}

	public Integer getTgc3Nr() {
		return cardBackgroundModel.TargetCard3();
	}

	public int getSharedObjectivePoints() { // i is de objectivecard
		int points = 0;
		int totalpoints = 0;
		for (int i = 0; i < cardBackgroundModel.getTargetCards().length; i++) {

			switch (cardBackgroundModel.getTargetCards()[i]) { // Voor objective kaart [i], voeg punten toe..

			case 1: // ,Tintvarieteit Sets van ��n van elke waarde (5 punten)
				int[] nrs = new int[] { 0, 0, 0, 0, 0, 0 };
				for (int j = 0; j < dhmodels.size(); j++) {
					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
						if (dhmodels.get(j).getDie() != null) {
							switch (dhmodels.get(j).getDie().getEyes()) {
							case 1:
								nrs[0]++;
								break;
							case 2:
								nrs[1]++;
								break;
							case 3:
								nrs[2]++;
								break;
							case 4:
								nrs[3]++;
								break;
							case 5:
								nrs[4]++;
								break;
							case 6:
								nrs[5]++;
								break;
							default:
								System.out.println(
										"error: Die nr did not fit the set boundaries (1 to 6) at CardsController line 108");

							}
						}
					}
				}
				int lowest = 4;
				for (int j = 0; j < nrs.length; j++) {
					if (nrs[j] < lowest) {
						lowest = nrs[j];
					}
				}
				points = lowest * 5;
				break;

			case 2: // Halfdonkere tinten, sets van waardes 3 & 4 ( 2 punten)
				int[] nrs1 = new int[] { 0, 0 };
				for (int j = 0; j < dhmodels.size(); j++) {
					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
						if (dhmodels.get(j).getDie() != null) {
							switch (dhmodels.get(j).getDie().getEyes()) {
							case 3:
								nrs1[0]++;
								break;
							case 4:
								nrs1[1]++;
								break;
							default:
								System.out.println("out of bounds, cardscontroll 142");
							}
						}
					}
				}
				int lowest1 = 20;
				for (int j = 0; j < nrs1.length; j++) {
					if (nrs1[j] < lowest1) {
						lowest1 = nrs1[j];
					}
				}
				points = lowest1 * 2;
			case 3: // Tintvarieteit per kolom ( 4 punten)

				points = 0;
				int[] totalEyes = new int[4];
				for (int k = 0; k < 5; k++) {
					for (int j = 0; j < dhmodels.size(); j++) {
						if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW
								&& dhmodels.get(j).getDie() != null) {
							if (dhmodels.get(j).getX() == k + 1) {
								totalEyes[dhmodels.get(j).getY()] = dhmodels.get(j).getDie().getEyes();
							}
						}
					}
					if (totalEyes[0] != totalEyes[1] && totalEyes[1] != totalEyes[2] && totalEyes[2] != totalEyes[3]
							&& totalEyes[0] != totalEyes[2] && totalEyes[1] != totalEyes[3]
							&& totalEyes[0] != totalEyes[3]) {
						points = points + 4;
					}

					for (int l = 0; l < totalEyes.length; l++) {
						totalEyes[l] = 0;
					}

				}
				break;
			case 4: // kleurvarieteit per kolom (5 punten)
				points = 0;
				Color[] diecolors = new Color[4];
				for (int k = 0; k < 5; k++) {
					for (int j = 0; j < dhmodels.size(); j++) {
						if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW
								&& dhmodels.get(j).getDie() != null) {
							if (dhmodels.get(j).getX() == k + 1) {
								diecolors[dhmodels.get(j).getY()] = dhmodels.get(j).getDie().getDieColor();
							}
						}
					}
					if (diecolors[0] != diecolors[1] && diecolors[1] != diecolors[2] && diecolors[2] != diecolors[3]
							&& diecolors[0] != diecolors[2] && diecolors[1] != diecolors[3]
							&& diecolors[0] != diecolors[3]) {
						points = points + 5;
					}

					for (int l = 0; l < diecolors.length; l++) {
						diecolors[l] = null;
					}

				}
				break;
			case 5: // Donkere tinten, sets van waardes 5 & 6 (2 punten)
				int[] nrs2 = new int[] { 0, 0 };
				for (int j = 0; j < dhmodels.size(); j++) {
					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
						if (dhmodels.get(j).getDie() != null) {
							switch (dhmodels.get(j).getDie().getEyes()) {
							case 5:
								nrs2[0]++;
								break;
							case 6:
								nrs2[1]++;
								break;
							default:
								System.out.println("out of bounds, cardscontroll 214");
							}
						}
					}
				}
				int lowest2 = 20;
				for (int j = 0; j < nrs2.length; j++) {
					if (nrs2[j] < lowest2) {
						lowest2 = nrs2[j];
					}
				}
				points = lowest2 * 2;
				break;
			case 6: // kleurvarieteit, sets van een van elke kleur ( 4 punten)
				int[] nrs3 = new int[] { 0, 0, 0, 0, 0 };
				for (int j = 0; j < dhmodels.size(); j++) {
					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
						if (dhmodels.get(j).getDie() != null) {
							switch (dhmodels.get(j).getDie().getDieColor().toString()) {
							case "0x0000ffff": // blue
								nrs3[0]++;
								break;
							case "0xff0000ff": // red
								nrs3[1]++;
								break;
							case "0x008000ff": // green
								nrs3[2]++;
								break;
							case "0x800080ff": // purple
								nrs3[3]++;
								break;
							case "0xffff00ff": // yellow
								nrs3[4]++;
								break;
							default:
								System.out.println(
										"error: Die color did not fit the set color boundaries at CardsController line 235");
							}
						}
					}
				}
				int least = 4;
				for (int j = 0; j < nrs3.length; j++) {
					if (nrs3[j] < least) {
						least = nrs3[j];
					}
				}
				points = least * 4;
				break;
			case 7: // kleurvarieteit per rij, rijen zonder herhaalde kleuren( 6 punten)
				points = 0; // TODO wordt twee keer uitgevoerd geloof ik ~ milan.
				Color[] diecolors1 = new Color[5]; // Array out of bounds (4) changed array to [5]
				for (int k = 0; k < 4; k++) { // dit is y
					for (int j = 0; j < dhmodels.size(); j++) {
						if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW
								&& dhmodels.get(j).getDie() != null) {
							if (dhmodels.get(j).getY() == k + 1) {
								diecolors1[dhmodels.get(j).getX() - 1] = dhmodels.get(j).getDie().getDieColor(); // Array
																													// out
																													// of
																													// bounds,
																													// added
																													// (
																													// -
																													// 1)
							}
						}
					}

					if (diecolors1[0] != diecolors1[1] && diecolors1[1] != diecolors1[2]
							&& diecolors1[2] != diecolors1[3] && diecolors1[3] != diecolors1[4]
							&& diecolors1[0] != diecolors1[2] && diecolors1[1] != diecolors1[3]
							&& diecolors1[2] != diecolors1[4] && diecolors1[0] != diecolors1[3]
							&& diecolors1[1] != diecolors1[4] && diecolors1[0] != diecolors1[4]) {

						if (diecolors1[0] != null && diecolors1[1] != null && diecolors1[2] != null
								&& diecolors1[3] != null && diecolors1[4] != null) {
							points = points + 3;

						}
					}

					for (int l = 0; l < diecolors1.length; l++) {
						diecolors1[l] = null;
					}

				}
				break;

			case 8: // kleurdiagonalen, aantal diagonaal aangrenzende stene in dezelfde kleur ( #
					// punten )
				for (int j = 0; j < dhmodels.size(); j++) {

					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(j).getDie() != null) {
						int X = dhmodels.get(j).getX();
						int Y = dhmodels.get(j).getY();
						for (int k = 0; k < dhmodels.size(); k++) {
							if (dhmodels.get(k).getType() == DiceHolderType.PLAYERWINDOW
									&& dhmodels.get(k).getDie() != null) {
								if (dhmodels.get(k).getX() == X - 1 && dhmodels.get(k).getY() == Y - 1
										|| dhmodels.get(k).getX() == X - 1 && dhmodels.get(k).getY() == Y + 1
										|| dhmodels.get(k).getX() == X + 1 && dhmodels.get(k).getY() == Y - 1
										|| dhmodels.get(k).getX() == X + 1 && dhmodels.get(k).getY() == Y + 1) {
									if (dhmodels.get(k).getDie().getDieColor() == dhmodels.get(j).getDie()
											.getDieColor()) {

										points++;

									}
								}
							}
						}
					}
				}

				break;
			case 9: // lichte tinten, waardes van 1 & 2 ( 2punten)
				int[] nrs4 = new int[] { 0, 0 };
				for (int j = 0; j < dhmodels.size(); j++) {
					if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
						if (dhmodels.get(j).getDie() != null) {
							switch (dhmodels.get(j).getDie().getEyes()) {
							case 1:
								nrs4[0]++;
								break;
							case 2:
								nrs4[1]++;
								break;
							default:
								break;
							}
						}
					}
				}
				int lowest3 = 20;
				for (int j = 0; j < nrs4.length; j++) {
					if (nrs4[j] < lowest3) {
						lowest3 = nrs4[j];
					}
				}
				points = lowest3 * 2;
				break;

			case 10: // tintvarieteit per rij ( 5 punten)
				points = 0;
				int[] totalEyes1 = new int[5];
				for (int k = 0; k < 5; k++) {
					for (int j = 0; j < dhmodels.size(); j++) {
						if (dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW
								&& dhmodels.get(j).getDie() != null) {
							if (dhmodels.get(j).getX() == k + 1) {
								totalEyes1[dhmodels.get(j).getX() - 1] = dhmodels.get(j).getDie().getDieNumber(); // Changed
																													// getY
																													// to
																													// getX
																													// and
																													// added
																													// -1
							}
						}
					}

					if (totalEyes1[0] != totalEyes1[1] && totalEyes1[1] != totalEyes1[2]
							&& totalEyes1[2] != totalEyes1[3] && totalEyes1[3] != totalEyes1[4]
							&& totalEyes1[0] != totalEyes1[2] && totalEyes1[1] != totalEyes1[3]
							&& totalEyes1[2] != totalEyes1[4] && totalEyes1[0] != totalEyes1[3]
							&& totalEyes1[1] != totalEyes1[4] && totalEyes1[0] != totalEyes1[4]) {
						points += 6;
					}

					for (int l = 0; l < totalEyes1.length; l++) {
						totalEyes1[l] = 0;
					}

				}
				break;
			}
			totalpoints += points;
		}
		return totalpoints;
	}
}
