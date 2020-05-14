package be.belfius.Van_Gompel_Jeroen_Games.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.cj.core.util.StringUtils;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrow;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Enum_ListState;
import be.belfius.Van_Gompel_Jeroen_Games.repository.BorrowRepository;
import be.belfius.Van_Gompel_Jeroen_Games.repository.CategoryRepository;

public class BorrowService {
	private BorrowRepository borrowRepository = new BorrowRepository();
	public List<Borrow> borrowList = new ArrayList<>();
	private Enum_ListState listState = Enum_ListState.EMPTY;

	public List getBorrowList(GameService gameService, BorrowerService borrowerService, CategoryService categoryService,
			DifficultyService difficultyService) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowList From Database");
			borrowList = borrowRepository.getBorrowList();
			listState = Enum_ListState.FILLED;
		} else {
			printInfo("BorrowList From Object");
		}
		for (Borrow borrow : borrowList) {
			borrow.setBorrower_name(borrowerService.getBorrowerByIndex(borrow.getBorrower_id()).getBorrower_name());
			borrow.setGame_name(
					gameService.getGameByIndex(borrow.getGame_id(), categoryService, difficultyService).getGame_name());
		}
		return borrowList.stream()
				.sorted(Comparator.comparing(Borrow::getBorrower_name).thenComparing(Borrow::getBorrow_date))
				.collect(Collectors.toList());
	}

	public List getBorrowListForGame(int game_id) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowListForGame From Database");
			return borrowRepository.getBorrowListForGame(game_id);
		} else {
			printInfo("BorrowList From Object");
			return borrowList.stream().filter(borrow -> borrow.getGame_id() == game_id).collect(Collectors.toList());
		}
	}

	public List getBorrowListForBorrower(int borrow_id) throws SQLException {
		if (listState == Enum_ListState.EMPTY) {
			printInfo("BorrowListForBorrower From Database");
			return borrowRepository.getBorrowListForBorrower(borrow_id);
		} else {
			printInfo("BorrowListForBorrower From Object");
			return borrowList.stream().filter(borrow -> borrow.getBorrower_id() == borrow_id)
					.collect(Collectors.toList());
		}
	}

	private void printInfo(String info) {
		// System.out.println(info);
	}

}
