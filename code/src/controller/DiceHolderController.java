package controller;

import java.util.ArrayList;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.DiceHolderModel;
import model.DiceModel;
import view.DiceHolderPane;


public class DiceHolderController {
	
	private ArrayList<DiceHolderModel> dhmodels = new ArrayList<DiceHolderModel>();
	private ArrayList<DiceHolderPane> dhpanes = new ArrayList<DiceHolderPane>();
	private DiceController dc = new DiceController();
	
	
	
	public DiceHolderPane CreateDiceHolder(double size, int x, int y, int  type) {//deze methode maakt de diceHolder model en pane aan en geeft de pane terug aan de view
		DiceHolderModel model = new DiceHolderModel(null, x, y, type, this);
		DiceHolderPane pane = new DiceHolderPane(size, this);
		dhmodels.add(model);
		dhpanes.add(pane);
		return pane;
	}
	
	public void DiceHolderClick(DiceHolderPane dp) {
		DiceHolderModel selectedModel = null;
		
		for (int i = 0; i < dhpanes.size(); i++) {//haalt de model van de gekozen op.
			if(dp == dhpanes.get(i)) {
				selectedModel = dhmodels.get(i);
				break;
			}
		}
		
		if(selectedModel.getSelected() == false) {//check of er al een ander vak geselcteerd was
			for (int i = 0; i < dhmodels.size(); i++) {
				if(dhmodels.get(i).getSelected() == true) {
					if(selectedModel.getDie() == null && dhmodels.get(i).getDie() == null) {//switch tussen de selected
						dhmodels.get(i).switchSelected();
						dhpanes.get(i).setBackground(null);//zet background en selected status naar null van de pane die eerder selected was
						
						selectedModel.switchSelected();
						dp.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
						
						return;
						
					}
					else if(selectedModel.getDie() == null && dhmodels.get(i).getDie() != null) {//switch een dobbelsteen
						selectedModel.setDie(dhmodels.get(i).getDie());//wiselt de models
						dhmodels.get(i).setDie(null);
						
						dhmodels.get(i).switchSelected();//zet achtergrond en selected naar nul van oude pane
						dhpanes.get(i).setBackground(null);
						
						dp.setCenter(dhpanes.get(i).getCenter());//wiselt de panes
						dhpanes.get(i).setCenter(null);
						
						
						
						return;
					}
				}
			}
			
			selectedModel.switchSelected();
			dp.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
			
		}
		
		//dp.setBackground(new Background(new BackgroundFill(Color.PURPLE, null, null)));
		
	}
	
	public void DiceHolderHover(DiceHolderPane dp) {
		dp.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 153, 0.5), null, null)));
	}
	
	public void DiceHolderLeaveHover(DiceHolderPane dp) {
		for (int i = 0; i < dhpanes.size(); i++) {
			if (dp == dhpanes.get(i)) {
				if(dhmodels.get(i).getSelected() == false) {
					dp.setBackground(null);
				}	
			}
		}
		
	}
	
	public void addDie(int location, int x, int y, int color, int eyes, boolean interactable) {
		DiceModel die = dc.createDieModel(color, eyes);
		for (int i = 0; i < dhmodels.size(); i++) {
			if(dhmodels.get(i).getType() == location && dhmodels.get(i).getX() == x && dhmodels.get(i).getY() == y) {
				dhmodels.get(i).setDie(die);
				dhpanes.get(i).setCenter(dc.createDicePane(die));
			};
		}
	}
}
