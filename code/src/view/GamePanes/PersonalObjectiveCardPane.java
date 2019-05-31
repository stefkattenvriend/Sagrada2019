package view.GamePanes;

import controller.PlayerController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.PlayerModel;
//Tjess Wjestt
public class PersonalObjectiveCardPane extends VBox{
	private Label label;
	private Color myColor;
	private double personalAttributesHeight = 75;
	private PlayerController controller;
	
	public PersonalObjectiveCardPane(PlayerController playercontroller) {
		controller = playercontroller;
		myColor=controller.getPersonalObjective();
		setAlignment(Pos.CENTER);
		setPaneSize();
		label = new Label("Your personal Color is: ");
		Rectangle square = new Rectangle();
		square.setWidth(30);
		square.setHeight(30);
		square.setStroke(Color.BLACK);
		square.setStrokeWidth(2);
		square.setFill(myColor);
		getChildren().addAll(label, square);
	}
	
	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);
	}
	
	public Color getColor() {
		return myColor;
	}
}
