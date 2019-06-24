package view.GamePanes;

import controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.PlayerModel;
import view.MyScene;

public class EndPane extends VBox{
	//Joery
	
	private MyScene myScene;
	private GameController gc;
	private Label score;
	private PlayerModel[] pma;
	public EndPane(MyScene myScene, GameController gc) {
		this.myScene = myScene;
		this.gc = gc;
		setUp();
	}

	private void setUp() {
		
		Button backToGame = new Button("Back to menu");
		backToGame.setOnAction(e -> myScene.setMenuPane());
		
		Label score = new Label();
		score.setFont(Font.font("Comic Sans", FontWeight.BOLD, 80));
		score.setTextFill(Color.WHITE);
		String text = (new StringBuilder()).append("The game has ended!\n").toString();
		pma = gc.getGm().getPma();
		for (int i = 0; i < pma.length; i++) {
		
		text = text + (new StringBuilder()).append((pma[i].getUsername()) + " score: " + pma[i].getTotalPoints() + "\n").toString();
	
		}
		score.setText(text);
		setAlignment(Pos.CENTER);
		this.setPrefSize(GamePane.windowMaxWidth, GamePane.windowMaxHeight);

		this.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		this.getChildren().addAll(backToGame, score);
		
	}
}
