package model;

import databeest.DataBaseApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardBackgrounds {
	private ImageView toolCards[] = new ImageView[3];
	private ImageView targetCards[] = new ImageView[3];
	
	
	//Jami
	CardBackgrounds() {
		//milan voeg aub hier code om de card id's te krijgen toe
		
		int firstCard = 3;
		int secondCard = 2;
		int thirdCard = 5;
		
		switch(firstCard) { //eerste kaart database
		case 1:
			ImageView iv1 = new ImageView(new Image("1.png"));
			toolCards[0] = iv1;
			break;
		case 2:
			ImageView iv2 = new ImageView(new Image("2.png"));
			break;
		case 3:
			ImageView iv3 = new ImageView(new Image("3.png"));
			break;
		case 4:
			ImageView iv4 = new ImageView(new Image("4.png"));
			break;
		case 5:
			ImageView iv5 = new ImageView(new Image("5.png"));
			break;
		case 6:
			ImageView iv6 = new ImageView(new Image("6.png"));
			break;
		case 7:
			ImageView iv7 = new ImageView(new Image("7.png"));
			break;
		case 8:
			ImageView iv8 = new ImageView(new Image("8.png"));
			break;
		case 9:
			ImageView iv9 = new ImageView(new Image("9.png"));
			break;
		case 10:
			ImageView iv10 = new ImageView(new Image("10.png"));
			break;
		case 11:
			ImageView iv11 = new ImageView(new Image("11.png"));
			break;
		case 12:
			ImageView iv12 = new ImageView(new Image("12.png"));
			break;
		default:
			System.out.println("There is no picture");
			break;
		}
	}
	
	//
	public ImageView[] getToolCards() {
		return toolCards;
	}
}
