package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
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
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select MAX((id) from category");

			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				return 0;
			}
		}
	}

	public List getCategoryList() throws SQLException {
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
			return categoryList;
		}
	}
}
