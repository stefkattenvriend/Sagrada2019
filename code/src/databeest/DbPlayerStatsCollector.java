package databeest;

import model.StatsModel;

public class DbPlayerStatsCollector {
	private DataBaseApplication dba;
	
	public DbPlayerStatsCollector(DataBaseApplication dba) {
		this.dba = dba;
	}
	
	public void getStats(StatsModel model) {
		model.setHighscore(dba.getPlayerHighscore(model.getUsername()));
		System.out.println(model.getHighscore());
	}
}
