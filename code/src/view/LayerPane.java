package view;

import controller.LayerController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class LayerPane extends BorderPane{//deze moet nog voor de gamepane worden aangemaakt(dus in de scene). deze pane geeft de mogelijkheid om "pop-ups" te cre�ren(voor bijvoorbeeld een pauze menu, patroonkaart keuze en een scorebord op einde van game ~Rens
	
	//constants
	public final static double windowMaxWidth = 1280;
	public final static double windowMaxHeight = 800;
	
	public LayerController controller;
	private FlowPane buttonPane;
	private Button button;
	private FlowPane chooserPane = new FlowPane();;
	private int[] randomPat;
	
	public LayerPane(LayerController controller) {
//		randomPat = controller.getRandomPat();
		this.controller = controller;
		setUp();
	}
	
	public void setUp() {
		setLayerSize();
		setDesign();
		setButton();
		setChooserPane();
		setLeft(buttonPane);
		setRight(chooserPane);	
	}
	
	private void setDesign() {
		setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255, 0.7), null, null)));
	}
	
	private void setButton() {
		buttonPane = new FlowPane();
		button = new Button("Random");
		button.setPrefSize(80, 40);
		button.setOnAction(e -> viewOffer()); //bij druk op de knop worden de randomkaarten pas zichtbaar.
		buttonPane.setAlignment(Pos.CENTER_LEFT);
		buttonPane.setPrefSize(120, windowMaxHeight);
		buttonPane.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
		buttonPane.getChildren().add(button);
	}
	
	private void setChooserPane() {
		chooserPane.setPrefSize(windowMaxWidth - 120, windowMaxHeight);
	} 
	
	private void viewOffer() {
		controller.generateRdmPatternCards();
		randomPat = controller.getRandomPat();
		chooserPane.getChildren().clear();
		chooserPane.getChildren().addAll(createPatternCard(String.valueOf(randomPat[0])), createPatternCard(String.valueOf(randomPat[1])), createPatternCard(String.valueOf(randomPat[2])), createPatternCard(String.valueOf(randomPat[3])));
		chooserPane.setAlignment(Pos.CENTER_RIGHT);
	}
	
	private Pane createPatternCard(String rdInt) {
		FlowPane patternCard = new FlowPane();
		patternCard.setPrefWidth(500);
		patternCard.setPrefHeight(400);
		patternCard.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		patternCard.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		Label label = new Label(rdInt);
		label.setFont(new Font("Arial", 80));
		patternCard.getChildren().add(label);
		patternCard.setAlignment(Pos.CENTER);
//		patternCard.setOnMouseClicked(e ->); // als je op een pattroonkaart klikt wordt deze gebruikt in het spel.
		return patternCard;
	}
	
	private void setLayerSize() {
		setMinSize(windowMaxWidth, windowMaxHeight);
		setMaxSize(windowMaxWidth, windowMaxHeight);
	}
	
}
