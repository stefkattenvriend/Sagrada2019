package controller;

import java.util.ArrayList;

import databeest.DbDieCollector;
import helpers.DiceHolderType;
import helpers.PatterncardType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.DiceHolderModel;
import model.DiceModel;
import view.GamePanes.DiceHolderPane;
import view.GamePanes.DicePane;

public class DiceHolderController {

	private ArrayList<DiceHolderModel> dhmodels = new ArrayList<DiceHolderModel>();
	private ArrayList<DiceHolderPane> dhpanes = new ArrayList<DiceHolderPane>();
	private ArrayList<DiceModel> movedDice = new ArrayList<DiceModel>();
	private DiceController dc;
	private PatterncardController pcc;
	private ToolCardController tcc;
	private boolean checkColor = true;
	private boolean checkEyes = true;
	private boolean checkNextTo = true;
	
	
	public DiceHolderController(PatterncardController pcc, DbDieCollector ddc, int gameid) {
		this.pcc = pcc;
		dc = new DiceController(ddc, gameid);
	}
	
	
	
	public void setTypeToInteractable(DiceHolderType dhc, boolean interactable) {
		for(int i = 0; i < dhmodels.size(); i++) {
			if(dhmodels.get(i).getType() == dhc) {
				dhmodels.get(i).setInteractable(interactable);
			}
		}
	}
	
	public DiceHolderModel GetSelectedDiceHolder() {
		for(int i = 0; i < dhmodels.size(); i++) {
			if(dhmodels.get(i).getSelected()) {
				return dhmodels.get(i);
			}
		}
		return null;
	}
	
	private DiceHolderPane GetSelectedDicePane() {
		for(int i = 0; i < dhmodels.size(); i++) {
			if(dhmodels.get(i).getSelected()) {
				return dhpanes.get(i);
			}
		}
		return null;
	}
	
	public void setAllUninteractable() {
		for(int i = 0; i < dhmodels.size(); i++) {
			dhmodels.get(i).setInteractable(false);
		}
	}

	public DiceHolderPane CreateDiceHolder(double size, int x, int y, DiceHolderType type) {// deze methode maakt de
																							// diceHolder model en pane
																							// aan en geeft de pane
																							// terug aan de view
		DiceHolderModel model = new DiceHolderModel(null, x, y, type);
		
		if(type == DiceHolderType.ENEMY1 || type == DiceHolderType.ENEMY2 || type == DiceHolderType.ENEMY3) {
			model.setInteractable(false);
		}
		else {
			model.setInteractable(true);
		}
		
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
		
		if (selectedModel.isInteractable() == false) {
			return;
		}

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
						movedDice.add(dhmodels.get(i).getDie());
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

	public void addDie(DiceHolderType location, int x, int y, int dieModel) {
		
		dieModel = dieModel - 1;//zodat t werkt met n array
		
		for (int i = 0; i < dhmodels.size(); i++) {
			if (dhmodels.get(i).getType() == location && dhmodels.get(i).getX() == x && dhmodels.get(i).getY() == y) {
				dhmodels.get(i).setDie(dc.getDiceModel(dieModel));
				dhpanes.get(i).setCenter(dc.getDicePane(dieModel));
			}
			
		}
	}
	
	public boolean checkDiceMovement(DiceHolderModel location, DiceModel die) {
		boolean check = true;
			if(location.getType() == DiceHolderType.PLAYERWINDOW) {
		
		if(checkColor) {
			for (int i = 0; i < pcc.getPcModelsSize(); i++) {//vergelijkt kleur van patroonkaart en die
				if(location.getX() == pcc.getPcModel(i).getX() && location.getY() == pcc.getPcModel(i).getY() && pcc.getPcModel(i).getPct() == PatterncardType.PLAYER) {
						if(die.getDieColor() != pcc.getPcModel(i).getColor() && pcc.getPcModel(i).getColor() != Color.WHITE) {
							check = false;
							return check;
					}
				}
			}		
		}
		
		if(checkEyes) {
			for (int i = 0; i < pcc.getPcModelsSize(); i++) {//vergelijkt waarde van patroonkaart en die
				if(location.getX() == pcc.getPcModel(i).getX() && location.getY() == pcc.getPcModel(i).getY() && pcc.getPcModel(i).getPct() == PatterncardType.PLAYER) {
					if(die.getEyes() != pcc.getPcModel(i).getNumber() && pcc.getPcModel(i).getNumber() != 0) {
						check = false;
						return check;
					}
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
							}else if (leftDie.getEyes() == die.getEyes() || leftDie.getDieColor() == die.getDieColor()) {
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
						}else if (rightDie.getEyes() == die.getEyes() || rightDie.getDieColor() == die.getDieColor()) {
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
						}else if (topDie.getEyes() == die.getEyes() || topDie.getDieColor() == die.getDieColor()) {
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
						}else if (bottomDie.getEyes() == die.getEyes() || bottomDie.getDieColor() == die.getDieColor()) {
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
	
	public ArrayList<DiceHolderModel> getPlayerWindowDice(){
		ArrayList<DiceHolderModel> playerWindowDice = new ArrayList<DiceHolderModel>();
		for(int i=0; i < dhmodels.size(); i++) {
			if(dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW) {
				playerWindowDice.add(dhmodels.get(i));
			}
		}
		return playerWindowDice;
	}
	
	public ArrayList<DiceModel> getMovedDice() {
		return movedDice;
	}

	public ArrayList<DiceHolderModel> getDhmodels() {
		return dhmodels;
	}

	public ArrayList<DiceHolderPane> getDhpanes() {
		return dhpanes;
	}

	public void changeDie(int j2, DicePane dp) {
		DiceHolderPane dhp = dhpanes.get(j2);
		dhp.setCenter(dp);
		
	}
	
	public void switchTurnInteractable(boolean b) {
		for (int i = 0; i < dhmodels.size(); i++) {
			if (dhmodels.get(i).getType() == DiceHolderType.OFFER || dhmodels.get(i).getType() == DiceHolderType.PLAYERWINDOW) {
				dhmodels.get(i).setInteractable(b);
			}
		}
	}
	
	public void solveTC1(ToolCardController tcc) {
		this.tcc = tcc;
		this.setAllUninteractable();
		//make it so you cant end turn
		//make it so you cant move any other stone till youve moved this one
		int dienr = this.GetSelectedDiceHolder().getDie().getEyes();
		GetSelectedDicePane().addPlusAndMinus(dienr);
	}
	
	public void higherClicked() {
		System.out.println("die eyes: " + this.GetSelectedDiceHolder().getDie().getEyes());
		int nr = this.GetSelectedDiceHolder().getDie().getEyes() + 1;
		System.out.println("new die eyes" + nr);
		this.GetSelectedDiceHolder().getDie().setEyes(nr);
		tcc.finish1();
	}
	
	public void lowerClicked() {
		System.out.println("die eyes: " + this.GetSelectedDiceHolder().getDie().getEyes());
		int nr = this.GetSelectedDiceHolder().getDie().getEyes() - 1;
		System.out.println("new die eyes" + nr);
		this.changeDieEyes(nr, this.GetSelectedDiceHolder());
		tcc.finish1();
	}
	
	public void changeDieEyes(int nr, DiceHolderModel dh) {
		dh.getDie().setEyes(nr);
	}
	
	public void setCheckColor(boolean i) {
		checkColor = i;
	}
	
	public void setCheckEyes(boolean i) {
		checkEyes = i;
	}
}
