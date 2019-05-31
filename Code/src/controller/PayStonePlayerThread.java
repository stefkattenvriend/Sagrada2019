package controller;

import model.PlayerPayStoneModel;
import view.GamePanes.PaystoneHolder;

public class PayStonePlayerThread implements Runnable {
	PayStoneController psc;
	PlayerPayStoneModel ppsm; 
	PaystoneHolder psh;
	
	public PayStonePlayerThread(PayStoneController psc, PaystoneHolder psh) {
		this.psh = psh;
		this.psc = psc;
		ppsm = new PlayerPayStoneModel();
	}

	@Override
	public void run() {
		int amount = psc.getPlayerStones();
		if (amount != ppsm.getStones()) {
			ppsm.setStones(amount);
			psh.setPaystones(amount);
		}
	}

}
