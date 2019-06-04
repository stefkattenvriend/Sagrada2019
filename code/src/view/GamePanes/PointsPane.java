package view.GamePanes;

import controller.PointsController;
import javafx.scene.layout.VBox;

//joery
public class PointsPane extends VBox {

	private double personalAttributesHeight = 75;
	private PointsController pc;

	public PointsPane(PointsController pc) {
		this.pc = pc;
		setUp();
	}

	private void setUp() {
		setPaneSize();
	}

	private void setPaneSize() {
		setMinSize((GamePane.windowMaxWidth / 3) / 3, personalAttributesHeight);

	}
}
