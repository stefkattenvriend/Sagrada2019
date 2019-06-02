package controller;

import databeest.DbPayStoneRuler;

public class PayStoneController {
	DbPayStoneRuler psr;
	int playerId;
	int gameId;
	
	public PayStoneController(DbPayStoneRuler psr, int playerId, int gameId) {
		this.playerId = playerId;
		this.gameId = gameId;
		this.psr = psr;
	}
	
	public void pay(int toolcardId, int cost) {
		psr.setStoneToCard(gameId, playerId, toolcardId, cost);
	}
	
	public boolean canPay(int cost) {
		if(psr.getStones(playerId, gameId) >= cost) {
			return true;
		} else {
			return false;
		}
	}
	
	public void giveStones(int idPatternCards) {
		psr.addStonesToPlayer(gameId, playerId, psr.getDifficulty(idPatternCards));
	}

	public int getStonesOnCard(int cardId) {
		return psr.getStonesOnCard(cardId, gameId);	
	}
	
	public int getPlayerStones() {
		return psr.getStones(playerId, gameId);
	}
	
}