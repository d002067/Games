package be.belfius.Van_Gompel_Jeroen_Games.domain;

import java.util.Scanner;

import be.belfius.Van_Gompel_Jeroen_Games.Games;

public class Console {

	Scanner scanner = new Scanner(System.in);

	public int showMenu() {
		String keuze = "";
		while (true) {
			System.out.println(
					"0: close \n" + "1. Game category \n" + "2. Game \n"
							+ "3. Borrower \n" + "4.  \n" + "5.   \n"
							+ "6.  \n" + "7. ");

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
			System.out.println("0. Main menu \n1. Game by index \n2. Game by Name \n3. Game list (All information) \n4. Game list (Editor information) \n5. Game list (Player informatiion)");
			keuze = scanner.next();
			if ("012345".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("Maak een juiste keuze \n");
			}
		}
		return Integer.parseInt(keuze);
	}

	public int askGameIndex(int maxId) {
		String keuze = "";
		int intKeuze;
		while (true) {
			System.out
					.println("Give the index of the game (1 - " + maxId + ") \ntype 0 to return to the main menu");
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
}
