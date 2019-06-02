package controller;

import java.util.ArrayList;
import java.util.Random;

import databeest.DbToolCardCollector;
import helpers.DiceHolderType;
import model.DiceModel;
import view.GamePanes.CardPane;

// gemaakt door TESS!!!!!!!!!!
public class ToolCardController {
	PayStoneController psc;
	DbToolCardCollector tcc;
	int gameid;
	private ArrayList<CardPane> selected = new ArrayList<CardPane>();
	private boolean turn = true;
	DiceHolderController dhc;
	private boolean exception = false;
	private int amountOfMoves = 0;
	private int waitTill = 0;
	private int selectedToolcard = 0;

	public ToolCardController(PayStoneController psc, DbToolCardCollector tcc, int gameid, DiceHolderController dhc) {
		this.dhc = dhc;
		this.tcc = tcc;
		this.psc = psc;
		this.gameid = gameid;
	}
	
	public void addAmountOfMoves() {
		amountOfMoves = amountOfMoves + 1;
	}
	
	public void useCard(CardPane cardpane) {
		
		System.out.println();								//Debug
		System.out.println("ToolCardDebug:");
		System.out.println("You clicked card:" + cardpane.getCardNr());
		System.out.println("price: " + tcc.getPrice(cardpane.getCardNr(), gameid));
		System.out.println("number: " + cardpane.getCardNr());
		System.out.println("gameid: " + gameid);
		
		
		if (cardpane.getCardNr() == 1) {
			// na het kiezen dobbelsteen, waarde 1 verhogen of verlagen (bij 1 niet -1 bij 6
			// niet +1)
			if(dhc.GetSelectedDiceHolder() != null) {
				if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die
					if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {		//if it has a die, then check if its in offer pane
						if(psc.canPay(tcc.getPrice(1, gameid))) {
							System.out.println("price: " + tcc.getPrice(1, gameid));
							exception = true;
							dhc.solveTC1(this);
							psc.pay(1, tcc.getPrice(1, gameid));
							//pay for the card.
						}
					}
				}
			}
		}
		
		if (cardpane.getCardNr() == 2) {
			// verplaats dobbelsteen in raam. kleur voorwaardes negeren
			if(psc.canPay(tcc.getPrice(2, gameid))) {
				System.out.println("price: " + tcc.getPrice(1, gameid));
				exception = true;
				dhc.setAllUninteractable();
				dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
				dhc.setCheckColor(false); 															//make it ignore color
				psc.pay(2, tcc.getPrice(2, gameid));					//pay
				
			}
		}
		
		if (cardpane.getCardNr() == 3) {
			// verplaats dobbelsteen in raam voorwaarde voor waardes negeren
			if(psc.canPay(tcc.getPrice(3, gameid))) {
				System.out.println("price: " + tcc.getPrice(1, gameid));
				exception = true;
				dhc.setAllUninteractable();
				dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
				dhc.setCheckEyes(false); 															//make it ignore eyes
				psc.pay(2, tcc.getPrice(3, gameid));												//pay
			}
		}
		
		if (cardpane.getCardNr() == 4) {
			// verplaats 2 dobbelstenen
			if(psc.canPay(tcc.getPrice(4, gameid))) {
				System.out.println("price: " + tcc.getPrice(1, gameid));
				exception = true;
				dhc.setAllUninteractable();
				dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
				psc.pay(2, tcc.getPrice(4, gameid));												//pay
			}
		}
		
		if (cardpane.getCardNr() == 5) {
			if(dhc.GetSelectedDiceHolder() != null) {
				if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die
					if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.PLAYERWINDOW) {		//if it has a die, then check if its in offer pane
						if(psc.canPay(tcc.getPrice(5, gameid))) {
							System.out.println("price: " + tcc.getPrice(1, gameid));
							exception = true;
							dhc.setAllUninteractable();
							dhc.setTypeToInteractable(DiceHolderType.ROUNDTRACK, true);
							psc.pay(1, tcc.getPrice(5, gameid));
							//pay for the card.
						}
					}
				}
			}
			// dobbelsteen kiezen daarna wisselen met eentje van roundtrack
		}
		
		if(cardpane.getCardNr() == 6) {
			if(dhc.GetSelectedDiceHolder() != null) {
				if(dhc.GetSelectedDiceHolder().getDie() != null) {
					if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {
						if(psc.canPay(tcc.getPrice(6, gameid))) {
							Random rand = new Random();
							int nr = rand.nextInt(5 + 1);
							dhc.changeDieEyes(nr, dhc.GetSelectedDiceHolder());
							psc.pay(6, tcc.getPrice(5, gameid));
						}
					}
				}
			}
			// reroll a dice in offer
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
//			dice.get(0).setEyes(7 - dice.get(0).getEyes());
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
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean exception() {
		return exception;
	}
	
	public void finish1() {
		exception = false;
	}
	
	public void setamountOfMoves(int i) {
		amountOfMoves = i;
		if(waitTill != 0) {
			if(i == waitTill) {
				switch(selectedToolcard)  {
				case 2:
					returnToNormal();
					return;
				case 3:
					returnToNormal();
					return;
				case 4:
					returnToNormal();
					return;
				case 5:
					returnToNormal();
					return;
				}
			}
		}
	}
	
	
	public void returnToNormal() {
		exception = false;
		dhc.setCheckColor(true);
		dhc.setCheckEyes(true);
		dhc.setCheckNextTo(true);
	}
}