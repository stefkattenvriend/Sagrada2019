package view;

import javafx.scene.layout.Pane;

public class CardPane extends Pane{

	//instance
	private int cardHeight = 347;
	private int cardWidth;
	
	public CardPane() {
		setMinSize(cardWidth, cardHeight);
		
	}
}
