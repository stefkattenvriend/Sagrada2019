package view.GamePanes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//Tjess Wjestt
public class PersonalObjectiveCardPane extends VBox{
	private Label label;
	private Color myColor;
	private double personalAttributesHeight = 75;
	private Rectangle square;
	
	public PersonalObjectiveCardPane() {

		setAlignment(Pos.CENTER);
		setPaneSize();
		label = new Label("Je persoonlijke kleur is: ");
		square = new Rectangle();
		square.setWidth(30);
		square.setHeight(30);
		square.setStroke(Color.BLACK);
		square.setStrokeWidth(2);
		getChildren().addAll(label, square);

	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);
	}
	
	public Color getColor() {

		return myColor;
	}

	public void setMyColor(Color color) {
		myColor = color;
		square.setFill(myColor);

	}
}
