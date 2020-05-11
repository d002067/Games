package be.belfius.Van_Gompel_Jeroen_Games;
import java.io.IOException;
import java.sql.SQLException;

import be.belfius.Van_Gompel_Jeroen_Games.domain.Console;
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
			//register database
			gameService = new GameService();
		}catch (ClassNotFoundException e) {
			console.message("Error while registering database driver \n" + e.getStackTrace().toString());
		}
		
		showHoofdmenu();

	}
	
	private static void showHoofdmenu()  {
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
				showHoofdmenu();
			}

	}
	
	private static void showCategoryMenu() {
		int keuze = console.askCategoryToShow();
		switch (keuze) {
		case 0:
			showHoofdmenu();
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
			showHoofdmenu();
		}		
	}
	
	private static void showCategoryById() {
		try{
			int maxId = categoryService.getMaxCategoryId();
			int keuze = console.askCategoryIndex(maxId);
			
			switch (keuze) {
			case 0:
				showHoofdmenu();
				break;
			case 1:
				showCategoryById();
				break;	
			default:
				console.message("this value is not valid. \n\n");
				showHoofdmenu();
			}		
		}catch(SQLException e) {
			console.askHoofdmenu("Database error: \n" + e.getStackTrace().toString());
		}		
	}
	
	private static void showCategoryByName() {
		
	}
	
	private static void showCategoryList() {
		
	}
	
}
