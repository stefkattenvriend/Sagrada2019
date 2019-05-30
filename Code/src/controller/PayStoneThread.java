package controller;

import model.PayStoneModel;
import view.GamePanes.CardPane;

public class PayStoneThread implements Runnable {
	PayStoneController psc;
	int cardId;
	PayStoneModel psm;
	CardPane cardPane;
	
	public PayStoneThread(PayStoneController psc, int cardId, CardPane cardPane) {
		this.psc = psc;
		this.cardId = cardId;
		psm = new PayStoneModel();
		this.cardPane = cardPane;
	}
	
	public void run() {
		if(psm.getamount() != psc.getStonesOnCard(cardId)) {
			psm.setamount(psc.getStonesOnCard(cardId));
			cardPane.setPayStones(psc.getStonesOnCard(cardId));
		}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
