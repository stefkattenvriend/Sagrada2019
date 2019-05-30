package view.GamePanes;

import controller.CardsController;
import controller.PayStoneController;
import controller.PayStoneThread;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

public class CardPane extends StackPane{
	private int cardNr;
	private CardsController cc;
	FlowPane ppsh = new FlowPane();
	PayStoneController psc;
	
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
			PayStoneThread pst = new PayStoneThread(psc, cardNr, this);
			Thread p1 = new Thread(pst);
		}
		
	}
	
	public void addPlayerPayStone() {
		PlayerPayStone pps = new PlayerPayStone(this);
		ppsh.getChildren().addAll(pps);
	}
	
	public int getCardNr() {
		return cardNr;
	}
	
}
