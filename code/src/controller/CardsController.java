package controller;

import databeest.DbCardCollector;
import javafx.scene.image.ImageView;
import model.CardBackgroundModel;

public class CardsController {
	
	private DbCardCollector dbCardCollector;
	private CardBackgroundModel cardBackgroundModel;
	
	CardsController(DbCardCollector dbCardCollector){
		this.dbCardCollector = dbCardCollector;
		cardBackgroundModel = new CardBackgroundModel(this.dbCardCollector);
	}
	
	public ImageView getTc1(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard1());
	}
	
	public ImageView getTc2(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard2());
		}
	
	public ImageView getTc3(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard3());
	}
	
	public ImageView getTgc1(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard1());
	}
	
	public ImageView getTgc2(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard2());
	}
	
	public ImageView getTgc3(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard3());
	}
}
