package view.GamePanes;

import controller.CardsController;
import controller.GameController;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class CardPane extends StackPane{
	private int cardNr;
	private CardsController cc;
	GameController gc;
	int stonesAmount = 0;
	ImageView background;
	boolean toolCard;
	boolean selected;
	
	public CardPane(ImageView background, boolean toolCard, CardsController cardsController, int cardNr, GameController gc) {
		this.background = background;
		this.gc = gc;
		gc.addCardPane(this);
		this.toolCard = toolCard;
		cc = cardsController;
		this.cardNr = cardNr;
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.getChildren().addAll(this.background);
		this.background.fitHeightProperty().bind(this.prefHeightProperty());
		FlowPane ppsh = new FlowPane();
		ppsh.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
		this.getChildren().addAll(ppsh);
		
		if(toolCard) {
			this.setOnMouseClicked(e -> cc.useCard(this));
		}
	}
	public void addPlayerPayStone(FlowPane ppsh) {
		 if(toolCard) {
			System.out.println("added a paystone");
			PlayerPayStone pps = new PlayerPayStone(this);
			ppsh.getChildren().addAll(pps);
		 }
	}
	
	public int getCardNr() {
		return cardNr;
	}
	
	public void refresh(int amount) {
		System.out.println("refresh amount: " + amount);
		stonesAmount = amount;
		this.getChildren().clear();
		this.getChildren().add(background);
		FlowPane ppsh = new FlowPane();
		ppsh.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
		this.getChildren().add(ppsh);
		for(int i = 0; i < amount; i++) {	
			this.addPlayerPayStone(ppsh);
		}
	}
	
	public void setStonesAmount(int amount) {
		stonesAmount = amount;
	}
	
	public int getStonesAmount() {
		return stonesAmount;
	}
	
	public void Selected() {
		
	}
	
}
