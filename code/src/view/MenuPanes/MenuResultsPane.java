package view.MenuPanes;

import java.util.ArrayList;

import controller.MenuController;
import databeest.DataBaseApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//Tesssss
public class MenuResultsPane extends VBox {
	private HBox won;
	private HBox lost;
	private ArrayList<Integer> finishedGames;
	private MenuController menuController;
	private String winner;
	private String username;
	private int winGame;
	private int lostGame;
	private DataBaseApplication databeest;

	public MenuResultsPane(MenuController menuController) {
//		calculateWinner();
		setPaneSize();
		setAlignment(Pos.CENTER);
		makePanes();
		Label lbl1 = new Label("Jouw Statistieken: ");
		lbl1.setFont(new Font(40));
		getChildren().addAll(lbl1, won, lost);
		this.menuController = menuController;
		this.username = menuController.getCurrentUsername();
		this.databeest = menuController.getDataBaseApplication();

	}

	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
	}

	private void makePanes() {
		
		won = new HBox();
		won.setAlignment(Pos.CENTER);
		Label lbl2 = new Label("Aantal keer gewonnen: ");
		Label lbl3 = new Label();
		lbl3.setText("" + winGame); // uit de database aantal halen van een int omzetten naar String en dan hierin
									// zetten
		lbl2.setFont(new Font(20));
		lbl3.setFont(new Font(20));
		won.getChildren().addAll(lbl2, lbl3);

		lost = new HBox();
		lost.setAlignment(Pos.CENTER);
		Label lbl4 = new Label("Aantal keer verloren: ");
		Label lbl5 = new Label();
		lbl5.setText("" + lostGame);
		lbl4.setFont(new Font(20));
		lbl5.setFont(new Font(20));
		lost.getChildren().addAll(lbl4, lbl5);
	}

	private void calculateWinner() {
		if(databeest.getFinishedGames(username) != null) {
			finishedGames = databeest.getFinishedGames(username);
		}
		
		if (finishedGames.size() != 0) {
			for (int i = 0; i < finishedGames.size(); i++) {
				winner = menuController.getWinner(finishedGames.get(i));

				if (winner.equals(username)) {
					winGame++;
				} else if (!winner.equals(username)) {
					lostGame++;
				}

			}
		}
	}
}
