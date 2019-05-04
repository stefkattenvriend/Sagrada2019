package view;

import controller.DiceHolderController;
import controller.PatterncardController;
import javafx.scene.layout.StackPane;

public class LayerPane extends StackPane{//deze moet nog voor de gamepane worden aangemaakt(dus in de scene). deze pane geeft de mogelijkheid om "pop-ups" te creëren(voor bijvoorbeeld een pauze menu, patroonkaart keuze en een scorebord op einde van game ~Rens
	
	private GamePane gamePane;
	private DiceHolderController dhc;
	private PatterncardController dcc;
	
	public LayerPane(DiceHolderController dhc, PatterncardController dcc) {
		this.dhc = dhc;
		this.dcc = dcc;
		setUp();
		
	}
	
	public void setUp() {
		gamePane = new GamePane(dhc, dcc);
		this.getChildren().add(gamePane);
	}
	
	public int paternCardChoice() {//aanroepen vanuit gameController
		int choice;
		
		//voeg een hbox toe, zet in deze hbox 3 losse panes waarop geklikt kan worden om een keuze te maken(maak het uiterlijk aan via dcc.PatterncardCreate(j, i, pcnumber, (int)size) methode)
		
		
		
		
		choice = 1;//voor testen later weg
		
		return choice;
	}
	
}
