package view.MenuPanes;

import controller.LoginController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuWelcomePane extends FlowPane{
	
	private LoginController loginController;
	private Label label1;
	private Label label2;
	
	public MenuWelcomePane(LoginController loginController) {
		this.loginController = loginController;
		setPaneSize();
		setLabels();
//		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))); //tijdelijk
	}
	
	private void setLabels() {
		VBox text = new VBox();
		
		label1 = new Label();
		label2 = new Label();
		
		label1.setText("Welkom terug,");
		label1.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		label1.setTextFill(Color.DARKMAGENTA);
		
		label2.setText(loginController.getCurrentAccount());
		label2.setFont(Font.font ("Verdana", 30));
		label2.setTextFill(Color.BLACK);
		
		text.getChildren().addAll(label1, label2);
		text.setAlignment(Pos.CENTER);
		
		Image image = new Image("layout_images/sagradalogo.png");
		ImageView imageView = new ImageView(image);
		
		
		getChildren().addAll(imageView, text);
		setAlignment(Pos.CENTER);
	}
	
	private void setPaneSize() {
		setMinSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
		setMaxSize(MenuPane.paneWidth, MenuPane.windowMaxHeight / 3);
	}

}


