package model;

import databeest.DbCardCollector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardBackgroundModel {
	
	private DbCardCollector dbCardCollector;
	private int ToolCard1;
	private int ToolCard2;
	private int ToolCard3;
	
	private int TargetCard1;
	private int TargetCard2;
	private int TargetCard3;
	
	public CardBackgroundModel(DbCardCollector dbCardCollector, int gameId) {
		this.dbCardCollector = dbCardCollector;
		
		ToolCard1 = this.dbCardCollector.getToolcards(gameId).get(0);
		System.out.println("toolcard 1: " + ToolCard1);
		ToolCard2 = this.dbCardCollector.getToolcards(gameId).get(1);
		System.out.println("toolcard 2: " + ToolCard1);
		ToolCard3 = this.dbCardCollector.getToolcards(gameId).get(2);
		
		TargetCard1 = this.dbCardCollector.getObjectivecards(gameId).get(0);
		TargetCard2 = this.dbCardCollector.getObjectivecards(gameId).get(1);
		TargetCard3 = this.dbCardCollector.getObjectivecards(gameId).get(2);
	}
	
	public int getToolCard1() {
		return ToolCard1;
	}
	
	public int getToolCard2() {
		return ToolCard2;
	}
	
	public int getToolCard3() {
		return ToolCard3;
	}
	
	public int TargetCard1() {
		return TargetCard1;
	}
	
	public int TargetCard2() {
		return TargetCard2;
	}
	
	public int TargetCard3() {
		return TargetCard3;
	}
	
	//Jami
	public ImageView getToolCardBg(int card) {
		
		switch(card) {
		case 1:
			ImageView iv1 = new ImageView(new Image("/toolcards/1.png"));
			return iv1;
		case 2:
			ImageView iv2 = new ImageView(new Image("/toolcards/2.png"));
			return iv2;
		case 3:
			ImageView iv3 = new ImageView(new Image("/toolcards/3.png"));
			return iv3;
		case 4:
			ImageView iv4 = new ImageView(new Image("/toolcards/4.png"));
			return iv4;
		case 5:
			ImageView iv5 = new ImageView(new Image("/toolcards/5.png"));
			return iv5;
		case 6:
			ImageView iv6 = new ImageView(new Image("/toolcards/6.png"));
			return iv6;
		case 7:
			ImageView iv7 = new ImageView(new Image("/toolcards/7.png"));
			return iv7;
		case 8:
			ImageView iv8 = new ImageView(new Image("/toolcards/8.png"));
			return iv8;
		case 9:
			ImageView iv9 = new ImageView(new Image("/toolcards/9.png"));
			return iv9;
		case 10:
			ImageView iv10 = new ImageView(new Image("/toolcards/10.png"));
			return iv10;
		case 11:
			ImageView iv11 = new ImageView(new Image("/toolcards/11.png"));
			return iv11;
		case 12:
			ImageView iv12 = new ImageView(new Image("/toolcards/12.png"));
			return iv12;
		default:
			System.out.println("There is no picture");
			ImageView iv13 = new ImageView(new Image("/toolcards/1.png"));
			return iv13;
		}
	}
	
	public ImageView getTargetCardbg(int card) {
		switch(card) {
		case 1:
			ImageView iv1 = new ImageView(new Image("/objectivecards/1.png"));
			return iv1;
		case 2:
			ImageView iv2 = new ImageView(new Image("/objectivecards/2.png"));
			return iv2;
		case 3:
			ImageView iv3 = new ImageView(new Image("/objectivecards/3.png"));
			return iv3;
		case 4:
			ImageView iv4 = new ImageView(new Image("/objectivecards/4.png"));
			return iv4;
		case 5:
			ImageView iv5 = new ImageView(new Image("/objectivecards/5.png"));
			return iv5;
		case 6:
			ImageView iv6 = new ImageView(new Image("/objectivecards/6.png"));
			return iv6;
		case 7:
			ImageView iv7 = new ImageView(new Image("/objectivecards/7.png"));
			return iv7;
		case 8:
			ImageView iv8 = new ImageView(new Image("/objectivecards/8.png"));
			return iv8;
		case 9:
			ImageView iv9 = new ImageView(new Image("/objectivecards/9.png"));
			return iv9;
		case 10:
			ImageView iv10 = new ImageView(new Image("/objectivecards/10.png"));
			return iv10;
		default:
			System.out.println("There is no picture");
			ImageView iv13 = new ImageView(new Image("toolcards/1.png"));
			return iv13;
		}
	}
}
