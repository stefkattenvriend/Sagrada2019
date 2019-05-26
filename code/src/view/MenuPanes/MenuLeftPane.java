package view.MenuPanes;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.GamePanes.GamePane;

public class MenuLeftPane extends VBox{
	

	
	private MenuInvitePane resultsPane;
	private MenuResultsPane invitePane;
	
	public MenuLeftPane() {
		setUp();
	}

	private void setUp() {
		setPaneSize();
		tijdelijkAanduiding();
		createPanes();

	}
	
	private void createPanes() {		
		resultsPane = new MenuInvitePane();
		invitePane = new MenuResultsPane();
		
		
		getChildren().addAll(invitePane, resultsPane);
	}

	private void setPaneSize() {
		setMinSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
		setMaxSize(GamePane.windowMaxWidth / 3, GamePane.windowMaxHeight);
	}
	
	private void tijdelijkAanduiding() {
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
		setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
	}
}
