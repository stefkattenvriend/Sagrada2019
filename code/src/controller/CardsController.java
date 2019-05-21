package controller;

import databeest.DataBaseApplication;
import databeest.DbCardCollector;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.CardBackgroundModel;
import view.GamePanes.CardDisplayPane;
import view.GamePanes.CardPane;

public class CardsController {
	
	DbCardCollector dbCardCollector;
	private CardBackgroundModel cardBackgroundModel = new CardBackgroundModel(dbCardCollector);
	
	CardsController(DbCardCollector dbCardCollector){
		this.dbCardCollector = dbCardCollector;
	}
	
	public ImageView getTc1(){
		return cardBackgroundModel.getToolCardBg(1);
	}
	
	public ImageView getTc2(){
		return cardBackgroundModel.getToolCardBg(2);
		}
	
	public ImageView getTc3(){
		return cardBackgroundModel.getToolCardBg(6);
	}
	
	public ImageView getTgc1(){
		return cardBackgroundModel.getTargetCardbg(3);
	}
	
	public ImageView getTgc2(){
		return cardBackgroundModel.getTargetCardbg(8);
	}
	
	public ImageView getTgc3(){
		return cardBackgroundModel.getTargetCardbg(9);
	}
}
