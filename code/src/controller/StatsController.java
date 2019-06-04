package controller;

import databeest.DbPlayerStatsCollector;
import model.StatsModel;

public class StatsController {

	private StatsModel playerStats;
	private DbPlayerStatsCollector dbsc;

	public StatsController(DbPlayerStatsCollector dbsc) {
		this.dbsc = dbsc;
		playerStats = new StatsModel("123", dbsc);
	}

	public void getPlayerStats() {
		System.out.println(playerStats.getUsername());
		System.out.println(playerStats.getHighscore());
		System.out.println(playerStats.getWon());
		System.out.println(playerStats.getLoss());
		System.out.println(playerStats.getMostPlacedColor());
		System.out.println(playerStats.getMostPlacedValue());
	}

}
