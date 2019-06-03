package view.GamePanes;

import controller.DiceHolderController;
import helpers.DiceHolderType;
import javafx.scene.layout.FlowPane;

public class RoundTrackPane extends FlowPane {

	// instance
	private double roundTrackPaneHeight = 100;
	private DiceHolderController dhc;

	public RoundTrackPane(DiceHolderController dhc) {
		this.dhc = dhc;
		setUp();
	}

	private void setUp() {
		setPaneSize();
		addDiceHolders();
	}

	private void addDiceHolders() {
		for (int i = 1; i < 11; i++) {
			double size = ((GamePane.windowMaxWidth / 3) / 10) - 1;
			getChildren().add(dhc.CreateDiceHolder(size, i, 0, DiceHolderType.ROUNDTRACK));
		}
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, roundTrackPaneHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, roundTrackPaneHeight);
	}

	public void redrawDice() {
		this.getChildren().clear();
		for (int x = 1; x < 11; x++) {
			this.getChildren().add(dhc.getPlayerWindowDiceHolders(x, 0, DiceHolderType.ROUNDTRACK));
		}
	}
}