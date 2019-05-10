package controller;

import databeest.DBPatternCardInfoCollector;
import databeest.DataBaseApplication;
import databeest.DbUserInfoCollector;

public class MasterController {//een controller die alle andere aanmaakt? ~Rens
	
	
	
	private DbUserInfoCollector dbUserInfoCollector;
	private DBPatternCardInfoCollector DatabasePTCCollector;
	private DataBaseApplication databeest = new DataBaseApplication();
	
	public LoginController lc;//laat de controllers voor nu op public staan. later get en set maken
	public GameController gm;
	
	public MasterController() {
		dbUserInfoCollector = new DbUserInfoCollector(databeest);
		DatabasePTCCollector = new DBPatternCardInfoCollector(databeest);
		
		gm = new GameController(DatabasePTCCollector);
		lc = new LoginController(dbUserInfoCollector);
		
		if ((databeest.loadDataBaseDriver("com.mysql.cj.jdbc.Driver"))
				&& (databeest.makeConnection()))
		{
//			databeest.doSomeQuerying();
//			databeest.getPaternCard(1, 1, 1);
//			databeest.getPaternCard(1, 2, 1);
//			databeest.getPaternCard(1, 3, 1);
//			databeest.getPaternCard(1, 4, 1);
//			databeest.getPaternCard(1, 5, 1);
			
//			databeest.doSomeUpdating();
		}

		
	}

}
