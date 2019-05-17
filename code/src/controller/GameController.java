package controller;

import databeest.DbGameCollector;
import databeest.DbPatternCardInfoCollector;
import databeest.DataBaseApplication;

public class GameController {//deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook de andere controllers aan ~Rens
	CardsController cards = new CardsController();//Jami wat is dit? ~Rens
	
	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DbPatternCardInfoCollector DatabasePTCCollector;
	private DbGameCollector dbGameCollector;
	private LayerController lyc;
	private LoginController lc;
	
	private int gameid;
	
	public GameController(DbPatternCardInfoCollector DatabasePTCCollector, DbGameCollector dbGamecollector, LoginController lc) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		this.lc = lc;
		dhc = new DiceHolderController();
		pcc = new PatterncardController(DatabasePTCCollector);
		lyc = new LayerController(pcc);
		this.dbGameCollector = dbGamecollector;
		
	}
	
	public DiceHolderController getDiceHolderController()
	{
		return dhc;
	}
	
	public PatterncardController getPatterncardController()
	{
		return pcc;
	}
	
	public LayerController getLayerController()
	{
		return lyc;
	}
	
	public void createPrivateObjective() {
		
	}
	
	public void newGame()
	{
		dbGameCollector.pushGame();
		String username = lc.getCurrentAccount();
		dbGameCollector.pushFirstPlayer(username);
	}
	
	public int getGameid() {
		return gameid;
	}
	
}
