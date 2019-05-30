package controller;

import databeest.DbCardCollector;
import javafx.scene.image.ImageView;
import model.CardBackgroundModel;

public class CardsController {
	
	private DbCardCollector dbCardCollector;
	private CardBackgroundModel cardBackgroundModel;
	private ToolCardController tcc;
	
	public CardsController(DbCardCollector dbCardCollector, int gameId, ToolCardController tcc){
		this.tcc = tcc;
		this.dbCardCollector = dbCardCollector;
		cardBackgroundModel = new CardBackgroundModel(this.dbCardCollector, gameId);
	}
	
	
	public void useCard(int CardNr) {
		tcc.useCard(CardNr);
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
}
