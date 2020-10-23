package StoreItems;
import java.time.LocalDate;
import java.util.ArrayList;

//Malky Sokol

public class Purchase {
	private ArrayList<PurchasedItem> purchasedItems;
	private LocalDate date;
	private String customer;
	private int id;
	private static int idNum = 0;
	
	/**
	 * constructor that receives four values
	 * @param theItem
	 * @param quantity
	 * @param date
	 * @param Customer
	 */
	public Purchase(Item theItem, int quantity, LocalDate date, String customer) {
		//instantiate the arraylist
		purchasedItems = new ArrayList<>();
		//create a PurchasedItem to add to the ArrayList
		PurchasedItem purchasedItem = new PurchasedItem (theItem, quantity);
		purchasedItems.add(purchasedItem);
		this.date = date;
		this.customer = customer;
		id = idNum;
		idNum ++;
	}
	/**
	 * this method adds a PurchasedItem to the ArrayList
	 * @param p
	 */
	public void addPurchasedItem(PurchasedItem p) {
		purchasedItems.add(p);
	}
	/**
	 * This method takes the two parameters and constructs a PurchasedItem, which it then adds to the ArrayList
	 * @param theItem
	 * @param qtyPurchased
	 */
	public void addPurchasedItem(Item theItem, int qtyPurchased) {
		PurchasedItem p = new PurchasedItem(theItem, qtyPurchased);
		purchasedItems.add(p);
	}
	/**
	 * getter for the customer's name
	 * @return customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * getter for the date
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * this method finds the total cost of a PurchasedItem (including tax)
	 * @param pItem
	 * @return the total cost
	 */
	public double getTotalCost() {
		double ttl = 0;
		//loop through the arraylist
		for(int i = 0; i < purchasedItems.size(); i ++) {
			//find the sum of the cost
			ttl +=  purchasedItems.get(i).getCost();
		}
		//add the taxes owed to the sum of the costs
		return ttl + getTotalTax();
	}
	/**
	 * this method finds the amount of tax the user will have to pay for a PurchasedItem
	 * @param pItem
	 * @return
	 */
	public double getTotalTax() {
		double ttl = 0;
		for(int i = 0; i < purchasedItems.size(); i ++) {
			ttl += purchasedItems.get(i).getTax();
		}	
		return ttl;
	}
	/**
	 * this method finds if a given item was purchased
	 * @param theItem
	 * @return
	 */
	public Item findItem(Item theItem) {
		for(int i = 0; i < purchasedItems.size(); i++) {
			//check if any purchasedItem's item is equal to theItem
			if(purchasedItems.get(i).getItem().equals(theItem)) {
				//return a deep copy of theItem
				return new Item(theItem);
			}
		}
		return null;
	}
	/**
	 * this method checks if two purchases are equal
	 */
	@Override
	public boolean equals(Object o) {
		//check if the purchases are the same
		if (this == o)
			return true;
		//check if o is null
		if (o == null) 
			return false;
		// if the classes are not equal then the objects must not be equal
		if(getClass() != o.getClass())
			return false;
		//typecast o
		Purchase next = (Purchase)o;
		//check if the purchases are equal to each other using the id
		if(id != next.id) 
			return false;
		return true;
	}
	/**
	 * this method compares 2 purchases
	 * @param anotherPurchase
	 * @return
	 */
	public int compareTo(Purchase anotherPurchase) {
		//if the Purchases are equal
		if(this.equals(anotherPurchase))
			return 0;
		//if this Purchase is greater than the other (happened more recently)
		if (this.id > anotherPurchase.id)
			return 1;
		//if this Purchase is less than the other
			return -1;
	}
	@Override
	public String toString() {
		//use the StringBuilder class
		StringBuilder str = new StringBuilder("Purchases\n");
		str.append("Name of Customer " + customer);
		str.append("\tDate " + date);
		str.append("\tItems Purchased " + purchasedItems);
		return str.toString();
	}
}
