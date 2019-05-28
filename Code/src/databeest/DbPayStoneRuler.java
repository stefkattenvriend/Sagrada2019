package databeest;

public class DbPayStoneRuler {
	private DataBaseApplication db;
	
	public DbPayStoneRuler(DataBaseApplication dataBaseApplication) {
		this.db = dataBaseApplication;
	}
	
	public void addStonesToGame(int gameId) {
		db.addStones(gameId);
	}
	
	public void addStonesToPlayer(int gameId, int playerId) {
		db.addStonesToPlayer(gameId, playerId);
	}
	
	public void setStoneToCard(int gameId, int playerId, int toolcardId) {
		db.setStoneToCard(gameId, playerId, toolcardId);
	}
	
	public int getStones(int playerId, int gameId) {
		return db.getStones(playerId, gameId);
	}
}
