package be.belfius.Van_Gompel_Jeroen_Games.repository;

public class GameRepository {
	
	public GameRepository() throws ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	//Class.forName("com.mysql.cj.jdbc.Driver");
}
