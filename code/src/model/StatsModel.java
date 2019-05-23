package model;

import controller.MasterController;
import databeest.DbPlayerStatsCollector;
import javafx.scene.paint.Color;

public class StatsModel {//waar moet dit worden aangemaakt en moet de mastercontroller krijgen
	
	private DbPlayerStatsCollector collector;
	private String username;
	private int highscore;
	private int won;
	private int loss;
	private Color mostPlacedColor;
	private int mostPlacedValue;
	private int totalEnemies;
	
	public StatsModel(String username, DbPlayerStatsCollector collector) {
		this.username = username;
		this.collector = collector;
		getStats();
	}
	
	public String getUsername() {
		return username;
	}

	public int getWon() {
		return won;
	}
	
	public void setWon(int won) {
		this.won = won;
	}
	
	public int getLoss() {
		return loss;
	}
	
	public void setLoss(int loss) {
		this.loss = loss;
	}
	
	public Color getMostPlacedColor() {
		return mostPlacedColor;
	}
	
	public void setMostPlacedColor(Color mostPlacedColor) {
		this.mostPlacedColor = mostPlacedColor;
	}
	
	public int getMostPlacedValue() {
		return mostPlacedValue;
	}
	
	public void setMostPlacedValue(int mostPlacedValue) {
		this.mostPlacedValue = mostPlacedValue;
	}
	
	public int getTotalEnemies() {
		return totalEnemies;
	}
	
	public void setTotalEnemies(int totalEnemies) {
		this.totalEnemies = totalEnemies;
	}
	
	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	private void getStats() {
		collector.getStats(this);
	}
	
}
