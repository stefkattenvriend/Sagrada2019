package controller;

import databeest.DBPatternCardInfoCollector;

public class GameController {//deze classe wordt aangemaakt in de masterController en maakt uiteindelijk ook de andere controllers aan ~Rens
	Cards cards = new Cards();//Jami wat is dit? ~Rens
	
	public DiceHolderController dhc;
	public PatterncardController pcc;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	public LayerController lc;

	public GameController(DBPatternCardInfoCollector DatabasePTCCollector) {
		this.DatabasePTCCollector = DatabasePTCCollector;
		dhc = new DiceHolderController();
		pcc = new PatterncardController(DatabasePTCCollector);
		lc = new LayerController();
	}
	
	
}
