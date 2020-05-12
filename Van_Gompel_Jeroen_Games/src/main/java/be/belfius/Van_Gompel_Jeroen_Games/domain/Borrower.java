package be.belfius.Van_Gompel_Jeroen_Games.domain;

import com.mysql.cj.core.util.StringUtils;

public class Borrower {
	private int id;
	private String borrower_name;
	private String street;
	private String house_number;
	private String bus_number;
	private int postcode;
	private String city;
	private String telephone;
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBorrower_name() {
		return borrower_name;
	}
	public void setBorrower_name(String borrower_name) {
		this.borrower_name = borrower_name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouse_number() {
		return house_number;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
	}
	public String getBus_number() {
		return bus_number;
	}
	public void setBus_number(String bus_number) {
		this.bus_number = bus_number;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return StringUtils.padString(borrower_name, 30) 
				+ StringUtils.padString(street,20) 
				+ StringUtils.padString(house_number, 13) 
				+ StringUtils.padString(bus_number==null?"":bus_number, 4) 
				+ StringUtils.padString(Integer.toString(postcode),9)
				+ StringUtils.padString(city,30)
				+ StringUtils.padString(telephone==null?"":telephone,15)
				+ StringUtils.padString(email==null?"":email,30);
	}
	public static String printHeader() {
		String head1 = "";
		String head2 = "";
		
		head1 = StringUtils.padString("borrower_name", 30) 
				+ StringUtils.padString("street",20) 
				+ StringUtils.padString("house_number", 13) 
				+ StringUtils.padString("bus", 4) 
				+ StringUtils.padString("postcode",9)
				+ StringUtils.padString("city",30)
				+ StringUtils.padString("telephone",15)
				+ StringUtils.padString("email",30);
		head2 = StringUtils.padString("_____________", 30) 
				+ StringUtils.padString("______",20) 
				+ StringUtils.padString("____________", 13) 
				+ StringUtils.padString("___", 4) 
				+ StringUtils.padString("________",9)
				+ StringUtils.padString("____",30)
				+ StringUtils.padString("_________",15)
				+ StringUtils.padString("_____",30);
		return head1 + "\n" + head2;
	}
}
