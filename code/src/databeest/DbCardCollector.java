package databeest;

import java.util.ArrayList;

public class DbCardCollector {
	private DataBaseApplication dataBaseApplication;

	public DbCardCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public ArrayList<Integer> getToolcards(int gameId) {
		return dataBaseApplication.getToolCards(gameId);
	}

	public ArrayList<Integer> getObjectivecards(int gameId) {
		return dataBaseApplication.getObjectiveCards(gameId);
	}
}
