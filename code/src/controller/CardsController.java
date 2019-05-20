package controller;

import databeest.DataBaseApplication;
import databeest.DbCardCollector;
import javafx.scene.layout.VBox;
import view.GamePanes.CardDisplayPane;
import view.GamePanes.CardPane;

public class CardsController {
	private CardDisplayPane cardDisplayPane;
	private DbCardCollector dataBaseCardGrabber;
	
	public CardsController() {
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		
		CardPane toolCard1 = new CardPane();	//toolcards
		CardPane toolCard2 = new CardPane();
		CardPane toolCard3 = new CardPane();
		
		CardPane targetCard1 = new CardPane(); //targetcards
		CardPane targetCard2 = new CardPane();
		CardPane targetCard3 = new CardPane();
		
//		cardDisplayPane = new CardDisplayPane(vbox1, vbox2, toolCard1, toolCard2, toolCard3, targetCard1, targetCard2, targetCard3);
		
//		DataBaseCardGrab dataBaseCardGrab = new DataBaseCardGrab();
//		dataBaseCardGrabber = dataBaseCardGrab;
		
		
		
	}
	
	public CardDisplayPane getcardDisplayPane() {
		return cardDisplayPane;
	}
}
