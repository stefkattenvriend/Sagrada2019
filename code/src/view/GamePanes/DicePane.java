package view.GamePanes;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DicePane extends BorderPane{
	
	public DicePane(int size, Color paint, int number) {
		super();
		this.setBackground(new Background(new BackgroundFill(paint, null, null)));
		Text eyes = new Text(Integer.toString(number));
		eyes.setScaleX(size/20);
		eyes.setScaleY(size/20);
		this.setCenter(eyes);
		this.setMaxSize(size, size);
	}
}
