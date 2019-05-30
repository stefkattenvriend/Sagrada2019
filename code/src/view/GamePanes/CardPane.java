package view.GamePanes;

import controller.CardsController;
import controller.PayStoneController;
import controller.PayStoneThread;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class CardPane extends StackPane{
	private int cardNr;
	private CardsController cc;
	FlowPane ppsh = new FlowPane();
	PayStoneController psc;
	PayStoneThread ps;
	
	public CardPane(ImageView background, boolean toolCard, CardsController cardsController, int cardNr, PayStoneController psc) {
		this.psc = psc;
		cc = cardsController;
		this.cardNr = cardNr;
		setPrefSize((GamePane.windowMaxWidth / 6), (GamePane.windowMaxHeight - 40) / 3);
		this.getChildren().addAll(background);
		background.fitHeightProperty().bind(this.prefHeightProperty());
		
		ppsh.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
		this.getChildren().addAll(ppsh);
		
		if(toolCard) {
			this.setOnMouseClicked(e -> cc.useCard(cardNr));
			ps = new PayStoneThread(psc, cardNr, this);
			Thread h1 = new Thread(ps);
			h1.run();
		}
	}
	public void addPlayerPayStone() {
		PlayerPayStone pps = new PlayerPayStone(this);
		ppsh.getChildren().addAll(pps);
	}
	
	public int getCardNr() {
		return cardNr;
	}

	public void setPayStones(int amount) {
		getChildren().clear();
		for(int i = 0; i < amount; i++) {
			this.addPlayerPayStone();
		}
		
	}
	
}
