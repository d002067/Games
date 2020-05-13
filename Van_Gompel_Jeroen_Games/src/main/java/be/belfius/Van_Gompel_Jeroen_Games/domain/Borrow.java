package be.belfius.Van_Gompel_Jeroen_Games.domain;

import java.util.Date;

public class Borrow {
	int game_id;
	int borrower_id;
	Date borrow_date;
	Date return_date;
	Game game;
	Borrower borrower;
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public int getBorrower_id() {
		return borrower_id;
	}
	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}
	public Date getBorrow_date() {
		return borrow_date;
	}
	public void setBorrow_date(Date borrow_date) {
		this.borrow_date = borrow_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	
}
