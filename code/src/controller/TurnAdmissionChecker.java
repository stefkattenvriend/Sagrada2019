package controller;


import databeest.DbTurnCollector;
import model.GameModel;
import view.GamePanes.ChatPane;
import view.GamePanes.PlayerPane;

public class TurnAdmissionChecker implements Runnable {

	private boolean myTurn;
	private DbTurnCollector dtc;
	private String username;
	private int gameId;
	private DiceHolderController dhc;
	private PlayerPane pp;
	private boolean playing = true;
	private TurnController tc;
	private ToolCardController tcc;
	private GameController gc;
	private boolean allInteractible;
//	private ChatPane chatPane;
	
	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId, DiceHolderController dhc, PlayerPane pp, TurnController tc, ToolCardController tcc, GameController gc) {
		this.tcc = tcc;
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
		this.dhc = dhc;
		this.pp = pp;
		this.tc = tc;
		this.gc = gc;
		
	}
	
	public void run() {
		while(playing) {
			checkMyTurn();
//			tc.updatePass(); //hoeft niet automatisch toch? aldus milan.
//			if(myTurn) {
////				pp.setLabel("Aan de beurt: ja");
//				tcc.setTurn(true);
//			}
//			if(!myTurn) {
////				pp.setLabel("Aan de beurt: nee");
//				tcc.setTurn(false);
//			}
			
			tc.updateChat(); //update chat automatisch hoop ik
		}
	}
	
	private void checkMyTurn() {
		if (!tcc.exception()) {
			if (dtc.myTurn(username, gameId)) {
				dhc.switchTurnInteractable(true);
				
				if (myTurn) {
					gc.setCurrentPlayer(true);	//zou ervoor moeten zorgen dat zodra het jouw turn is de game nog 1 keer update voor laatste gegevens
				}
				myTurn = true;
				
				
				pp.yourTurn();
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("TurnAdmissionController checkMyTurn sleep error");
					e.printStackTrace();
				}
			} else {
				//dont allow something
	//			System.out.println("not my turn");
				dhc.switchTurnInteractable(false);
				myTurn = false;
				gc.setCurrentPlayer(false);
				allInteractible = false;
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("TurnAdmissionController checkMyTurn sleep error");
					e.printStackTrace();
				}
			}
		}
	}

	public void start() {
		playing = true;
	}
	
	
	public void stop() {
		playing = false;
		
	}
	
	

}
