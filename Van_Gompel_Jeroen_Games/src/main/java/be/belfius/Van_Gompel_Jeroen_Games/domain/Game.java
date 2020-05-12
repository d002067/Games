package be.belfius.Van_Gompel_Jeroen_Games.domain;

import com.mysql.cj.core.util.StringUtils;

public class Game {
	private int id;
	private String game_name;
	private String editor;
	private String author;
	private int year_edition;
	private String age;
	private int min_players;
	private int max_players;
	private int category_id;
	private String play_duration;
	private int difficulty_id;
	private double price;
	private String image;
	private Difficulty difficulty = new Difficulty();
	private Category category = new Category();

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear_edition() {
		return year_edition;
	}

	public void setYear_edition(int year_edition) {
		this.year_edition = year_edition;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getMin_players() {
		return min_players;
	}

	public void setMin_players(int min_players) {
		this.min_players = min_players;
	}

	public int getMax_players() {
		return max_players;
	}

	public void setMax_players(int max_players) {
		this.max_players = max_players;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getPlay_duration() {
		return play_duration;
	}

	public void setPlay_duration(String play_duration) {
		this.play_duration = play_duration;
	}

	public int getDifficulty_id() {
		return difficulty_id;
	}

	public void setDifficulty_id(int difficulty_id) {
		this.difficulty_id = difficulty_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category gategory) {
		this.category = gategory;
	}

	public String toString(int i) {
		String result = "";
		switch (i) {
		case 0:
			result = StringUtils.padString(game_name, 40) + "\t" + StringUtils.padString(editor, 40) + "\t"
					+ StringUtils.padString(StringUtils.isNullOrEmpty(author) ? "" : author, 40) + "\t"
					+ StringUtils.padString(Integer.toString(year_edition), 8) + "\t" + StringUtils.padString(age, 20)
					+ "\t" + StringUtils.padString(Integer.toString(min_players), 12) + "\t"
					+ StringUtils.padString(Integer.toString(max_players), 12) + "\t"
					+ StringUtils.padString(Integer.toString(category_id), 12) + "\t"
					+ StringUtils.padString(category == null ? "" : category.getCategory_name(), 16) + "\t"
					+ StringUtils.padString(play_duration, 20) + "\t"
					+ StringUtils.padString(Integer.toString(difficulty_id), 16) + "\t"
					+ StringUtils.padString(
							difficulty.getDifficulty_name() == null ? "" : difficulty.getDifficulty_name(), 16)
					+ "\t" + StringUtils.padString(Double.toString(price), 8) + "\t" + image;
			break;
		case 1:
			result = StringUtils.padString(game_name, 40) + "\t" + StringUtils.padString(editor, 40) + "\t"
					+ StringUtils.padString(StringUtils.isNullOrEmpty(author) ? "" : author, 40) + "\t"
					+ StringUtils.padString(Integer.toString(year_edition), 8) + "\t"
					+ StringUtils.padString(Double.toString(price), 8) + "\t" + image;
			break;
		case 2:
			result = StringUtils.padString(game_name, 40) + "\t" + StringUtils.padString(editor, 40) + "\t"
					+ StringUtils.padString(age, 20) + "\t" + StringUtils.padString(Integer.toString(min_players), 12)
					+ "\t" + StringUtils.padString(Integer.toString(max_players), 12) + "\t"
					+ StringUtils.padString(category == null ? "" : category.getCategory_name(), 16) + "\t"
					+ StringUtils.padString(play_duration, 20) + "\t"
					+ StringUtils.padString(
							difficulty.getDifficulty_name() == null ? "" : difficulty.getDifficulty_name(), 16)
					+ "\t" + StringUtils.padString(Double.toString(price), 8);
			break;
		}
		return result;
	}

	public static String printHeader(int i) {
		String head1 = "";
		String head2 = "";
		switch (i) {

		case 0:
			head1 = StringUtils.padString("game_name", 40) + "\t" + StringUtils.padString("editor", 40) + "\t"
					+ StringUtils.padString("author", 40) + "\t" + StringUtils.padString("year_edition", 8) + "\t"
					+ StringUtils.padString("age", 20) + "\t" + StringUtils.padString("min_players", 12) + "\t"
					+ StringUtils.padString("max_players", 12) + "\t" + StringUtils.padString("category_id", 12) + "\t"
					+ StringUtils.padString("category_name", 16) + "\t" + StringUtils.padString("play_duration", 20)
					+ "\t" + StringUtils.padString("difficulty_id", 16) + "\t"
					+ StringUtils.padString("difficulty_name", 16) + "\t" + StringUtils.padString("price", 8) + "\t"
					+ "image";
			head2 = StringUtils.padString("_________", 40) + "\t" + StringUtils.padString("______", 40) + "\t"
					+ StringUtils.padString("______", 40) + "\t" + StringUtils.padString("____________", 8) + "\t"
					+ StringUtils.padString("___", 20) + "\t" + StringUtils.padString("___________", 12) + "\t"
					+ StringUtils.padString("___________", 12) + "\t" + StringUtils.padString("___________", 12) + "\t"
					+ StringUtils.padString("_____________", 16) + "\t" + StringUtils.padString("_____________", 20)
					+ "\t" + StringUtils.padString("_____________", 16) + "\t"
					+ StringUtils.padString("_______________", 16) + "\t" + StringUtils.padString("_____", 8) + "\t"
					+ "_____";
			break;
		case 1:
			head1 = StringUtils.padString("game_name", 40) + "\t" + StringUtils.padString("editor", 40) + "\t"
					+ StringUtils.padString("author", 40) + "\t" + StringUtils.padString("year_edition", 8) + "\t"
					+ StringUtils.padString("price", 8) + "\t" + "image";
			head2 = StringUtils.padString("_________", 40) + "\t" + StringUtils.padString("______", 40) + "\t"
					+ StringUtils.padString("______", 40) + "\t" + StringUtils.padString("____________", 8) + "\t"
					+ StringUtils.padString("_____", 8) + "\t" + "_____";
			break;

		case 2:
			head1 = StringUtils.padString("game_name", 40) + "\t" + StringUtils.padString("editor", 40) + "\t"
					+ StringUtils.padString("age", 20) + "\t" + StringUtils.padString("min_players", 12) + "\t"
					+ StringUtils.padString("max_players", 12) + "\t" + StringUtils.padString("category_name", 16)
					+ "\t" + StringUtils.padString("play_duration", 20) + "\t"
					+ StringUtils.padString("difficulty_name", 16) + "\t" + StringUtils.padString("price", 8);
			head2 = StringUtils.padString("_________", 40) + "\t" + StringUtils.padString("______", 40) + "\t"
					+ StringUtils.padString("___", 20) + "\t" + StringUtils.padString("___________", 12) + "\t"
					+ StringUtils.padString("___________", 12) + "\t" + StringUtils.padString("_____________", 16)
					+ "\t" + StringUtils.padString("_____________", 20) + "\t"
					+ StringUtils.padString("_______________", 16) + "\t" + StringUtils.padString("_____", 8);
			break;
		}
		return head1 + "\n" + head2;
	}

}
