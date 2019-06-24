package controller;

import java.util.Random;

import databeest.DbToolCardCollector;
import helpers.DiceHolderType;
import view.GamePanes.CardPane;

// gemaakt door TESS!!!!!!!!!! overgenomen door jami
public class ToolCardController {
	private PayStoneController psc;
	private DbToolCardCollector tcc;
	private GameController gc;
	private boolean turn = true;
	private DiceHolderController dhc;
	private boolean exception = false;
	private int amountOfMoves = 0;
	private int waitTill = 0;
	private int selectedToolcard = 0;
	boolean allowed = true;
	private boolean again = false;

	public ToolCardController(PayStoneController psc, DbToolCardCollector tcc, DiceHolderController dhc, GameController gc) {
		this.dhc = dhc;
		this.tcc = tcc;
		this.psc = psc;
		this.gc = gc;
	}
	
	public void useCard(CardPane cardpane) {
		int gameid = gc.getGameId();
		if (!again) {
			if (gc.isCurrentPlayer()) {
				if (allowed) {					//gebruikt voor toolcard 8
					if (psc.canPay(tcc.getPrice(cardpane.getCardNr(), gameid))) {
						if (selectedToolcard == 0) {						
							if (cardpane.getCardNr() == 1) {
								// na het kiezen dobbelsteen, waarde 1 verhogen of verlagen (bij 1 niet -1 bij 6
								// niet +1)
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die							
										exception = true;
										dhc.solveTC1(this);
										amountOfMoves = 0;
										waitTill = 1;
										selectedToolcard = 1;
										psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
									}
								}
							}
							
							if (cardpane.getCardNr() == 2) {			//works reliable 
							// verplaats dobbelsteen in raam. kleur voorwaardes negeren
								if (dhc.getAmountOfDice() > 0) {
									exception = true;
									dhc.setAllUninteractable();
									dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
									dhc.setCheckColor(false); 															//make it ignore color
									dhc.addMove();
									amountOfMoves = 0;
									waitTill = 1;
									selectedToolcard = 2;
									psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));					//pay
								}
							}
							
							if (cardpane.getCardNr() == 3) {		//probably works, not tested extended
							// verplaats dobbelsteen in raam voorwaarde voor waardes negeren
								if (dhc.getAmountOfDice() > 0) {
									exception = true;
									dhc.setAllUninteractable();
									dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
									dhc.setCheckEyes(false); 															//make it ignore eyes
									dhc.addMove();
									amountOfMoves = 0;
									waitTill = 1;
									selectedToolcard = 3;
									psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));								//pay
								}
							}
							
							if (cardpane.getCardNr() == 4) {		//fix the test to see if window has at least two dices
																	//make sure the same dice cant be moved twice, 
																	//make it so it doesnt count its previous location as a dice
																	//make it so you cant use your normal turn to move in the window (if you use it after activation)
							// verplaats 2 dobbelstenen
								if (dhc.getAmountOfDice() > 1) {
									exception = true;
									dhc.setAllUninteractable();
									dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
									dhc.addMove();
									dhc.addMove();
									dhc.setCard4(true);
									amountOfMoves = 0;
	
									waitTill = 2;
	
									selectedToolcard = 4;
									psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));							//pay
								}
							}
							
							if (cardpane.getCardNr() == 5) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {		//if it has a die, then check if its in offer pane
	
											exception = true;
											dhc.setAllUninteractable();
											dhc.setTypeToInteractable(DiceHolderType.ROUNDTRACK, true);
											psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
											//pay for the card.
										}
									}
								}
								// dobbelsteen kiezen daarna wisselen met eentje van roundtrack
							}
							
							if(cardpane.getCardNr() == 6) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {{
												Random rand = new Random();
												int nr = rand.nextInt(6) + 1;
												dhc.changeDieEyes(nr, dhc.GetSelectedDiceHolder());
												psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
											}
										}
									}
								}
								// reroll a dice in offer
							}
							
							if (cardpane.getCardNr() == 7) {
								if(dhc.getTurn() == 2) {
									dhc.reroll();
									psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
								}
								// Werp alle dobbelstenen in het aanbod opnieuw, enkel bij 2e beurt
							}
							
							if (cardpane.getCardNr() == 8) {
								// na eerste beurt gelijk nieuwe dobbelsteen kiezen tweede beurt overslaan.
								if(!gc.getPlayerPaneController().getNumber8()) {
									if(dhc.getTurn() == 1) {
										gc.getPlayerPaneController().setNumber8(true);
										dhc.addMove();
										dhc.removeMove2();
	
										psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
									}
								}
							}
							
							if (cardpane.getCardNr() == 9) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {									//check if it has a die
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {		//if it has a die, then check if its in offer pane										
											if(dhc.getAmountOfDice() > 0) {
	
											exception = true;
											dhc.setAllUninteractable();
											dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);						//make it so you can only move in the window
											dhc.setCheckNextTo(false); 															//make it ignore eyes
											dhc.addMove();
											amountOfMoves = 0;
											waitTill = 1;
											selectedToolcard = 3;
											psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));	
											//pay
											}
										}
												// nadat dobbelsteen gekozen, leggen in een vak dat niet grenst aan andere steen
									}
								}
							}
							
							if (cardpane.getCardNr() == 10) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {									//check if it has a die
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {		//if it has a die, then check if its in offer pane											
	
											exception = true;							
											dhc.setEyes(7 - dhc.GetSelectedDiceHolder().getDie().getEyes(), dhc.GetSelectedDiceHolder().getDie());
											selectedToolcard = 10;
											psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));												//pay
										}
									}
								}
								// nadat dobbelsteen gekozen, mag draaien naar tegenovergestelde zijde
								//dice.get(0).setEyes(7 - dice.get(0).getEyes());
								// dobbelsteen.waarde = 7 - dobbelsteen.waarde;
							}
								
							
							if (cardpane.getCardNr() == 11) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {		//if it has a die, then check if its in offer pane
	
											if (dhc.getMoves() > 0) {
												exception = true;
												dhc.solveTC11(this);
												selectedToolcard = 11;
												psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
											}
										}
									}
								}
							}
							
							if (cardpane.getCardNr() == 12) {
								if(dhc.GetSelectedDiceHolder() != null) {
									if(dhc.GetSelectedDiceHolder().getDie() != null) {	//check if it has a die
										if(dhc.GetSelectedDiceHolder().getType() == DiceHolderType.OFFER) {
											dhc.addMove();
											dhc.addMove();
											dhc.setTypeToInteractable(DiceHolderType.PLAYERWINDOW, true);
											dhc.handleTwelve(dhc.GetSelectedDiceHolder().getDie().getDieColor());
											amountOfMoves = 0;
											waitTill = 2;
											// verplaats 2 dobbelstenen van dezelfde kleur die overeenkomen met een steen op
											// het rondespoor
											psc.pay(cardpane.getCardNr(), tcc.getPrice(cardpane.getCardNr(), gameid));
										}
									}
								}
							}
							again = true;
						}
					}	
				}
			}
		}
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean exception() {
		return exception;
	}
	
	
	public void doMove() {
		amountOfMoves += 1;
		if(amountOfMoves == waitTill) {
			returnToNormal();
		}
	}
	
	
	public void returnToNormal() {
		exception = false;
		selectedToolcard = 0;
		again = false;
		dhc.setCheckColor(true);
		dhc.setCheckEyes(true);
		dhc.setCheckNextTo(true);
		dhc.setAllUninteractable();
		dhc.setTypeToInteractable(DiceHolderType.OFFER, true);
		dhc.setCard4(false);
		dhc.handleTwelve(null);
	}
	
	
	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
		if(allowed) {
			gc.getPlayerPaneController().setNumber8(false);
		}
	}
}