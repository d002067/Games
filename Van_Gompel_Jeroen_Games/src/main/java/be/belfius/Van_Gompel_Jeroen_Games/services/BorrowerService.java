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
import be.belfius.Van_Gompel_Jeroen_Games.domain.Enum_ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.BorrowerRepository;

public class BorrowerService {
	private BorrowerRepository borrowerRepository = new BorrowerRepository();
	public List<Borrower> borrowerList = new ArrayList<>();
	private Enum_ListState listState = Enum_ListState.EMPTY;

	public List getBorrowerList(BorrowService borrowService) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowerList From Database");
			listState = Enum_ListState.FILLED;
			borrowerList = borrowerRepository.getBorrowerList();
			for (Borrower borrower : borrowerList) {
				borrower.borrowList = borrowService.getBorrowListForBorrower(borrower.getId());
			}
		} else {
			printInfo("BorrowerList From Object");
		}
		return borrowerList.stream().sorted(Comparator.comparing(Borrower::getBorrower_name))
				.collect(Collectors.toList());
	}

	public Borrower getBorrowerByIndex(int catIndex) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowerByIndex From Database");
			return borrowerRepository.getBorrowerByIndex(catIndex);
		} else {
			printInfo("BorrowerByIndex From Object");
			return (Borrower) borrowerList.stream().filter(borrower -> borrower.getId() == catIndex).findFirst()
					.orElse(null);
		}
	}

	public List<Borrower> getBorrowerByName(String beginLetters, BorrowService borrowService) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowerByName From Database");
			borrowerList = borrowerRepository.getBorrowerList(beginLetters);
			for (Borrower borrower : borrowerList) {
				borrower.borrowList = borrowService.getBorrowListForBorrower(borrower.getId());
			}
			return borrowerList;
		} else {
			printInfo("BorrowerByName From Object");
			return (List<Borrower>) borrowerList.stream().filter(
					borrower -> borrower.getBorrower_name().toLowerCase().startsWith(beginLetters.toLowerCase()))
					.collect(Collectors.toList());
			/*
			 * List<Borrower> filteredBorrower = new ArrayList<Borrower>(); for (Borrower
			 * borrower : borrowerList) {
			 * if(borrower.getBorrower_name().toLowerCase().startsWith(beginLetters.
			 * toLowerCase())){ filteredBorrower.add(borrower); } } return filteredBorrower;
			 */
		}
	}

	public List<Borrower> getSelectedBorrower(String letters, BorrowService borrowService) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowerByName From Database");
			borrowerList = borrowerRepository.getBorrowerByPart(letters);
			for (Borrower borrower : borrowerList) {
				borrower.borrowList = borrowService.getBorrowListForBorrower(borrower.getId());
			}
			return borrowerList;
		} else {
			printInfo("BorrowerByName From Object");
			return (List<Borrower>) borrowerList.stream()
					.filter(borrower -> borrower.getBorrower_name().toLowerCase().contains(letters.toLowerCase()))
					.sorted(Comparator.comparing(Borrower::getBorrower_name))
					.collect(Collectors.toList());
			/*
			 * List<Borrower> filteredBorrower = new ArrayList<Borrower>(); for (Borrower
			 * borrower : borrowerList) {
			 * if(borrower.getBorrower_name().toLowerCase().startsWith(beginLetters.
			 * toLowerCase())){ filteredBorrower.add(borrower); } } return filteredBorrower;
			 */
		}
	}

	private void printInfo(String info) {
		// System.out.println(info);
	}
}
