package be.belfius.Van_Gompel_Jeroen_Games.domain;

public enum Enum_Difficulty {
	VERY_EASY("12345","Very easy","1. Very easy\n"), EASY("2345", "Easy","2. Easy\n"), AVERAGE("345", "Average", "3. Average \n"), DIFFICULT("45", "Difficult", "4. Difficult\n"), VERY_DIFFICULT("5", "Very Difficult", "5. Very Difficult\n");
	public String[] value;


	
	private Enum_Difficulty(String... myValue) {
		value = myValue;
	}
	public String[] getInfo() {
		return value;
	}
}
