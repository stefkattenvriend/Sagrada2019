package controller;

import databeest.DbTurnCollector;
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
	private int round;
	// private ChatPane chatPane;

	public TurnAdmissionChecker(DbTurnCollector dtc, String username, int gameId, DiceHolderController dhc,
			PlayerPane pp, TurnController tc, ToolCardController tcc, GameController gc) {
		this.tcc = tcc;
		this.username = username;
		this.gameId = gameId;
		this.dtc = dtc;
		this.dhc = dhc;
		this.pp = pp;
		this.tc = tc;
		this.gc = gc;
		this.myTurn = false;

	}

	public void run() {
		while (playing) {
				checkMyTurn();
			round = dtc.getRoundNumber(gameId);
			tc.updateChat(); // update chat automatisch hoop ik
		}
	}

	private void checkMyTurn() {
		if (!tcc.exception()) {
			if (dtc.myTurn(username, gameId)) {
				if (!myTurn) {
					dhc.switchTurnInteractable(true);
					gc.setCurrentPlayer(true);
					pp.yourTurn();
					if(round == 11) {
					pp.showEndPane();
					}
					myTurn = true;
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("TurnAdmissionController checkMyTurn sleep error");
					e.printStackTrace();
				}
			} else {
				// dont allow something
				// System.out.println("not my turn");
				dhc.switchTurnInteractable(false);
				gc.setCurrentPlayer(false);
				if(round == 11) {
					pp.showEndPane();
					}
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

	public void setMyTurn(boolean b) {
		this.myTurn = b;
		
	}

}
