package controller;

import view.LayerPane;

public class LayerController {
	//maakt random ints -> worden uiteindelijk 4 random patroonkaarten.
	
	public LayerPane layerPane;
	private int[] rdmPatternCards;
	
	public LayerController() {
		layerPane = new LayerPane(this);
		rdmPatternCards = new int[4];
		
	}
	
	public void generateRdmPatternCards() { //voor nu alleen een int, later wordt dit verwerkt met een pattroonkaart.
		int min = 0; //minimaal aantal patroonkaarten.
		int max = 10; //maximaal aantal patroonkaarten -> wordt geteld uit database.
		
//		rdmPatternCards[0] = (int) ((Math.random()*((max-min)+1))+min);
		
		for(int i = 0; i < rdmPatternCards.length; i++) {
					
			rdmPatternCards[i] = (int) ((Math.random()*((max-min)+1))+min);
			
		}
		
		for(int i = 0; i < rdmPatternCards.length; i++) {
			
			for(int x = 1; x < rdmPatternCards.length; x++) {
				
				if(rdmPatternCards[i] == rdmPatternCards[x]) {				
					System.out.println(rdmPatternCards[i] + " deze is gelijk aan " + rdmPatternCards[x]);
					rdmPatternCards[i] = (int) ((Math.random()*((max-min)+1))+min);
				}
			}

		}
		
		for(int i = 0; i < rdmPatternCards.length; i++) {
			System.out.println("Nummer: " + rdmPatternCards[i]);
		}
		
		System.out.println("einde method");
		return;
	}
	
	
	
	
	
}
