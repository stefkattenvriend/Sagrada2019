package view.MenuPanes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.StatsModel;
//Tesssss
public class MenuResultsPane extends VBox{
	private HBox won;
	private HBox lost;
import javafx.scene.layout.Pane;

	public MenuResultsPane() {
		
		setPaneSize();
		setAlignment(Pos.CENTER);
		makePanes();
		Label lbl1 = new Label("Jouw Statistieken: ");
		lbl1.setFont(new Font(40));
		getChildren().addAll(lbl1, won, lost);
		
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
		lbl3.setText("3"); //uit de database aantal halen van een int omzetten naar String en dan hierin zetten
		lbl2.setFont(new Font(20));
		lbl3.setFont(new Font(20));
		won.getChildren().addAll(lbl2, lbl3);
		
		lost = new HBox();
		lost.setAlignment(Pos.CENTER);
		Label lbl4 = new Label("Aantal keer verloren: ");
		Label lbl5 = new Label();
		lbl5.setText("4");
		lbl4.setFont(new Font(20));
		lbl5.setFont(new Font(20));
		lost.getChildren().addAll(lbl4, lbl5);
	}
}
