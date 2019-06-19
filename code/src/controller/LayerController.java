package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.LayerPane;
//NOTE het random getal dat wordt gegenereerd is uiteindelijk de patroonkaart die wordt weergegeven. - Joery
public class LayerController {
	//maakt random ints -> worden uiteindelijk 4 random patroonkaarten.
	
	public LayerPane layerPane;
	private int totalAmoundOfptrnCards;
	private int fourPatternCards = 4;
	private ArrayList<Integer> patternCards = new ArrayList<Integer>(); //arraylist met alle patroonkaarten in zich.
	private int[] randomPat = new int[4]; //array met 4 random gekozen patroonkaarten opgeslagen.
	private PatterncardController pcc;
	private GameController gc;
	
	public LayerController(PatterncardController pcc, GameController gc) {
		totalAmoundOfptrnCards = pcc.numberOfPatternCards();
		this.pcc = pcc;
		this.gc = gc;
//		System.out.println();
	}
	
	public void generateRdmPatternCards() { //voor nu alleen een int, later wordt dit verwerkt met een pattroonkaart.
		patternCards.clear();
		
		for(int i = 0; i < totalAmoundOfptrnCards; i++) {
			patternCards.add(i+1);
		}
			Collections.shuffle(patternCards);
		for(int j = 0; j < fourPatternCards; j++)
		{
			randomPat[j] = (int) patternCards.get(j);
		} 	
	}
	
	public int[] getRandomPat() {
		for (int i = 0; i < randomPat.length; i++) {
			pcc.addPatternCardChoice(randomPat[i]);
		}
		
		return randomPat.clone();
	}

	public void insertChoice(int i, int playerid) {
		String query = "INSERT INTO `patterncardoption` (`patterncard_idpatterncard`, `player_idplayer`) VALUES ('" + i + "', '" + playerid + "');";
		pcc.insertChoice(query);
		
	}
	
	public void setRandomID(int[] ids) {
		this.randomPat = ids;
	}

	public void setGameRunning(boolean b) {
		gc.setGameRunning(b);
	}
	
	public void updatePCid(int i) {
		gc.updatePCid(i);
	}
	
}
