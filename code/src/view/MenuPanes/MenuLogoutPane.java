package view.MenuPanes;

import controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import view.MyScene;

public class MenuLogoutPane extends FlowPane{
	private Button button;
	private LoginController logincontroller;
	private MyScene myscene;
	
	public MenuLogoutPane(LoginController controller, MyScene myScene) {
		logincontroller = controller;
		myscene = myScene;
		setAlignment(Pos.TOP_LEFT);
		button = new Button("Log out");
		button.setMaxSize(100, 50);
		button.setMinSize(100, 50);
		button.setOnAction(e -> logout());
		button.setTextAlignment(TextAlignment.RIGHT);
		button.setOnMouseClicked(e -> button.setTextAlignment(TextAlignment.RIGHT));
		button.setOnMouseEntered(e -> button.setTextAlignment(TextAlignment.RIGHT));
		
		getChildren().add(button);
	}

	private void logout() {
		logincontroller.logout();
		myscene.setLoginPane();
		
	}
	
	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 4);
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 4);
	}
}
