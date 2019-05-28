package view.MenuPanes;

import java.util.ArrayList;

import controller.LoginController;
import controller.MenuController;
import databeest.DataBaseApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuInvitePane extends FlowPane{
	
	private ScrollPane inviteList;
	private VBox list;
	private DataBaseApplication databeest;
	private ArrayList<String> challengers;
	private LoginController lc;
	private MenuController menuController;
	
	public MenuInvitePane(MenuController menuController, LoginController lc) {
		databeest = menuController.getDataBaseApplication();
		this.lc = lc;
		this.menuController = menuController;
		challengers = databeest.getChallenger(lc.getCurrentAccount());
		setPaneSize();
		createActiveGamesList();
		setBackground(new Background(new BackgroundFill(Color.rgb(254, 255, 209, 0.8), null, null))); //tijdelijk
	}
	
	private void createActiveGamesList() {
		Label title = new Label();
		title.setText("Uitnodigingen");
		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 30));
		title.setTextFill(Color.GOLD);
		
		inviteList = new ScrollPane();
		inviteList.setMinSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		inviteList.setMaxSize(MenuPane.paneWidth - 60, (MenuPane.windowMaxHeight / 2) - 60);
		inviteList.setFitToWidth(true);
		inviteList.setFitToHeight(true);
		
		list = new VBox();
		list.setMinWidth(MenuPane.paneWidth - 80); // hoogte van lijst moet automatisch bijgewerkt worden met binding of listner.
		list.setMaxWidth(MenuPane.paneWidth - 80);
		inviteList.setContent(list);
		
		ArrayList<MenuDropdown> games = new ArrayList<MenuDropdown>();
		
		for(int i = 0; i < challengers.size(); i++) {// vult verzameling met alle knoppen
			games.add(new MenuDropdown(menuController, false, "Uitnodiging van " + challengers.get(i), false, null, false, true, null, lc));
			
		}
		
		for(int x = 0; x < games.size(); x++) { //voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}
				
		setAlignment(Pos.CENTER);
		getChildren().addAll(title, inviteList);
		
	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 80);
		setMaxSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 80);
	}
	
	private void update() {
		//TODO
	}
}
