package be.belfius.Van_Gompel_Jeroen_Games.domain;

import java.util.Scanner;

import be.belfius.Van_Gompel_Jeroen_Games.Games;

public class Console {

	Scanner scanner = new Scanner(System.in);

	public int showMenu() {
		String keuze = "";
		while (true) {
			System.out.println("0: close \n" + "1. Game category \n" + "2. Game \n" + "3. Borrower \n" + "4. Report ");

			keuze = scanner.next();
			if ("01234567".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("Maak een juiste keuze \n");
			}
		}
		return Integer.parseInt(keuze);
	}

	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void message(String message) {
		System.out.println(message);
	}

	public void askHoofdmenu() {
		String keuze;
		int intKeuze;
		while (true) {
			System.out.println("\n" + "Type 0 to return to the main menu: ");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				if (intKeuze == 0) {
					break;
				}
			}
		}
		Games.showHoofdMenu();
	}

	public void askHoofdmenu(String message) {
		String keuze;
		int intKeuze;
		System.out.println(message + "\n\n");
		while (true) {
			System.out.println("\n" + "Type 0 to return to the main menu: ");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				if (intKeuze == 0) {
					break;
				}
			}
		}
		Games.showHoofdMenu();
	}

	public int askCategoryToShow() {
		String keuze = "";
		while (true) {
			System.out.println("0. Main menu \n1. Category by index \n2. Category by Name \n3. Category list");
			keuze = scanner.next();
			if ("0123".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("invalid input \n");
			}
		}
		return Integer.parseInt(keuze);
	}

	public int askCategoryIndex(int maxId) {
		String keuze = "";
		int intKeuze;
		while (true) {
			System.out
					.println("Give the index of the category (1 - " + maxId + ") \ntype 0 to return to the main menu");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				{
					if (intKeuze > -1 && intKeuze <= maxId) {
						break;
					}
				}
			}
		}
		return Integer.parseInt(keuze);
	}

	public String askCategoryName() {
		String keuze;
		System.out.println("Give the first letters of the category (min 2) \ntype 0 to return to the main menu");
		while (true) {

			keuze = scanner.next();
			if (keuze.length() >= 2 || keuze.equals("0")) {
				break;
			} else {
				System.out.println("Give at least 2 letters");
			}
		}
		return keuze;
	}

	public int askGameToShow() {
		String keuze = "";
		while (true) {
			System.out.println(
					"0. Main menu \n1. Game by index \n2. Game by Name \n3. Game list (All information) \n4. Game list (Editor information) \n5. Game list (Player information) \n6. Select game \n7. Borrowed Games \n8. Borrowed Games (expanded) \n9. Select minimum difficulty");
			keuze = scanner.next();
			if ("0123456789".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("this value is not valid \n");
			}
		}
		return Integer.parseInt(keuze);
	}

	public int askGameIndex(int maxId) {
		String keuze = "";
		int intKeuze;
		while (true) {
			System.out.println("Give the index of the game (1 - " + maxId + ") \ntype 0 to return to the main menu");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				{
					if (intKeuze > -1 && intKeuze <= maxId) {
						break;
					}
				}
			}
		}
		return Integer.parseInt(keuze);
	}

	public Enum_Difficulty askDifficulty(Enum_Difficulty currentDifficulty) {

		String keuze;
		System.out.println("Your current difficulty = " + currentDifficulty.getInfo()[1]);
		System.out.println("Chose a new minimum difficulty. Gamelists will only show games for at least this difficulty (Except Borrow lists)");

		System.out.println();

		Enum_Difficulty newDifficulty = currentDifficulty;
		String menu = "";
		for (Enum_Difficulty difficulty : Enum_Difficulty.values()) {
			menu += difficulty.value[2];
		}
		System.out.println(menu);
		while (true) {

			keuze = scanner.next();
			if ("12345".indexOf(keuze) != -1 && keuze.length() == 1) {
				switch (keuze) {
				case "1":
					newDifficulty = Enum_Difficulty.VERY_EASY;
					break;
				case "2":
					newDifficulty = Enum_Difficulty.EASY;
					break;
				case "3":
					newDifficulty = Enum_Difficulty.AVERAGE;
					break;
				case "4":
					newDifficulty = Enum_Difficulty.DIFFICULT;
					break;
				case "5":
					newDifficulty = Enum_Difficulty.VERY_DIFFICULT;
					break;
				default:
					break;
				}
				break;
			} else {
				System.out.println("this value is not valid \n");
			}
		}
		return newDifficulty;
	}

	public String askGameName() {
		String keuze;
		System.out.println("Give the first letters of the game (min 2) \ntype 0 to return to the main menu");
		while (true) {
			keuze = scanner.next();
			if (keuze.length() >= 2 || keuze.equals("0")) {
				break;
			} else {
				System.out.println("Give at least 2 letters");
			}
		}
		return keuze;
	}

	public String askSelectGame() {
		String keuze;
		System.out.println(
				"Give a part of the game Name (min 2 letters). Borrowed games are shown in uppercase. \ntype 0 to return to the main menu");
		while (true) {
			keuze = scanner.next();
			if (keuze.length() >= 2 || keuze.equals("0")) {
				break;
			} else {
				System.out.println("Give at least 2 letters");
			}
		}
		return keuze;
	}

	public int askBorrowerToShow() {
		String keuze = "";
		while (true) {
			System.out.println("0. Main menu \n1. Borrower by Name \n2. Borrower list");
			keuze = scanner.next();
			if ("012".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("invalid input \n");
			}
		}
		return Integer.parseInt(keuze);
	}

	public String askBorrowerName() {
		String keuze;
		System.out.println("Give the first letters of the borrower name (min 2)  \ntype 0 to return to the main menu");
		while (true) {
			keuze = scanner.next();
			if (keuze.length() >= 2 || keuze.equals("0")) {
				break;
			} else {
				System.out.println("Give at least 2 letters");
			}
		}
		return keuze;
	}

	public String askSelectBorrower() {
		String keuze;
		System.out.println("Give a part of borrower Name (min 2 letters). \ntype 0 to return to the main menu");
		while (true) {
			keuze = scanner.next();
			if (keuze.length() >= 2 || keuze.equals("0")) {
				break;
			} else {
				System.out.println("Give at least 2 letters");
			}
		}
		return keuze;
	}
	public int askReports() {
		String keuze = "";
		while (true) {
			System.out.println(
					"0. Main menu \n1. Game report \n2. Borrow report ");
			keuze = scanner.next();
			if ("012".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("this value is not valid \n");
			}
		}
		return Integer.parseInt(keuze);
	}
}
