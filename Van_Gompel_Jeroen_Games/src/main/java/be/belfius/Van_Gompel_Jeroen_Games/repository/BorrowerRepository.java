package be.belfius.Van_Gompel_Jeroen_Games.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrower;



public class BorrowerRepository {

	public List<Borrower> getBorrowerList() throws SQLException {
		List<Borrower> borrowerList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			statement.execute("select * from borrower");
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				Borrower borrower = new Borrower();
				borrower.setId(resultSet.getInt("id"));
				borrower.setBorrower_name(resultSet.getString("borrower_name"));
				borrower.setStreet(resultSet.getString("street"));
				borrower.setHouse_number(resultSet.getString("house_number"));
				borrower.setBus_number(resultSet.getString("bus_number"));
				borrower.setPostcode(resultSet.getInt("postcode"));
				borrower.setCity(resultSet.getString("city"));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setTelephone(resultSet.getString("telephone"));
				borrowerList.add(borrower);
			}
			resultSet.close();
			statement.close();
			return borrowerList;
		}
	}
	
	public Borrower getBorrowerByIndex(int catIndex) throws SQLException{
		Borrower borrower = new Borrower();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from borrower where id = " + catIndex);
			if (resultSet.next()) {				
				borrower.setId(resultSet.getInt("id"));
				borrower.setBorrower_name(resultSet.getString("borrower_name"));
				borrower.setStreet(resultSet.getString("street"));
				borrower.setHouse_number(resultSet.getString("house_number"));
				borrower.setBus_number(resultSet.getString("bus_number"));
				borrower.setPostcode(resultSet.getInt("postcode"));
				borrower.setCity(resultSet.getString("city"));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setTelephone(resultSet.getString("telephone"));
			}
			resultSet.close();
			statement.close();
			return borrower;
		}
	}
	
	public List<Borrower> getBorrowerList(String beginLetters) throws SQLException{
		List<Borrower> borrowerList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			
			PreparedStatement statement = connection.prepareStatement("select * from borrower where lower(borrower_name) like ?");
			statement.setString(1,beginLetters + "%".toLowerCase());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Borrower borrower = new Borrower();
				borrower.setId(resultSet.getInt("id"));
				borrower.setBorrower_name(resultSet.getString("borrower_name"));
				borrower.setStreet(resultSet.getString("street"));
				borrower.setHouse_number(resultSet.getString("house_number"));
				borrower.setBus_number(resultSet.getString("bus_number"));
				borrower.setPostcode(resultSet.getInt("postcode"));
				borrower.setCity(resultSet.getString("city"));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setTelephone(resultSet.getString("telephone"));
				borrowerList.add(borrower);
			}
			resultSet.close();
			statement.close();
			return borrowerList;
		}
	}
	
	public List<Borrower> getBorrowerByPart(String letters) throws SQLException{
		List<Borrower> borrowerList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/games?useSSL=false",
				"root", "");) {
			
			PreparedStatement statement = connection.prepareStatement("select * from borrower where lower(borrower_name) like ?");
			statement.setString(1,"%" + letters + "%".toLowerCase());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Borrower borrower = new Borrower();
				borrower.setId(resultSet.getInt("id"));
				borrower.setBorrower_name(resultSet.getString("borrower_name"));
				borrower.setStreet(resultSet.getString("street"));
				borrower.setHouse_number(resultSet.getString("house_number"));
				borrower.setBus_number(resultSet.getString("bus_number"));
				borrower.setPostcode(resultSet.getInt("postcode"));
				borrower.setCity(resultSet.getString("city"));
				borrower.setEmail(resultSet.getString("email"));
				borrower.setTelephone(resultSet.getString("telephone"));
				borrowerList.add(borrower);
			}
			resultSet.close();
			statement.close();
			return borrowerList;
		}
	}
	
}
