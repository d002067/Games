package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;

public class CategoryRepository {

	public int getMaxCategoryId() throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			int i;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select MAX(id) from category");
			//statement.close();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			} else {
				i =  0;
			}
			resultSet.close();
			statement.close();
			return i;
		}
	}

	public List<Category> getCategoryList() throws SQLException {
		List<Category> categoryList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from category");
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategory_name(resultSet.getString("category_name"));
				categoryList.add(category);
			}
			resultSet.close();
			statement.close();
			return categoryList;
		}
	}
	
	public Category getCategoryByIndex(int catIndex) throws SQLException{
		Category category = new Category();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from category where id = " + catIndex);
			if (resultSet.next()) {				
				category.setId(resultSet.getInt("id"));
				category.setCategory_name(resultSet.getString("category_name"));
			}
			resultSet.close();
			statement.close();
			return category;
		}
	}
	
	public List<Category> getCategoryByName(String beginLetters) throws SQLException{
		List<Category> categoryList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			
			PreparedStatement statement = connection.prepareStatement("select * from category where lower(category_name) like ?");
			statement.setString(1,beginLetters + "%".toLowerCase());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				category.setId(resultSet.getInt("id"));
				category.setCategory_name(resultSet.getString("category_name"));
				categoryList.add(category);
			}
			resultSet.close();
			statement.close();
			return categoryList;
		}
	}
}
