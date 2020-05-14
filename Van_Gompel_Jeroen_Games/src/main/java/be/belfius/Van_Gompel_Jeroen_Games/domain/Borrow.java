package be.belfius.Van_Gompel_Jeroen_Games.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.core.util.StringUtils;

public class Borrow {
	int game_id;
	int borrower_id;
	Date borrow_date;
	Date return_date;
	String game_name;
	String borrower_name;
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
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getBorrower_name() {
		return borrower_name;
	}
	public void setBorrower_name(String borrower_name) {
		this.borrower_name = borrower_name;
	}
	
	public String toString() {
				return StringUtils.padString(game_name, 40) + "\t" + StringUtils.padString(borrower_name, 40) + "\t"
				+ StringUtils.padString(borrow_date == null ? ""
						:new SimpleDateFormat("dd/MM/yyyy").format(borrow_date), 11) + "\t"
				+ StringUtils.padString(return_date == null ? ""
						:new SimpleDateFormat("dd/MM/yyyy").format(return_date), 11);
	}
	public static String printHeader() {
		return StringUtils.padString("game_name", 40) + "\t" + StringUtils.padString("borrower_name", 40) + "\t"
				+ StringUtils.padString("borrow_date", 11) + "\t"
				+ StringUtils.padString("return_date", 11)
				+ "\n"
				+ StringUtils.padString("_________", 40) + "\t" + StringUtils.padString("_____________", 40) + "\t"
				+ StringUtils.padString("___________", 11) + "\t"
				+ StringUtils.padString("___________", 11)
				;
}

}
