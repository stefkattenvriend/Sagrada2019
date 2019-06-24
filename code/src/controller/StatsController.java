package controller;

import databeest.DbPlayerStatsCollector;
import model.StatsModel;

public class StatsController {
	
	private StatsModel playerStats;
	private StatsModel SelectedPlayerStats;
	private DbPlayerStatsCollector dbsc;
	
	public StatsController(DbPlayerStatsCollector dbsc) {
		this.dbsc = dbsc;
		playerStats = new StatsModel("123", dbsc);
	}
	
	public void getPlayerStats() {
		
	}
	
}
