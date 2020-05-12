package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrower;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.repository.BorrowerRepository;


public class BorrowerService {
	private BorrowerRepository borrowerRepository = new BorrowerRepository();
	public List<Borrower> borrowerList = new ArrayList<>();
	

	public List getBorrowerList() throws SQLException {
		if (borrowerList.isEmpty()) {
			System.out.println("BorrowerList From Database");
			borrowerList = borrowerRepository.getBorrowerList();
		}else {
			System.out.println("BorrowerList From Object");
		}		
		return borrowerList.stream()
				.sorted(Comparator.comparing(Borrower::getBorrower_name))
				.collect(Collectors.toList());
	}

	public Borrower getBorrowerByIndex(int catIndex) throws SQLException {
		if (borrowerList.isEmpty()) {
			System.out.println("BorrowerByIndex From Database");
			return borrowerRepository.getBorrowerByIndex(catIndex);
		} else {
			System.out.println("BorrowerByIndex From Object");
			return (Borrower) borrowerList.stream().filter(borrower -> borrower.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}
	
	
	public List<Borrower> getBorrowerByName(String beginLetters) throws SQLException{
		if (borrowerList.isEmpty()) {
			System.out.println("BorrowerByName From Database");
			return borrowerRepository.getBorrowerByName(beginLetters);
		} else {
			System.out.println("BorrowerByName From Object");
			return  (List<Borrower>) borrowerList.stream().filter(borrower -> borrower.getBorrower_name().toLowerCase().startsWith(beginLetters.toLowerCase())).collect(Collectors.toList());
			/*List<Borrower> filteredBorrower = new ArrayList<Borrower>();
			for (Borrower borrower : borrowerList) {
				if(borrower.getBorrower_name().toLowerCase().startsWith(beginLetters.toLowerCase())){
					filteredBorrower.add(borrower);
				}
			}
			return filteredBorrower;*/
		}
	}
}
