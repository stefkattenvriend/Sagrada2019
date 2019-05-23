package controller;

import java.util.ArrayList;

import helpers.DiceHolderType;
import helpers.PatterncardType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.DiceHolderModel;
import model.DiceModel;
import view.GamePanes.DiceHolderPane;

public class DiceHolderController {

	private ArrayList<DiceHolderModel> dhmodels = new ArrayList<DiceHolderModel>();
	private ArrayList<DiceHolderPane> dhpanes = new ArrayList<DiceHolderPane>();
	private DiceController dc = new DiceController();
	private PatterncardController pcc;
	
	public DiceHolderController(PatterncardController pcc) {
		this.pcc = pcc;
	}

	public DiceHolderPane CreateDiceHolder(double size, int x, int y, DiceHolderType type) {// deze methode maakt de
																							// diceHolder model en pane
																							// aan en geeft de pane
																							// terug aan de view
		DiceHolderModel model = new DiceHolderModel(null, x, y, type);
		DiceHolderPane pane = new DiceHolderPane(size, this);
		dhmodels.add(model);
		dhpanes.add(pane);
		return pane;
	}

	public void DiceHolderClick(DiceHolderPane dp) {
		DiceHolderModel selectedModel = null;

		for (int i = 0; i < dhpanes.size(); i++) {// haalt de model van de gekozen op.
			if (dp == dhpanes.get(i)) {
				selectedModel = dhmodels.get(i);
				break;
			}
		}

		// if er een gereedschapskaart actief is
		// welke kaart is actief

		if (selectedModel.getSelected() == false) {// check of er al een ander vak geselcteerd was
			for (int i = 0; i < dhmodels.size(); i++) {
				if (dhmodels.get(i).getSelected() == true) {
					if (selectedModel.getDie() == null && dhmodels.get(i).getDie() == null) {// switch tussen de
																								// selected van 2 lege vakjes
						dhmodels.get(i).switchSelected();
						dhpanes.get(i).setBackground(null);// zet background en selected status naar null van de pane
															// die eerder selected was

						selectedModel.switchSelected();
						dp.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 10, 0.8), null, null)));

						return;

					} else if (selectedModel.getDie() == null && dhmodels.get(i).getDie() != null) {// switch een
																									// dobbelsteen
						
						if(checkDiceMovement(selectedModel, dhmodels.get(i).getDie()) == true) {
							selectedModel.setDie(dhmodels.get(i).getDie());// wiselt de models
						dhmodels.get(i).setDie(null);

						dhmodels.get(i).switchSelected();// zet achtergrond en selected naar nul van oude pane
						dhpanes.get(i).setBackground(null);

						dp.setCenter(dhpanes.get(i).getCenter());// wiselt de panes
						dhpanes.get(i).setCenter(null);

						return;
						}else {
							dhmodels.get(i).switchSelected();
							dhpanes.get(i).setBackground(null);// zet background en selected status naar null van de pane
																// die eerder selected was

							selectedModel.switchSelected();
							dp.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 10, 0.8), null, null)));

							return;
						}
						
						
					} else if (selectedModel.getDie() != null && dhmodels.get(i).getDie() == null) {
						
						dhmodels.get(i).switchSelected();
						dhpanes.get(i).setBackground(null);// zet background en selected status naar null van de pane
															// die eerder selected was

						selectedModel.switchSelected();
						dp.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 10, 0.8), null, null)));

						
						return;
					} else if (selectedModel.getDie() != null && dhmodels.get(i).getDie() != null) {
						
						dhmodels.get(i).switchSelected();
						dhpanes.get(i).setBackground(null);// zet background en selected status naar null van de pane
															// die eerder selected was

						selectedModel.switchSelected();
						dp.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 10, 0.8), null, null)));

						
						return;
					}
				}
			}

			selectedModel.switchSelected();
			dp.setBackground(new Background(new BackgroundFill(Color.rgb(10, 10, 10, 0.8), null, null)));

		} else if(selectedModel.getSelected() == true) {
			selectedModel.switchSelected();
			dp.setBackground(null);
		}

		

	}

	public void DiceHolderHover(DiceHolderPane dp) {
		dp.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153, 0.5), null, null)));
	}

	public void DiceHolderLeaveHover(DiceHolderPane dp) {
		for (int i = 0; i < dhpanes.size(); i++) {
			if (dp == dhpanes.get(i)) {
				if (dhmodels.get(i).getSelected() == false) {
					dp.setBackground(null);
				}
			}
		}

	}

	public void addDie(DiceHolderType location, int x, int y, int color, int eyes, boolean interactable) {
		DiceModel die = dc.createDieModel(color, eyes);
		for (int i = 0; i < dhmodels.size(); i++) {
			if (dhmodels.get(i).getType() == location && dhmodels.get(i).getX() == x && dhmodels.get(i).getY() == y) {
				dhmodels.get(i).setDie(die);
				dhpanes.get(i).setCenter(dc.createDicePane(die));
			}
			
		}
	}
	
	public boolean checkDiceMovement(DiceHolderModel location, DiceModel die) {
		boolean check = true;
			if(location.getType() == DiceHolderType.PLAYERWINDOW) {
		
		for (int i = 0; i < pcc.getPcModelsSize(); i++) {//vergelijkt kleur van patroonkaart en die
			if(location.getX() == pcc.getPcModel(i).getX() && location.getY() == pcc.getPcModel(i).getY() && pcc.getPcModel(i).getPct() == PatterncardType.PLAYER) {
					if(die.getPaint() != pcc.getPcModel(i).getColor() && pcc.getPcModel(i).getColor() != Color.WHITE) {
						check = false;
						return check;
				}
			}
			
			
		}
		
		for (int i = 0; i < pcc.getPcModelsSize(); i++) {//vergelijkt waarde van patroonkaart en die
			if(location.getX() == pcc.getPcModel(i).getX() && location.getY() == pcc.getPcModel(i).getY() && pcc.getPcModel(i).getPct() == PatterncardType.PLAYER) {
				if(die.getEyes() != pcc.getPcModel(i).getNumber() && pcc.getPcModel(i).getNumber() != 0) {
					check = false;
					return check;
				}
			}
		}
		
	
				//krijg de linker
		if(location.getX() != 1) {
			for (int i = 0; i < dhmodels.size(); i++) {
				if(dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(i).getY() == location.getY() && dhmodels.get(i).getX() == (location.getX() -1)) {
					if (dhmodels.get(i).getDie() != null) {
						DiceModel leftDie = dhmodels.get(i).getDie();
							if (leftDie == die) {
								break;
							}else if (leftDie.getEyes() == die.getEyes() || leftDie.getPaint() == die.getPaint()) {
								check = false;
								return check;
							}
						}
					}
				}
			}
		//krijg rechter
		if(location.getX() != 5) {
			for (int i = 0; i < dhmodels.size(); i++) {
				if(dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(i).getY() == location.getY() && dhmodels.get(i).getX() == (location.getX() + 1)) {
					if (dhmodels.get(i).getDie() != null) {
						DiceModel rightDie = dhmodels.get(i).getDie();
						if (rightDie == die) {
							break;
						}else if (rightDie.getEyes() == die.getEyes() || rightDie.getPaint() == die.getPaint()) {
								check = false;
								return check;
							}
						}
					}
				}
			}
		//krijg boven
		if(location.getY() != 1) {
			for (int i = 0; i < dhmodels.size(); i++) {
				if(dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(i).getY() == location.getY() - 1 && dhmodels.get(i).getX() == location.getX()) {
					if (dhmodels.get(i).getDie() != null) {
						DiceModel topDie = dhmodels.get(i).getDie();
						if (topDie == die) {
							break;
						}else if (topDie.getEyes() == die.getEyes() || topDie.getPaint() == die.getPaint()) {
								check = false;
								return check;
							}
						}
					}
				}
			}
		//krijg onder
		if(location.getY() != 4) {
			for (int i = 0; i < dhmodels.size(); i++) {
				if(dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(i).getY() == location.getY() + 1 && dhmodels.get(i).getX() == location.getX()) {
					if (dhmodels.get(i).getDie() != null) {
						DiceModel bottomDie = dhmodels.get(i).getDie();
						if (bottomDie == die) {
							break;
						}else if (bottomDie.getEyes() == die.getEyes() || bottomDie.getPaint() == die.getPaint()) {
								check = false;
								return check;
							}
						}
					}
				}
			}
		}
		
		
			
			
		return check;
	}
	
	public DiceController getDiceController() {
		return dc;
	}
}
