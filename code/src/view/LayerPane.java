package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class LayerPane extends StackPane{//deze moet nog voor de gamepane worden aangemaakt(dus in de scene). deze pane geeft de mogelijkheid om "pop-ups" te cre�ren(voor bijvoorbeeld een pauze menu, patroonkaart keuze en een scorebord op einde van game ~Rens
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	
	
	public LayerPane() {
		setUp();
		
	}
	
	public void setUp() {
		setLayerSize();
		setDesign();
		
	}
	
	private void setDesign() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
		
	}

	public int paternCardChoice() {//aanroepen vanuit gameController
		int choice;
		
		//voeg een hbox toe, zet in deze hbox 3 losse panes waarop geklikt kan worden om een keuze te maken(maak het uiterlijk aan via dcc.PatterncardCreate(j, i, pcnumber, (int)size) methode)
		
		
		
		
		choice = 1;//voor testen later weg
		
		return choice;
	}
	
	private void setLayerSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
}
