package view;

import controller.LayerController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LayerPane extends VBox{//deze moet nog voor de gamepane worden aangemaakt(dus in de scene). deze pane geeft de mogelijkheid om "pop-ups" te cre�ren(voor bijvoorbeeld een pauze menu, patroonkaart keuze en een scorebord op einde van game ~Rens
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	public LayerController controller;
	private FlowPane buttonPane;
	
	public LayerPane(LayerController controller) {
		this.controller = controller;
		setUp();
	}
	
	public void setUp() {
		setLayerSize();
		setDesign();
		setButton();
		getChildren().add(buttonPane);
		
	}
	
	private void setDesign() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
		
	}
	
	private void setButton() {
		buttonPane = new FlowPane();
		Button button = new Button("Random");
		button.setPrefSize(80, 40);
		button.setOnAction(e -> generateRdmPatternCards());
		button.setAlignment(Pos.CENTER);
		buttonPane.setPrefSize(120, windowMaxHeight);
		buttonPane.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
		buttonPane.getChildren().add(button);
	}

	public int paternCardChoice() {//aanroepen vanuit gameController
		int choice;
		
		//voeg een hbox toe, zet in deze hbox 3 losse panes waarop geklikt kan worden om een keuze te maken(maak het uiterlijk aan via dcc.PatterncardCreate(j, i, pcnumber, (int)size) methode)
		
		
		
		
		choice = 1;//voor testen later weg
		
		return choice;
	}
	
	public void generateRdmPatternCards(){
		LayerController thecontroller = new LayerController();
		thecontroller.generateRdmPatternCards();	
	}
	
	private void setLayerSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
}
