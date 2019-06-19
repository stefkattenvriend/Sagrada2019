package controller;

import java.util.ArrayList;

import databeest.DbCardCollector;
import helpers.DiceHolderType;
import javafx.scene.image.ImageView;
import model.CardBackgroundModel;
import model.DiceHolderModel;
import model.DiceModel;
import view.GamePanes.CardPane;

public class CardsController {
	
	private DbCardCollector dbCardCollector;
	private CardBackgroundModel cardBackgroundModel;
	private ToolCardController tcc;
	private ArrayList<DiceHolderModel> dhmodels;
	
	public CardsController(DbCardCollector dbCardCollector, int gameId, ToolCardController tcc, ArrayList<DiceHolderModel> dhmodels){
		this.dhmodels = dhmodels;
		this.tcc = tcc;
		this.dbCardCollector = dbCardCollector;
		cardBackgroundModel = new CardBackgroundModel(this.dbCardCollector, gameId);	
	}
	
	
	public void useCard(CardPane cardPane) {
		tcc.useCard(cardPane);
	}
	
	public ImageView getTc1(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard1());
	}
	
	public ImageView getTc2(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard2());
	}
	
	public ImageView getTc3(){
		return cardBackgroundModel.getToolCardBg(cardBackgroundModel.getToolCard3());
	}
	
	public ImageView getTgc1(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard1());
	}
	
	public ImageView getTgc2(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard2());
	}
	
	public ImageView getTgc3(){
		return cardBackgroundModel.getTargetCardbg(cardBackgroundModel.TargetCard3());
	}
	
	public Integer getTc1Nr() {
		return cardBackgroundModel.getToolCard1();
	}
	
	public Integer getTc2Nr() {
		return cardBackgroundModel.getToolCard2();
	}
	
	public Integer getTc3Nr() {
		return cardBackgroundModel.getToolCard3();
	}
	
	public Integer getTgc1Nr() {
		return cardBackgroundModel.TargetCard1();
	}
	
	public Integer getTgc2Nr() {
		return cardBackgroundModel.TargetCard2();
	}
	
	public Integer getTgc3Nr() {
		return cardBackgroundModel.TargetCard3();
	}
	
	
	public int getSharedObjectivePoints(int gameid, int playerid) { // i is de objectivecard
		int points = 0;
		
		switch () { // Voor objective kaart [i], voeg punten toe..

		case 1: // ,Tintvarieteit Sets van één van elke waarde (5 punten)
			int[] nrs = new int[] {0,0,0,0,0,0};
			for(int j = 0; j < dhmodels.size(); j++) { 
				if(dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
					if(dhmodels.get(j) != null) {
						switch (dhmodels.get(i).getDie().getEyes()) {
						case 1:
							nrs[0]++;
						case 2:
							nrs[1]++;
						case 3:
							nrs[2]++;
						case 4:
							nrs[3]++;
						case 5:
							nrs[4]++;
						case 6:
							nrs[5]++;
						default:
							System.out.println("error: Die nr did not fit the set boundaries (1 to 6) at CardsController line 95");	
						}
					}
				}
			}
			int lowest = 4;
			for(int j; j < nrs.length; j++) {
				if(nrs[j] < lowest) {
					lowest = nrs[j];
				}
			}
			points = lowest * 5;
			break;
		case 2: // Halfdonkere tinten, sets van waardes 3 & 4 ( 2 punten)
			int[] nrs1 = new int[] {0,0};
			for(int j = 0; j < dhmodels.size(); j++) { 
				if(dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW) {
					if(dhmodels.get(j) != null) {
						switch (dhmodels.get(j).getDie().getEyes()) {
						case 3:
							nrs1[0]++;
						case 4:
							nrs1[1]++;
						default:
							break;	
						}
					}
				}
			}
			int lowest1 = 20;
			for(int j; j < nrs1.length; j++) {
				if(nrs1[j] < lowest) {
					lowest = nrs1[j];
				}
			}
			points = lowest * 2;
		case 3: // Tintvarieteit per kolom ( 4 punten)
				// Uitleg: Elke kolom heeft 4 dobbelstenen er in, loop daar doorheen met forloop
				// x,
				// kijk of de ogen van deze dobbelstenen allemaal ongelijk zijn, zo ja: voeg 4
				// punten toe.
			points = 0;
			int[] totalEyes = new int[4];
			for(int k = 0; k < 5; k++) {
				for(int j = 0; j < dhmodels.size(); j++) { 
					if(dhmodels.get(j).getType() == DiceHolderType.PLAYERWINDOW && dhmodels.get(j) != null) {
						if(dhmodels.get(j).getX() == k) {
							totalEyes[dhmodels.get(j).getY()] = dhmodels.get(j).getDie().getEyes();
						}
					}
				}
				if (totalEyes[0] != totalEyes[1] && totalEyes[1] != totalEyes[2] && totalEyes[2] != totalEyes[3]
						&& totalEyes[0] != totalEyes[2] && totalEyes[1] != totalEyes[3]
						&& totalEyes[0] != totalEyes[3]) {
					points = points + 4;
				}
				
				for(int l = 0; l < totalEyes.length; l++) {
					totalEyes[l] = 0;
				}
				
			}
			break;
		case 4: // kleurvarieteit per kolom (5 punten)

			amount = 4;
			points = 0;
			diecolors = new ArrayList<>();

			for (int x = 1; x < 6; x++) {
				diecolors.clear();
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
				if (diecolors.get(0) != diecolors.get(1) && diecolors.get(1) != diecolors.get(2)
						&& diecolors.get(2) != diecolors.get(3) && diecolors.get(0) != diecolors.get(2)
						&& diecolors.get(1) != diecolors.get(3) && diecolors.get(0) != diecolors.get(3)) {
					points = points + 5;
				}
			}

			break;
		case 5: // Donkere tinten, sets van waardes 5 & 6 (2 punten)
			amount = getDiceAmountOnFrame(playerid);

			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}

			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 5) {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 6) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 2;

			break;
		case 6: // kleurvarieteit, sets van een van elke kleur ( 4 punten)

			amount = getDiceAmountOnFrame(playerid);

			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "geel") {
					counter++;
				}
			}
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "rood") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "blauw") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "groen") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			counter2 = 0;
			for (int a = 0; a < amount; a++) {
				if (diecolors.get(a) == "paars") {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}

			points = counter * 4;

			break;
		case 7: // kleurvarieteit per rij, rijen zonder herhaalde kleuren( 6 punten)

			amount = 5;
			points = 0;
			diecolors = new ArrayList<>();

			for (int y = 1; y < 5; y++) {
				diecolors.clear();
				for (int x = 1; x < 6; x++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
				if (diecolors.get(0) != diecolors.get(1) && diecolors.get(1) != diecolors.get(2)
						&& diecolors.get(2) != diecolors.get(3) && diecolors.get(3) != diecolors.get(4)
						&& diecolors.get(0) != diecolors.get(2) && diecolors.get(1) != diecolors.get(3)
						&& diecolors.get(2) != diecolors.get(4) && diecolors.get(0) != diecolors.get(3)
						&& diecolors.get(1) != diecolors.get(4) && diecolors.get(0) != diecolors.get(4)) {
					points = points + 6;
				}
			}

			break;
		case 8: // kleurdiagonalen, aantal diagonaal aangrenzende stene in dezelfde kleur ( #
				// punten )
			diecolors = new ArrayList<>();
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}
			if (diecolors.get(0) == diecolors.get(5) || diecolors.get(2) == diecolors.get(5)
					|| diecolors.get(8) == diecolors.get(5) || diecolors.get(10) == diecolors.get(5)
					|| diecolors.get(2) == diecolors.get(7) || diecolors.get(7) == diecolors.get(10)
					|| diecolors.get(10) == diecolors.get(15) || diecolors.get(18) == diecolors.get(15)
					|| diecolors.get(10) == diecolors.get(13) || diecolors.get(13) == diecolors.get(8)
					|| diecolors.get(13) == diecolors.get(16) || diecolors.get(13) == diecolors.get(18)
					|| diecolors.get(1) == diecolors.get(4) || diecolors.get(4) == diecolors.get(9)
					|| diecolors.get(1) == diecolors.get(6) || diecolors.get(6) == diecolors.get(9)
					|| diecolors.get(3) == diecolors.get(6) || diecolors.get(6) == diecolors.get(11)
					|| diecolors.get(11) == diecolors.get(14) || diecolors.get(14) == diecolors.get(9)
					|| diecolors.get(9) == diecolors.get(12) || diecolors.get(12) == diecolors.get(17)
					|| diecolors.get(17) == diecolors.get(14) || diecolors.get(14) == diecolors.get(19)) {
				points = points + 1;
			}

			break;
		case 9: // lichte tinten, waardes van 1 & 2 ( 2punten)
			amount = getDiceAmountOnFrame(playerid);

			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int x = 1; x < 6; x++) {
				for (int y = 1; y < 5; y++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y));
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}
			}

			for (int z = 0; z < amount; z++) {
				int dienumber = dienumbers.get(z);
				String diecolor = diecolors.get(z);
				int eyes = getEyes(dienumber, gameid, diecolor);
				totalEyes[z] = eyes;
			}

			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 1) {
					counter++;
				}
			}
			
			for (int a = 0; a < amount; a++) {
				if (totalEyes[a] == 2) {
					counter2++;
					if (counter2 < counter) {
						counter = counter2;
					}
				}
			}
			points = counter * 2;
			break;

		case 10: // tintvarieteit per rij ( 5 punten)
			amount = 5;
			points = 0;
			totalEyes = new int[amount];
			dienumbers = new ArrayList<>();
			diecolors = new ArrayList<>();
			counter = 0;
			counter2 = 0;
			for (int y = 1; y < 5; y++) {
				for (int x = 1; x < 6; x++) {
					dienumbers.add(dbApplication.getDieNumberinos(playerid, x, y)); // wat krijg je als er geen
																					// dobbelsteen staat op die plek..
					diecolors.add(dbApplication.getDieColorinos(playerid, x, y));
				}

				for (int z = 0; z < amount; z++) {
					int dienumber = dienumbers.get(z);
					String diecolor = diecolors.get(z);
					int eyes = getEyes(dienumber, gameid, diecolor);
					totalEyes[z] = eyes;
				}
				if (totalEyes[0] != totalEyes[1] && totalEyes[1] != totalEyes[2] && totalEyes[2] != totalEyes[3]
						&& totalEyes[3] != totalEyes[4] && totalEyes[0] != totalEyes[2] && totalEyes[1] != totalEyes[3]
						&& totalEyes[2] != totalEyes[4] && totalEyes[0] != totalEyes[3] && totalEyes[1] != totalEyes[4]
						&& totalEyes[0] != totalEyes[4]) {
					points = points + 5;
				}

			}

			break;
		default:
			points = 0;
			break;
		}
		return points;
	}
}
