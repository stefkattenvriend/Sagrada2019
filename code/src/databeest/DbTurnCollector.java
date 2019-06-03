package databeest;

public class DbTurnCollector {
	DataBaseApplication dataBaseApplication;

	public DbTurnCollector(DataBaseApplication dataBaseApplication) {
		this.dataBaseApplication = dataBaseApplication;
	}

	public boolean myTurn(String username, int gameId) {
		return dataBaseApplication.myTurn(username, gameId);
	}

	public int getRoundNumber(int gameID) {
		int round;
		round = dataBaseApplication.getRoundNumber(gameID);
		return round;
	}

}
