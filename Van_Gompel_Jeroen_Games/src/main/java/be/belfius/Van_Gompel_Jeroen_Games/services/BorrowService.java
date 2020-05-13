package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrow;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.domain.ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.BorrowRepository;
import be.belfius.Van_Gompel_Jeroen_Games.repository.CategoryRepository;

public class BorrowService {
	private BorrowRepository borrowRepository = new BorrowRepository();
	public List<Borrow> borrowList = new ArrayList<>();
	private ListState listState = ListState.EMPTY;
	
	public List getBorrowList() throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("BorrowList From Database");
			borrowList = borrowRepository.getBorrowList();
			listState = ListState.FILLED;
		}else {
			printInfo("BorrowList From Object");
		}		
		return borrowList;
	}
	
	public List getBorrowListForGame(int game_id) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("BorrowListForGame From Database");
			return borrowRepository.getBorrowListForGame(game_id);
		}else {
			printInfo("BorrowList From Object");
			return  borrowList.stream().filter(borrow -> borrow.getGame_id() == game_id).collect(Collectors.toList());
		}
	}
	
	public List getBorrowListForBorrower(int borrow_id) throws SQLException {
		if (listState == ListState.EMPTY) {
			printInfo("BorrowListForBorrower From Database");
			return borrowRepository.getBorrowListForBorrower(borrow_id);
		}else {
			printInfo("BorrowListForBorrower From Object");
			return  borrowList.stream().filter(borrow -> borrow.getBorrower_id() == borrow_id).collect(Collectors.toList());
		}
	}
	
	private void printInfo(String info) {
		//System.out.println(info);
	}
}
