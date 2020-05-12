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
			e.printStackTrace();
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
			showGameMenu();
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
			console.askHoofdmenu();

		}
	}

	private static void showCategoryByName() {
		try {
			while (true) {
				String beginLetters = console.askCategoryName();
				if (beginLetters.equals("0")) {
					showHoofdMenu();
				} else {
					List<Category> categoryList = categoryService.getCategoryByName(beginLetters);
					if (!categoryList.isEmpty()) {
						System.out.println("ID \t CATEGORY_NAME");
						System.out.println("__ \t _____________ \n");
						for (Category category : categoryList) {
							System.out.println(category.getId() + "\t" + category.getCategory_name());

						}
						break;
					} else {
						console.message("no categories found");
					}
				}
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
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
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showGameMenu() {
		int keuze = console.askGameToShow();
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
				System.out.println(Game.printHeader());
				Game game = gameService.getGameByIndex(keuze, categoryService,difficultyService);
				System.out.println(game.toString());
				console.askHoofdmenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showGameByName() {
		try {
			while (true) {
				String beginLetters = console.askGameName();
				if (beginLetters.equals("0")) {
					showHoofdMenu();
				} else {
					List<Game> gameList = gameService.getGameByName(beginLetters,categoryService,difficultyService);
					if (!gameList.isEmpty()) {
						System.out.println(Game.printHeader());
						for (Game game : gameList) {						
							System.out.println(game.toString());
						}
						break;
					} else {
						console.message("No games found");
					}
				}
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showGameList() {
		try {
			List<Game> gameList = gameService.getGameList(categoryService,difficultyService);
			System.out.println(Game.printHeader());
			for (Game game : gameList) {				
				System.out.println(game.toString());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	public static Console getConsole() {
		return console;
	}

	public static void setConsole(Console console) {
		Games.console = console;
	}

	public static GameService getGameService() {
		return gameService;
	}

	public static void setGameService(GameService gameService) {
		Games.gameService = gameService;
	}

	public static BorrowerService getBorrowerService() {
		return borrowerService;
	}

	public static void setBorrowerService(BorrowerService borrowerService) {
		Games.borrowerService = borrowerService;
	}

	public static DifficultyService getDifficultyService() {
		return difficultyService;
	}

	public static void setDifficultyService(DifficultyService difficultyService) {
		Games.difficultyService = difficultyService;
	}

	public static CategoryService getCategoryService() {
		return categoryService;
	}

	public static void setCategoryService(CategoryService categoryService) {
		Games.categoryService = categoryService;
	}
}
