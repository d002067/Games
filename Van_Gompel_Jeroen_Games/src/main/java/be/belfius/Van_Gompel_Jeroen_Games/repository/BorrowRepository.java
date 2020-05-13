package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrow;



public class BorrowRepository {
	public List<Borrow> getBorrowList() throws SQLException {
		List<Borrow> borrowList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from borrow");
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Borrow borrow = new Borrow();
				borrow.setGame_id(resultSet.getInt("game_id"));
				borrow.setBorrower_id(resultSet.getInt("borrower_id"));
				borrow.setBorrow_date(resultSet.getDate("borrow_date"));
				borrow.setReturn_date(resultSet.getDate("return_date"));
				borrowList.add(borrow);
			}
			resultSet.close();
			statement.close();
			return borrowList;
		}
	}
	
	public List<Borrow> getBorrowListForGame(int game_id) throws SQLException {
		List<Borrow> borrowList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from borrow where game_id = " + game_id);
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Borrow borrow = new Borrow();
				borrow.setGame_id(resultSet.getInt("game_id"));
				borrow.setBorrower_id(resultSet.getInt("borrower_id"));
				borrow.setBorrow_date(resultSet.getDate("borrow_date"));
				borrow.setReturn_date(resultSet.getDate("return_date"));
				borrowList.add(borrow);
			}
			resultSet.close();
			statement.close();
			return borrowList;
		}
	}
	
	public List<Borrow> getBorrowListForBorrower(int borrow_id) throws SQLException {
		List<Borrow> borrowList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from borrow where borrower_id = " + borrow_id);
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Borrow borrow = new Borrow();
				borrow.setGame_id(resultSet.getInt("game_id"));
				borrow.setBorrower_id(resultSet.getInt("borrower_id"));
				borrow.setBorrow_date(resultSet.getDate("borrow_date"));
				borrow.setReturn_date(resultSet.getDate("return_date"));
				borrowList.add(borrow);
			}
			resultSet.close();
			statement.close();
			return borrowList;
		}
	}
	
}
