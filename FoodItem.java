package StoreItems;
import java.time.*;
//Malky Sokol

public class FoodItem extends Item {
	LocalDate expirationDate;
	/**
	 * this constructor is if the user passed the date as a string
	 * @param name
	 * @param barcode
	 * @param price
	 * @param expirationDate
	 */
	public FoodItem(String name, int[] barcode, double price, String expirationDate) {
		this(name, barcode, price, LocalDate.parse(expirationDate));
	}
	/**
	 * This constructs a food item
	 * @param name
	 * @param barcode
	 * @param price
	 * @param expirationDate
	 */
	public FoodItem(String name, int[] barcode, double price, LocalDate expirationDate) {
		//pass the first 3 values to the super constructor
		super(name, barcode, price);
		this.expirationDate = expirationDate;
	}
	/**
	 * this method returns the expiration date
	 * @return expirationDate
	 */
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	/**
	 * this method overrides the parents method and returns false because food is not taxable
	 */
	@Override
	public boolean isTaxable() {
		return false;
	}
	/**
	 * This method returns the values of the instance variables formatted in a String
	 */
	@Override
	public String toString() {
		//use the StringBuilder class
		StringBuilder str = new StringBuilder("Food Item\n");
		str.append(super.toString());
		str.append("\tExpiration Date " + expirationDate + "\n");
		return str.toString();
	}

}
