package databeest;

public class DbPayStoneRuler {
	private DataBaseApplication db;
	
	public DbPayStoneRuler(DataBaseApplication dataBaseApplication) {
		this.db = dataBaseApplication;
	}
	
	public void addStonesToGame(int gameId) {
		db.addStones(gameId);
	}
	
	public void addStonesToPlayer(int gameId, int playerId, int amount) {
		db.addStonesToPlayer(gameId, playerId, amount);
	}
	
	public void setStoneToCard(int gameId, int playerId, int toolcardId, int amount) {
		db.setStoneToCard(gameId, playerId, toolcardId, amount);
	}
	
	public int getStones(int playerId, int gameId) {
		return db.getStones(playerId, gameId);
	}
}
