package StoreItems;
//Malky Sokol

public class PurchasedItem {
	Item item;
	int quantity;
	final double TAXRATE = .0825;
	
	/**
	 * constructor
	 * @param item
	 * @param quantity
	 */
	public PurchasedItem(Item item, int quantity) {	
		this.item = item;
		if(quantity > 0) {
			this.quantity = quantity;
		}
		//else throw an exception
		else {
			throw new IllegalArgumentException();
		}
		
	}
	/**
	 * this method returns the item
	 * @return item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * this method returns the quantity of the item
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * this method returns the cost
	 * @return cost
	 */
	public double getCost() {
		return quantity * item.getPrice();
	}
	/**
	 * this method returns the tax owed for a PurchasedItem
	 * @return tax owed
	 */
	public double getTax() {
		//if the item is taxable
		if(item.isTaxable()) {
			//calculate the tax
			return getCost() * TAXRATE;
		}
		//if an item is not taxable the tax owed is 0
		return 0;
	}
	@Override
	public boolean equals(Object o) {
		//check if the addresses are the same
		if (this == o)
			return true;
		//check if o is null
		if (o == null) 
			return false;
		// if the classes are not equal then the objects must not be equal
		if(getClass() != o.getClass())
			return false;
		//typecast o
		PurchasedItem next = (PurchasedItem)o;
		//check if the items are equal to each other
		//first check if item is pointing to null
		if(item == null) {
			//if item is pointing to null and next is not
			if(next.item != null)
				return false;
		}
		//if the items are not equal
		else if (!item.equals(next.item))
			return false;
		//check if the quantities are equal
		if(quantity != next.quantity) 
			return false;
		return true;
	}
	/**
	 * method that compares two PurchasedItems
	 * @param anotherPurchasedItem
	 * @return
	 */
	public int compareTo​(PurchasedItem anotherPurchasedItem) {
		//if  the PurchasedItems are equal
		if (this.equals(anotherPurchasedItem))
			return 0;
		//if this PurchasedItem is greater than the other
		if (this.getCost() > anotherPurchasedItem.getCost())
			return 1;
		//if this PurchasedItem is less than the other
		return -1;
	}
	/**
	 * This method returns the values of the instance variables formatted in a String
	 */
	@Override
	public String toString() {
		//use the StringBuilder class
		StringBuilder str = new StringBuilder("Purchased Item\n");
		str.append("Item " + item.toString());
		str.append("\tQuantity " + quantity + "\n");
		return str.toString();
	}
}
