package databeest;

import java.util.ArrayList;

public class DbCardCollector {
	private DataBaseApplication dataBaseApplication;
	
	public DbCardCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}
	
	public ArrayList<Integer> getToolcards() {
		return dataBaseApplication.getToolCards();
	}
	
	public ArrayList<Integer> getObjectivecards() {
		return dataBaseApplication.getObjectiveCards();
	}
}
