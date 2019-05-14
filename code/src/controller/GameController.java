package controller;

import databeest.DBPatternCardInfoCollector;

public class GameController {//deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook de andere controllers aan ~Rens
	Cards cards = new Cards();//Jami wat is dit? ~Rens
	
	private DiceHolderController dhc;
	private PatterncardController pcc;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	private LayerController lyc;

	public GameController(DBPatternCardInfoCollector DatabasePTCCollector) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		dhc = new DiceHolderController();
		pcc = new PatterncardController(DatabasePTCCollector);
		lyc = new LayerController(pcc);
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
	
	
	public void newGame()
	{
		
	}
	
}
