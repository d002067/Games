package be.belfius.Van_Gompel_Jeroen_Games;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.cj.core.util.StringUtils;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrow;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Borrower;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Category;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Console;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Enum_Difficulty;
import be.belfius.Van_Gompel_Jeroen_Games.domain.Game;
import be.belfius.Van_Gompel_Jeroen_Games.services.BorrowerService;
import be.belfius.Van_Gompel_Jeroen_Games.services.CategoryService;
import be.belfius.Van_Gompel_Jeroen_Games.services.DifficultyService;
import be.belfius.Van_Gompel_Jeroen_Games.services.GameService;
import be.belfius.Van_Gompel_Jeroen_Games.services.BorrowService;

public class Games {
	private static Console console = new Console();
	private static GameService gameService;
	private static BorrowerService borrowerService = new BorrowerService();
	private static DifficultyService difficultyService = new DifficultyService();
	private static CategoryService categoryService = new CategoryService();
	private static BorrowService borrowService = new BorrowService();
	private static Enum_Difficulty enumDifficulty = Enum_Difficulty.VERY_EASY;

	public static void main(String[] args) {
		System.out.println("Jeroen Van Gompel --- Van_Gompel_Jeroen_Games");
		System.out.println();
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
			showBorrowerMenu();
			break;
		case 4:
			showReports();
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
		case 4:
			showReports();
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
					break;
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
		case 4:
		case 5:
			showGameList(keuze - 3);
			break;
		case 6:
			showGameListForSelect();
			break;
		case 7:
			showBorrowedGames();
			break;
		case 8:
			showBorrowedGames(0);
			break;
		case 9:
			showGameList(enumDifficulty);
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
				System.out.println(Game.printHeader(0));
				Game game = gameService.getGameByIndex(keuze, categoryService, difficultyService);
				System.out.println(game.toString(0));
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
					break;
				} else {
					List<Game> gameList = gameService.getGameByName(beginLetters, categoryService, difficultyService,
							borrowService);
					if (!gameList.isEmpty()) {
						System.out.println(Game.printHeader(0));
						for (Game game : gameList) {
							System.out.println(game.toString(0));
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

	private static void showGameList(int output) {
		try {
			List<Game> gameList = gameService.getGameList(categoryService, difficultyService, borrowService);
			List<Game> gameList2 = gameList.stream()
					.filter(game -> enumDifficulty.value[0].toString()
							.indexOf(Integer.toString(game.getDifficulty_id()).trim()) != -1)
					.collect(Collectors.toList());

			System.out.println(Game.printHeader(output));
			for (Game game : gameList2) {
				System.out.println(game.toString(output));
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showGameList(Enum_Difficulty thisEnumDifficulty) {
		try {
			enumDifficulty = console.askDifficulty(thisEnumDifficulty);
			List<Game> gameList = gameService.getGameList(categoryService, difficultyService, borrowService);
			List<Game> gameListF = gameList.stream()
					.filter(game -> enumDifficulty.value[0].toString()
							.indexOf(Integer.toString(game.getDifficulty_id()).trim()) != -1)
					.collect(Collectors.toList());

			System.out.println(Game.printHeader(0));
			for (Game game : gameListF) {
				System.out.println(game.toString(0));
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showBorrowedGames() {
		try {
			List<Borrow> borrowList = borrowService.getBorrowList(gameService, borrowerService, categoryService,
					difficultyService);
			System.out.println(Borrow.printHeader());
			for (Borrow borrow : borrowList) {
				System.out.println(borrow.toString());
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showBorrowedGames(int output) {
		try {
			List<Game> gameList = gameService.getGameList(categoryService, difficultyService, borrowService);
			System.out.println(Game.printHeader(output));
			for (Game game : gameList.stream().filter(game -> !game.getBorrowList().isEmpty())
					.collect(Collectors.toList())) {
				System.out.println(game.toString(output));
				if (!game.borrowList.isEmpty()) {
					for (Borrow borrow : game.borrowList) {
						Borrower borrower = borrowerService.getBorrowerByIndex(borrow.getBorrower_id());
						System.out.println("\t- Borrower: " + borrower.toString());
						System.out.println("\t- Borrow date: " + (borrow.getBorrow_date() == null ? ""
								: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getBorrow_date())));
						System.out.println("\t- Return date: " + (borrow.getReturn_date() == null ? ""
								: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getReturn_date())));
					}
				}
			}
			// gameList.stream().filter(game ->
			// !game.getBorrowList().isEmpty()).collect(Collectors.toList());
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showGameListForSelect() {
		try {
			List<Game> gameList = gameService.getGameList(categoryService, difficultyService, borrowService);
			int i = 0;
			for (Game game : gameList) {
				if (i % 4 == 0) {
					System.out.println(StringUtils.padString(
							game.borrowList.isEmpty() ? game.getGame_name() : game.getGame_name().toUpperCase(), 40));
				} else {
					System.out.print(StringUtils.padString(
							game.borrowList.isEmpty() ? game.getGame_name() : game.getGame_name().toUpperCase(), 40));
				}
				i++;
			}
			while (true) {
				System.out.println();
				String letters = console.askSelectGame();
				if (letters.equals("0")) {
					showHoofdMenu();
					break;
				} else {
					List<Game> gameList2 = gameService.getSelectedGame(letters, categoryService, difficultyService,
							borrowService);
					if (!gameList2.isEmpty()) {
						System.out.println(Game.printHeader(0));
						for (Game game : gameList2) {
							System.out.println(game.toString(0));
							if (!game.borrowList.isEmpty()) {
								for (Borrow borrow : game.borrowList) {
									Borrower borrower = borrowerService.getBorrowerByIndex(borrow.getBorrower_id());
									System.out.println("\t- Borrower: " + borrower.toString());
									System.out.println("\t- Borrow date: " + (borrow.getBorrow_date() == null ? ""
											: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getBorrow_date())));
									System.out.println("\t- Return date: " + (borrow.getReturn_date() == null ? ""
											: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getReturn_date())));
								}
							}
						}
						// break;
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

	private static void showBorrowerMenu() {
		int keuze = console.askBorrowerToShow();
		switch (keuze) {
		case 0:
			showHoofdMenu();
			break;
		case 1:
			showBorrowerByName();
			break;
		case 2:
			showBorrowerList();
			break;
		default:
			console.message("this value is not valid. \n\n");
			showHoofdMenu();
		}
	}

	private static void showBorrowerByName() {
		try {
			while (true) {
				String beginLetters = console.askBorrowerName();
				if (beginLetters.equals("0")) {
					showHoofdMenu();
				} else {
					List<Borrower> borrowerList = borrowerService.getBorrowerByName(beginLetters, borrowService);
					if (!borrowerList.isEmpty()) {
						System.out.println(Borrower.printHeader());
						for (Borrower borrower : borrowerList) {
							System.out.println(borrower.toString());
						}
						break;
					} else {
						console.message("no borrowers found");
					}
				}
			}
			console.askHoofdmenu();
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showBorrowerList() {
		try {
			List<Borrower> borrowerList = borrowerService.getBorrowerList(borrowService);
			System.out.println(Borrower.printHeader());
			for (Borrower borrower : borrowerList) {
				System.out.println(borrower.toString());
			}
			while (true) {
				System.out.println();
				String letters = console.askSelectBorrower();
				if (letters.equals("0")) {
					showHoofdMenu();
					break;
				} else {
					List<Borrower> borrowerList2 = borrowerService.getSelectedBorrower(letters, borrowService);
					if (!borrowerList2.isEmpty()) {
						for (Borrower borrower : borrowerList2) {
							System.out.println(borrower.getBorrower_name() + "\t" + borrower.getStreet() + "\t"
									+ borrower.getHouse_number() + "\t" + borrower.getCity());
							if (!borrower.borrowList.isEmpty()) {
								for (Borrow borrow : borrower.borrowList) {
									Game game = gameService.getGameByIndex(borrow.getGame_id(), categoryService,
											difficultyService);
									System.out.println("\t* - Game: " + game.getGame_name() + "\t" + "    Editor: "
											+ game.getEditor() + "\t" + "   Author: " + game.getAuthor());
									System.out.println("\t  - Borrow date: " + (borrow.getBorrow_date() == null ? ""
											: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getBorrow_date())));
									System.out.println("\t  - Return date: " + (borrow.getReturn_date() == null ? ""
											: new SimpleDateFormat("dd/MM/yyyy").format(borrow.getReturn_date())));
								}
							}
						}
						// break;
					} else {
						console.message("No games found");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			console.askHoofdmenu();
		}
	}

	private static void showReports() {
		int keuze = console.askReports();
		switch (keuze) {
		case 0:
			showHoofdMenu();
			break;
		case 1:
			makeGameReport();
			break;
		case 2:
			makeBorrowReport();
			break;
		default:
			console.message("this value is not valid. \n\n");
			showHoofdMenu();
		}
	}

	private static void makeGameReport() {
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("Y://Games.csv"))))) {
			printWriter.print("");
			try {
				List<Game> gameList = gameService.getGameList(categoryService, difficultyService, borrowService);
				printWriter.println(
						"Game_name" 
						+ ";" + "Editor"
						+ ";" + "Author" 
						+ ";" + "Year_edition" 
						+ ";" + "Age"
						+ ";" + "Min_players" 
						+ ";" + "Max_players" 
						+ ";" + "Category_id"
						+ ";" + "Category_name" 
						+ ";" + "Play_duration" 
						+ ";" + "Difficulty_id" 
						+ ";" + "Difficulty_name"
						+ ";" + "Price" 
						+ ";" + "Image");

				gameList.stream()
						
						.forEach(value -> printWriter.println(value.getGame_name().toString() + ";" + value.getEditor()
						+ ";" + value.getAuthor() 
						+ ";" + value.getYear_edition() 
						+ ";" + value.getAge() 
						+ ";" + value.getMin_players() 
						+ ";" + value.getMax_players() 
						+ ";" + value.getCategory_id()
						+ ";" + (value.getCategory() == null? "": value.getCategory().getCategory_name()) 
						+ ";" + value.getPlay_duration() 
						+ ";" + value.getDifficulty_id() 
						+ ";" + (value.getDifficulty() == null ? "": value.getDifficulty().getDifficulty_name())
						+ ";"  + value.getPrice() + 
						";" + value.getImage()));
				printWriter.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			Desktop.getDesktop().open(new File("Y://Games.csv"));
			console.askHoofdmenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void makeBorrowReport() {
		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File("Y://Borrows.csv"))))) {
			printWriter.print("");
			try {
				List<Borrow> borrowList = borrowService.getBorrowList(gameService, borrowerService, categoryService,
						difficultyService);
				printWriter.println(
						"Game_name" 
						+ ";" + "Borrower_name"
						+ ";" + "Borrow_date" 
						+ ";" + "Return_date" );

				borrowList.stream()						
						.forEach(value -> printWriter.println(value.getGame_name().toString() + ";" + value.getBorrower_name()
						+ ";" + new SimpleDateFormat("dd/MM/yyyy").format(value.getBorrow_date()) 
						+ ";" + new SimpleDateFormat("dd/MM/yyyy").format(value.getReturn_date()))) ;

				printWriter.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			Desktop.getDesktop().open(new File("Y://Games.csv"));
			console.askHoofdmenu();
		} catch (IOException e) {
			e.printStackTrace();
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
