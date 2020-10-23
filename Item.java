package StoreItems;


//Malky Sokol
public class Item {
	private int[] barcode;
	private double price;
	public String name;
	/**
	 *copy constructor
	 */
	public Item(Item theItem) {
		this.barcode = theItem.barcode;
		this.price = theItem.price;
		this.name = theItem.name;
	}
	/**
	 * This constructor accepts 4 values to construct an Item
	 * @param name
	 * @param barcode
	 * @param price
	 * @param tax
	 */
	public Item(String name, int[] barcode, double price) {
		//the barcode must be the length of 12 and 13
		if(barcode.length == 13 || barcode.length == 12 ) {
			this.barcode = barcode;
		}
		//validate that the price is a positive number
		if(price > 0) {
			this.price = price;
		}
		//else throw an exception
		else {
			throw new IllegalArgumentException();
		}
		
		this.name = name;
	}
	/**
	 * This method returns the name of the item
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * this method returns the price to the user
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * setter for the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * setter for price
	 * @param price
	 */
	public void setPrice(double price) {
		if(price > 0) {
			this.price = price;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	/**
	 * this method returns true for all taxable items
	 * @return
	 */
	public boolean isTaxable() {
		return true;
	}
	/**
	 * This method verifies that a barcode is accurate
	 * @return true or false
	 */
	public boolean verifyUPC() {
		int ttl1 = 0;
		int ttl2 = 0;
		//add up all the odd numbers in the barcode (excluding the last number in the array(the check digit)) - so begin from barcode.length -2
		//create a second var x, that will keep track of which digit place in the barcode the loop is up to(it goes the opposite direction)
		for (int i = barcode.length -2,x = 1; i >= 0; i--,x++){
			//if x is odd
			if(x % 2 != 0) {
				ttl1 += barcode[i];
				//System.out.println(ttl1);
			}
		}
		
		//multiply the result by 3
		ttl1 *= 3;
		//add the even digits
		for (int i = barcode.length -2,x = 1; i >= 0; i--,x++){
			//if the element of the barcode is even
			if(x % 2 == 0) {
				ttl2 += barcode[i];
			}
		}
		//System.out.println(ttl2);
		//add the two results together
		int ttl = ttl1 + ttl2;
		int remainder = ttl % 10;
		//if the remainder is not equal to zero, subtract it from 10
		int check = 0;
		if(remainder != 0) {
			check = 10 - remainder;
		}
		//if check is equal to the last element in the array(first element in the barcode)
		if(check == barcode[barcode.length-1]) {
			//System.out.println(check);
			return true;
		}
		
		
		return false;
	}
	/**
	 * This method returns the values of the instance variables formatted in a String
	 */
	@Override
	public String toString() {
		//use the StringBuilder class
		StringBuilder str = new StringBuilder("Item\n");
		str.append("Name of Item: " + name );
		str.append("\tPrice of Item: " + price );
		//str.append("\tTax of Item: " + tax );
		str.append("\tBarcode: ");
		for(int i = 0; i < barcode.length; i++) {
			str.append(barcode[i] + " ");
		}
		return str.toString();
	}
	
	
	
}
