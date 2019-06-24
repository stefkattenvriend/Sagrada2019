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

public class MenuInvitePane extends FlowPane {

	private ScrollPane inviteList;
	private VBox list;
	private DataBaseApplication databeest;
	private ArrayList<String> challengers;
	private LoginController lc;
	private MenuWaitingPane menuWaitingPane;
	private MenuController menuController;
	private ArrayList<String> invitedGameIDs;
	private ArrayList<MenuDropdown> games;
	private Label title;

	public MenuInvitePane(MenuController menuController, LoginController lc, MenuWaitingPane menuWaitingPane) {
		databeest = menuController.getDataBaseApplication();
		this.lc = lc;
		this.menuController = menuController;
		this.menuWaitingPane = menuWaitingPane;
		menuController.setInvitePane(this);
	
		this.challengers = menuController.getChallengers();
		this.invitedGameIDs = menuController.getInvitedGamesID();

		setPaneSize();
		createInvitesList();

		setBackground(new Background(new BackgroundFill(Color.rgb(254, 255, 209, 0.8), null, null))); // tijdelijk
	}

	public void createInvitesList() {
		title = new Label();
		title.setText("Uitnodigingen");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
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

		games = new ArrayList<MenuDropdown>();

		for (int i = 0; i < invitedGameIDs.size(); i++) {
			games.add(new MenuDropdown(menuController, false,
					"Uitnodiging voor Sagrada " + invitedGameIDs.get(i) + " door " + challengers.get(i), false, null,
					false, true, null, lc, this, null, false));
		}

		for (int x = 0; x < games.size(); x++) { // voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}

		setAlignment(Pos.CENTER);
		getChildren().addAll(title, inviteList);

	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 80);
		setMaxSize(MenuPane.paneWidth - 40, MenuPane.windowMaxHeight - (MenuPane.windowMaxHeight / 3) - 80);
	}
	
	public void setNewInput(ArrayList<String> newInvitedGames, ArrayList<String> newChallengers) {
		invitedGameIDs.clear();
		challengers.clear();
		invitedGameIDs = newInvitedGames;
		challengers = newChallengers;
		updateInvitePane();
	}

	public void updateInvitePane() {
		getChildren().clear();
		list.getChildren().clear();
		games.clear();

		for (int i = 0; i < invitedGameIDs.size(); i++) {
			games.add(new MenuDropdown(menuController, false,
					"Uitnodiging voor Sagrada " + invitedGameIDs.get(i) + " door " + challengers.get(i), false, null,
					false, true, menuWaitingPane, lc, this, null, false));
		}

		for (int x = 0; x < games.size(); x++) { // voegt alle knoppen toe aan de lijst
			list.getChildren().add(games.get(x));
		}

		setAlignment(Pos.CENTER);
		getChildren().addAll(title, inviteList);
		menuWaitingPane.updateWaitingPane();
	}

}
