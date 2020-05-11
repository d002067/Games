package be.belfius.Van_Gompel_Jeroen_Games.domain;

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
}
