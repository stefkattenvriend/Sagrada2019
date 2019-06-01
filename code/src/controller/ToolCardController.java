package controller;

import java.util.ArrayList;
import java.util.Random;

import databeest.DbToolCardCollector;
import model.DiceModel;
import view.GamePanes.CardPane;
import view.GamePanes.ToolCard1Pane;

// gemaakt door TESS!!!!!!!!!!
public class ToolCardController {
	PayStoneController psc;
	DbToolCardCollector tcc;
	int gameid;
	private ArrayList<CardPane> selected = new ArrayList<CardPane>();
	private boolean turn = true;

	public ToolCardController(PayStoneController psc, DbToolCardCollector tcc, int gameid) {
		this.tcc = tcc;
		this.psc = psc;
		this.gameid = gameid;
	}
	
	public void useCard(CardPane cardpane, ArrayList<DiceModel> dice) {
		
		System.out.println();
		System.out.println("ToolCardDebug:");
		System.out.println("You clicked card:" + cardpane.getCardNr());
		System.out.println("price: " + tcc.getPrice(cardpane.getCardNr(), gameid));
		System.out.println("number: " + cardpane.getCardNr());
		System.out.println("gameid: " + gameid);
		if(turn) {
			if (selected.isEmpty()) {	
				selected.add(cardpane);
				
				if (psc.pay(tcc.getPrice(cardpane.getCardNr(), gameid), cardpane.getCardNr())) {
					System.out.println("You used card:" + cardpane.getCardNr());
					
					if (cardpane.getCardNr() == 1) {
						// na het kiezen dobbelsteen, waarde 1 verhogen of verlagen (bij 1 niet -1 bij 6
						// niet +1)
						ToolCard1Pane card1Pane = new ToolCard1Pane();
						if (card1Pane.chosen == 1) {
							if (dice.get(0).getEyes() == 1) {
								// pop up dat dit niet kan
								return;
							}
							dice.get(0).setEyes(dice.get(0).getEyes() - 1);
						}
						if (card1Pane.chosen == 2) {
							if (dice.get(0).getEyes() == 6) {
								// pop up dat dit niet kan
								return;
							}
							dice.get(0).setEyes(dice.get(0).getEyes() + 1);
						}
					}
					
					if (cardpane.getCardNr() == 2) {
						// verplaats dobbelsteen in raam. kleur voorwaardes negeren
					}
					
					if (cardpane.getCardNr() == 3) {
						// verplaats dobbelsteen in raam voorwaarde voor waardes negeren
					}
					
					if (cardpane.getCardNr() == 4) {
						// verplaats 2 dobbelstenen
					}
					
					if (cardpane.getCardNr() == 5) {
						// dobbelsteen kiezen daarna wisselen met eentje van roundtrack
					}
					
					if (cardpane.getCardNr() == 6) {
						// nadat dobbelsteen kiest opnieuw werpen
						Random random = new Random();
						int randomInt = random.nextInt(6) + 1;
						dice.get(0).setEyes(randomInt);
					}
					
					if (cardpane.getCardNr() == 7) {
						// Werp alle dobbelstenen in het aanbod opnieuw, enkel bij 2e beurt
					}
					
					if (cardpane.getCardNr() == 8) {
						// na eerste beurt gelijk nieuwe dobbelsteen kiezen tweede beurt overslaan.
					}
					
					if (cardpane.getCardNr() == 9) {
						// nadat dobbelsteen gekozen, leggen in een vak dat niet grenst aan andere steen
					}
					
					if (cardpane.getCardNr() == 10) {
						// nadat dobbelsteen gekozen, mag draaien naar tegenovergestelde zijde
						dice.get(0).setEyes(7 - dice.get(0).getEyes());
						// dobbelsteen.waarde = 7 - dobbelsteen.waarde;
					}
					
					if (cardpane.getCardNr() == 11) {
						// nadat dobbelsteen gekozen, nieuwe dobbelsteen pakken uit zak en waarde kiezen
						// op je bord of in het aanbodleggen
					}
					
					if (cardpane.getCardNr() == 12) {
						
						// verplaats 2 dobbelstenen van dezelfde kleur die overeenkomen met een steen op
						// het rondespoor
					}
				}
				
				else if(selected.get(0) == cardpane) {
					selected.clear();
				} else {
					System.out.println("please deselect your current card first");
				}
			}
		} else {
			System.out.println("not your turn!");
		}
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
}