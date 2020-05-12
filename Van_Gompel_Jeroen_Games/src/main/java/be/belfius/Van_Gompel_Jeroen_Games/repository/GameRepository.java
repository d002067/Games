package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;

public class GameRepository {
	
	public GameRepository() throws ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	public int getMaxGameId() throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			int i;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select MAX(id) from game");
			if (resultSet.next()) {
				i =  resultSet.getInt(1);
			} else {
				i = 0;
			}
			resultSet.close();
			statement.close();
			return i;
		}
	}

	public List<Game> getGameList() throws SQLException {
		List<Game> gameList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from game");
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Game game = new Game();
				game.setId(resultSet.getInt("id"));
				game.setGame_name(resultSet.getString("game_name"));
				game.setEditor(resultSet.getString("editor"));
				game.setAuthor(resultSet.getString("author"));
				game.setYear_edition(resultSet.getInt("year_edition"));
				game.setAge(resultSet.getString("age"));
				game.setMin_players(resultSet.getInt("min_players"));
				game.setMax_players(resultSet.getInt("max_players"));
				game.setCategory_id(resultSet.getInt("category_id"));
				game.setPlay_duration(resultSet.getString("play_duration"));
				game.setDifficulty_id(resultSet.getInt("difficulty_id"));
				game.setPrice(resultSet.getDouble("price"));
				game.setImage(resultSet.getString("image"));
				gameList.add(game);
			}
			resultSet.close();
			statement.close();
			return gameList;
		}
	}
	
	public Game getGameByIndex(int gameIndex) throws SQLException{
		Game game = new Game();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from game where id = " + gameIndex);
			if (resultSet.next()) {				
				game.setId(resultSet.getInt("id"));
				game.setGame_name(resultSet.getString("game_name"));
				game.setEditor(resultSet.getString("editor"));
				game.setAuthor(resultSet.getString("author"));
				game.setYear_edition(resultSet.getInt("year_edition"));
				game.setAge(resultSet.getString("age"));
				game.setMin_players(resultSet.getInt("min_players"));
				game.setMax_players(resultSet.getInt("max_players"));
				game.setCategory_id(resultSet.getInt("category_id"));
				game.setPlay_duration(resultSet.getString("play_duration"));
				game.setDifficulty_id(resultSet.getInt("difficulty_id"));
				game.setPrice(resultSet.getDouble("price"));
				game.setImage(resultSet.getString("image"));
			}
			resultSet.close();
			statement.close();
			return game;
		}
	}
	
	public List<Game> getGameByName(String beginLetters) throws SQLException{
		List<Game> gameList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			
			PreparedStatement statement = connection.prepareStatement("select * from game where lower(game_name) like ?");
			statement.setString(1,beginLetters + "%".toLowerCase());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Game game = new Game();
				game.setId(resultSet.getInt("id"));
				game.setGame_name(resultSet.getString("game_name"));
				game.setEditor(resultSet.getString("editor"));
				game.setAuthor(resultSet.getString("author"));
				game.setYear_edition(resultSet.getInt("year_edition"));
				game.setAge(resultSet.getString("age"));
				game.setMin_players(resultSet.getInt("min_players"));
				game.setMax_players(resultSet.getInt("max_players"));
				game.setCategory_id(resultSet.getInt("category_id"));
				game.setPlay_duration(resultSet.getString("play_duration"));
				game.setDifficulty_id(resultSet.getInt("difficulty_id"));
				game.setPrice(resultSet.getDouble("price"));
				game.setImage(resultSet.getString("image"));
				gameList.add(game);
			}
			resultSet.close();
			statement.close();
			return gameList;
		}
	}
}


