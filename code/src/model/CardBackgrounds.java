package model;

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
			ImageView iv1 = new ImageView(new Image("/toolcards/1.png"));
			toolCards[0] = iv1;
			break;
		case 2:
			ImageView iv2 = new ImageView(new Image("/toolcards/2.png"));
			toolCards[0] = iv2;
			break;
		case 3:
			ImageView iv3 = new ImageView(new Image("/toolcards/3.png"));
			toolCards[0] = iv3;
			break;
		case 4:
			ImageView iv4 = new ImageView(new Image("/toolcards/4.png"));
			toolCards[0] = iv4;
			break;
		case 5:
			ImageView iv5 = new ImageView(new Image("/toolcards/5.png"));
			toolCards[0] = iv5;
			break;
		case 6:
			ImageView iv6 = new ImageView(new Image("/toolcards/6.png"));
			toolCards[0] = iv6;
			break;
		case 7:
			ImageView iv7 = new ImageView(new Image("/toolcards/7.png"));
			toolCards[0] = iv7;
			break;
		case 8:
			ImageView iv8 = new ImageView(new Image("/toolcards/8.png"));
			toolCards[0] = iv8;
			break;
		case 9:
			ImageView iv9 = new ImageView(new Image("/toolcards/9.png"));
			toolCards[0] = iv9;
			break;
		case 10:
			ImageView iv10 = new ImageView(new Image("/toolcards/10.png"));
			toolCards[0] = iv10;
			break;
		case 11:
			ImageView iv11 = new ImageView(new Image("/toolcards/11.png"));
			toolCards[0] = iv11;
			break;
		case 12:
			ImageView iv12 = new ImageView(new Image("/toolcards/12.png"));
			toolCards[0] = iv12;
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
