package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardBackgroundModel {
	
	//Jami
	public ImageView getToolCard(int card) {
		
		switch(card) {
		case 1:
			ImageView iv1 = new ImageView(new Image("/toolcards/1.png"));
			return iv1;
		case 2:
			ImageView iv2 = new ImageView(new Image("/toolcards/2.png"));
			return iv2;
		case 3:
			ImageView iv3 = new ImageView(new Image("/toolcards/3.png"));
			return iv3;
		case 4:
			ImageView iv4 = new ImageView(new Image("/toolcards/4.png"));
			return iv4;
		case 5:
			ImageView iv5 = new ImageView(new Image("/toolcards/5.png"));
			return iv5;
		case 6:
			ImageView iv6 = new ImageView(new Image("/toolcards/6.png"));
			return iv6;
		case 7:
			ImageView iv7 = new ImageView(new Image("/toolcards/7.png"));
			return iv7;
		case 8:
			ImageView iv8 = new ImageView(new Image("/toolcards/8.png"));
			return iv8;
		case 9:
			ImageView iv9 = new ImageView(new Image("/toolcards/9.png"));
			return iv9;
		case 10:
			ImageView iv10 = new ImageView(new Image("/toolcards/10.png"));
			return iv10;
		case 11:
			ImageView iv11 = new ImageView(new Image("/toolcards/11.png"));
			return iv11;
		case 12:
			ImageView iv12 = new ImageView(new Image("/toolcards/12.png"));
			return iv12;
		default:
			System.out.println("There is no picture");
			ImageView iv13 = new ImageView(new Image("/toolcards/1.png"));
			return iv13;
		}
	}
	
	public ImageView getTargetCard(int card) {
		switch(card) {
		case 1:
			ImageView iv1 = new ImageView(new Image("/toolcards/1.png"));
			return iv1;
		case 2:
			ImageView iv2 = new ImageView(new Image("/toolcards/2.png"));
			return iv2;
		case 3:
			ImageView iv3 = new ImageView(new Image("/toolcards/3.png"));
			return iv3;
		case 4:
			ImageView iv4 = new ImageView(new Image("/toolcards/4.png"));
			return iv4;
		case 5:
			ImageView iv5 = new ImageView(new Image("/toolcards/5.png"));
			return iv5;
		case 6:
			ImageView iv6 = new ImageView(new Image("/toolcards/6.png"));
			return iv6;
		case 7:
			ImageView iv7 = new ImageView(new Image("/toolcards/7.png"));
			return iv7;
		case 8:
			ImageView iv8 = new ImageView(new Image("/toolcards/8.png"));
			return iv8;
		case 9:
			ImageView iv9 = new ImageView(new Image("/toolcards/9.png"));
			return iv9;
		case 10:
			ImageView iv10 = new ImageView(new Image("/toolcards/10.png"));
			return iv10;
		case 11:
			ImageView iv11 = new ImageView(new Image("/toolcards/11.png"));
			return iv11;
		case 12:
			ImageView iv12 = new ImageView(new Image("/toolcards/12.png"));
			return iv12;
		default:
			System.out.println("There is no picture");
			ImageView iv13 = new ImageView(new Image("/toolcards/1.png"));
			return iv13;
		}
	}
}
