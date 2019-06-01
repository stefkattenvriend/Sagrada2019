package view.GamePanes;

import controller.CardsController;
import controller.GameController;
import controller.PayStoneController;
import controller.PayStoneThread;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class CardPane extends StackPane{
	private int cardNr;
	private CardsController cc;
	FlowPane ppsh = new FlowPane();
	PayStoneThread ps;
	GameController gc;
	int stonesAmount = 0;
	ImageView background;
	
	public CardPane(ImageView background, boolean toolCard, CardsController cardsController, int cardNr, GameController gc) {
		this.background = background;
		this.gc = gc;
		gc.addCardPane(this);
		cc = cardsController;
		this.cardNr = cardNr;
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.getChildren().addAll(this.background);
		this.background.fitHeightProperty().bind(this.prefHeightProperty());
		
		ppsh.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
		this.getChildren().addAll(ppsh);
		
		if(toolCard) {
			System.out.println("this should crash now!");
			this.setOnMouseClicked(e -> cc.useCard(cardNr));
		}
	}
	public void addPlayerPayStone() {
		System.out.println("added a paystone");
		PlayerPayStone pps = new PlayerPayStone(this);
		ppsh.getChildren().addAll(pps);
	}
	
	public int getCardNr() {
		return cardNr;
	}
	
	public void refresh(int amount) {
		stonesAmount = amount;
		this.getChildren().clear();
		this.getChildren().add(background);
		this.getChildren().add(ppsh);
		for(int i = 0; i < amount; i++) {
			
			this.addPlayerPayStone();
		}
	}
	
	public void setStonesAmount(int amount) {
		stonesAmount = amount;
	}
	

	public void setPayStones(int amount) {
		getChildren().clear();
		for(int i = 0; i < amount; i++) {
			this.addPlayerPayStone();
		}
		
	}
	
	public int getStonesAmount() {
		return stonesAmount;
	}
	
}
