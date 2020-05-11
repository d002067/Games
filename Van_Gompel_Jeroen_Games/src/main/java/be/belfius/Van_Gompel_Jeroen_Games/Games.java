package be.belfius.Van_Gompel_Jeroen_Games;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Console;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.services.BorrowerService;
import be.belfius.Van_Gompel_Jeroen_Games.services.CategoryService;
import be.belfius.Van_Gompel_Jeroen_Games.services.DifficultyService;
import be.belfius.Van_Gompel_Jeroen_Games.services.GameService;

public class Games {
	private static Console console = new Console();
	private static GameService gameService;
	private static BorrowerService borrowerService = new BorrowerService();
	private static DifficultyService difficultyService = new DifficultyService();
	private static CategoryService categoryService = new CategoryService();

	public static void main(String[] args) {
		try {
			// register database
			gameService = new GameService();
		} catch (ClassNotFoundException e) {
			console.message("Error while registering database driver \n" + e.getStackTrace().toString());
		}

		showHoofdMenu();

	}

	public static void showHoofdMenu() {
		int keuze = console.showMenu();
		switch (keuze) {
		case 0:
			System.exit(0);
			break;
		case 1:
			showCategoryMenu();
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:

			break;
		case 7:

			break;
		default:
			console.message("this value is not valid. \n\n");
			showHoofdMenu();
		}

	}

	private static void showCategoryMenu() {
		int keuze = console.askCategoryToShow();
		switch (keuze) {
		case 0:
			showHoofdMenu();
			break;
		case 1:
			showCategoryById();
			break;
		case 2:
			showCategoryByName();
			break;
		case 3:
			showCategoryList();
			break;
		default:
			console.message("this value is not valid. \n\n");
			showHoofdMenu();
		}
	}

	private static void showCategoryById() {
		try {
			int maxId = categoryService.getMaxCategoryId();
			int keuze = console.askCategoryIndex(maxId);
			if (keuze == 0) {
				showHoofdMenu();
			} else {
				Category category = categoryService.getCategoryByIndex(keuze);
				System.out.println("ID \tCATEGORY_NAME \n__ \t_________ \n" + category.getId() + "\t"
						+ category.getCategory_name());
				console.askHoofdmenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());

		}
	}

	private static void showCategoryByName() {
		try {
			String beginLetters = console.askCategoryName();
			List<Category> categoryList = categoryService.getCategoryByName(beginLetters);
			System.out.println("ID \t CATEGORY_NAME");
			System.out.println("__ \t _____________ \n");
			for (Category category : categoryList) {
				System.out.println(category.getId() + "\t" + category.getCategory_name());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());

		}

	}

	private static void showCategoryList() {
		try {
			List<Category> categoryList = categoryService.getCategoryList();
			System.out.println("ID \t CATEGORY_NAME");
			System.out.println("__ \t _____________ \n");
			for (Category category : categoryList) {
				System.out.println(category.getId() + "\t" + category.getCategory_name());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());
		}
	}

	private static void showGameMenu() {
		int keuze = console.askCategoryToShow();
		switch (keuze) {
		case 0:
			showHoofdMenu();
			break;
		case 1:
			showGameById();
			break;
		case 2:
			showGameByName();
			break;
		case 3:
			showGameList();
			break;
		default:
			console.message("this value is not valid. \n\n");
			showHoofdMenu();
		}
	}

	private static void showGameById() {
		try {
			int maxId = gameService.getMaxGameId();
			int keuze = console.askGameIndex(maxId);
			if (keuze == 0) {
				showHoofdMenu();
			} else {
				Game game = gameService.getGameByIndex(keuze);
				System.out.println(game.toString());
				console.askHoofdmenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());

		}
	}

	private static void showGameByName() {
		try {
			String beginLetters = console.askGameName();
			List<Game> gameList = gameService.getGameByName(beginLetters);
			for (Game game : gameList) {
				System.out.println(game.toString());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());

		}

	}

	private static void showGameList() {
		try {
			List<Game> gameList = gameService.getGameList();
			for (Game game : gameList) {
				System.out.println(game.toString());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());
		}
	}
}