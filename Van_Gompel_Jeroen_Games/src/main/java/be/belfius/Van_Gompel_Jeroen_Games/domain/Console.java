package be.belfius.Van_Gompel_Jeroen_Games.domain;

import java.util.Scanner;

public class Console {

	Scanner scanner = new Scanner(System.in);

	public int showMenu() {
		String keuze = "";
		while (true) {
			System.out.println(
					"0: close \n" + "1. Show game category \n" + "2: Toon de verschillende dieren in het park \n"
							+ "3. Selecteer een dier \n" + "4. Voeg een dier toe \n" + "5. Voeder de dieren  \n"
							+ "6. Toon voedselvoorraad \n" + "7. voedselvoorraad aanvullen");

			keuze = scanner.next();
			if ("01234567".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("Maak een juiste keuze \n\n\n");
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
	
	public int askHoofdmenu() {
		String keuze;
		int intKeuze;
		while (true) {
			System.out.println("\n" + "Kies 0 om terug naar het hoofdmenu te gaan: ");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				if (intKeuze == 0) {
					break;
				}
			}
		}
		return intKeuze;
	}
	
	public int askHoofdmenu(String message) {
		String keuze;
		int intKeuze;
		System.out.println(message + "\n\n");
		while (true) {
			System.out.println("\n" + "Kies 0 om terug naar het hoofdmenu te gaan: ");
			keuze = scanner.next();
			if (isNumeric(keuze)) {
				intKeuze = Integer.parseInt(keuze);
				if (intKeuze == 0) {
					break;
				}
			}
		}
		return intKeuze;
	}

	public int askCategoryToShow() {
		String keuze = "";
		while (true) {
			System.out.println("0. Main menu \n 1. Category by index \n 2. Category by Name \n 3. Category list");
			keuze = scanner.next();
			if ("0123".indexOf(keuze) != -1 && keuze.length() == 1) {
				break;
			} else {
				System.out.println("Maak een juiste keuze \n\n\n");
			}
		}
		return Integer.parseInt(keuze);
	}
	public int askCategoryIndex(int maxId) {
		String keuze = "";
		int intKeuze;
		while (true) {
			System.out.println("Give the index of the category (1 - " + maxId + ") \n type 0 to return to the main menu");
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

}
