package view.MenuPanes;

import javafx.scene.layout.Pane;

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
