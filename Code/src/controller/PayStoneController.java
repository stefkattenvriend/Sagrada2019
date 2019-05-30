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
	
	public boolean pay(int cost, int toolcardId) {
		if(psr.getStones(playerId, gameId) >= cost){
			psr.setStoneToCard(gameId, playerId, toolcardId, cost);
			return true;
		} else {
			return false;
		}
	}
	
	public void giveStones(int idPatternCards) {
		psr.addStonesToPlayer(idPatternCards, idPatternCards, psr.getDifficulty(idPatternCards));
	}
	
	public int getStonesOnCard(int cardId) {
		return psr.getStonesOnCard(cardId, gameId);
	}
}