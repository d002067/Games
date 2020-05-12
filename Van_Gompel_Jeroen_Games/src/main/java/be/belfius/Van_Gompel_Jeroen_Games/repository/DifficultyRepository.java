package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Difficulty;

public class DifficultyRepository {


		public int getMaxDifficultyId() throws SQLException {
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
					"root", "");) {
				int i;
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select MAX(id) from difficulty");
				//statement.close();
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

		public List<Difficulty> getDifficultyList() throws SQLException {
			List<Difficulty> difficultyList = new ArrayList<>();
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
					"root", "");) {
				Statement statement = connection.createStatement();
				statement.execute("select * from difficulty");
				ResultSet resultSet = statement.getResultSet();

				while (resultSet.next()) {
					Difficulty difficulty = new Difficulty();
					difficulty.setId(resultSet.getInt("id"));
					difficulty.setDifficulty_name(resultSet.getString("difficulty_name"));
					difficultyList.add(difficulty);
				}
				resultSet.close();
				statement.close();
				return difficultyList;
			}
		}
		
		public Difficulty getDifficultyByIndex(int catIndex) throws SQLException{
			Difficulty difficulty = new Difficulty();
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
					"root", "");) {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from difficulty where id = " + catIndex);
				if (resultSet.next()) {				
					difficulty.setId(resultSet.getInt("id"));
					difficulty.setDifficulty_name(resultSet.getString("difficulty_name"));
				}
				resultSet.close();
				statement.close();
				return difficulty;
			}
		}
		
		public List<Difficulty> getDifficultyByName(String beginLetters) throws SQLException{
			List<Difficulty> difficultyList = new ArrayList<>();
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
					"root", "");) {
				
				PreparedStatement statement = connection.prepareStatement("select * from difficulty where lower(difficulty_name) like ?");
				statement.setString(1,beginLetters + "%".toLowerCase());
				ResultSet resultSet = statement.executeQuery();
				while (resultSet.next()) {
					Difficulty difficulty = new Difficulty();
					difficulty.setId(resultSet.getInt("id"));
					difficulty.setDifficulty_name(resultSet.getString("difficulty_name"));
					difficultyList.add(difficulty);
				}
				resultSet.close();
				statement.close();
				return difficultyList;
			}
		}
	}
