package controller;

import java.util.ArrayList;
import java.util.Collections;

import view.LayerPane;
//NOTE het random getal dat wordt gegenereerd is uiteindelijk de patroonkaart die wordt weergegeven. - Joery
public class LayerController {
	//maakt random ints -> worden uiteindelijk 4 random patroonkaarten.
	
	public LayerPane layerPane;
	private int totalAmoundOfptrnCards = 10; //deze informatie moet uit de database gehaald worden.
	private int fourPatternCards = 4;
	private ArrayList<Integer> patternCards = new ArrayList<Integer>(); //arraylist met alle patroonkaarten in zich.
	private int[] randomPat = new int[4]; //array met 4 random gekozen patroonkaarten opgeslagen.
	
	public LayerController() {
		layerPane = new LayerPane(this);
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
		return randomPat.clone();
	}
	
	
	
	
	
}
