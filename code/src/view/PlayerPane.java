package view;

import javafx.scene.layout.VBox;

public class PlayerPane extends VBox{
	
	public PlayerPane() {
		setBackground(controller.Main.PLAYERPANE); // aanduiding voor pane
		setPaneSize();
	}
	
	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}

}
