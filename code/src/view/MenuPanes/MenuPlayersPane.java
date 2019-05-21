package view.MenuPanes;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuPlayersPane extends Pane{
	
	public MenuPlayersPane() {
		setPaneSize();
		setBackground(new Background(new BackgroundFill(Color.DARKORANGE, null, null))); //tijdelijk
	}
	
	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3));
	}
}
