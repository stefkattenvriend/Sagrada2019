package view.MenuPanes;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MenuResultsPane extends Pane{
	
	public MenuResultsPane() {
		setPaneSize();
//		setBackground(new Background(new BackgroundFill(Color.GREEN, null, null))); //tijdelijk
	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
	}
}
