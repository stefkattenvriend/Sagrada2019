package view.MenuPanes;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.GamePanes.GamePane;

public class MenuWaitingPane extends Pane{
	
	public MenuWaitingPane() {
		setPaneSize();
		setBackground(new Background(new BackgroundFill(Color.DARKRED, null, null))); //tijdelijk
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight / 2);
	}
}
