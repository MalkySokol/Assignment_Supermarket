//Malky Sokol
package StoreItems;
import java.time.LocalDate;
import java.util.*;

public class Supermarket {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//instantiate an ArrayList of Purchases
		ArrayList<Purchase>purchases = new ArrayList<>();
		int num =0;
		while (num != 6) {
			System.out.println("MENU\n"
					+ " Please enter the number that corresponds to your request:\n"
					+ " 1: to make a purchase\n"
					+ " 2: to find a purchase\n"
					+ " 3: to find the tax on a purchase\n"
					+ " 4: to find the cost of a purchase\n"
					+ " 5: to display a purchase\n"
					+ " 6: EXIT\n");
			num = input.nextInt();
			switch(num) {
				case 1: 
					newPurchase(input,purchases);
					break;
				case 2:
					findPurchase(input,purchases);
					break;
				case 3:
					findTax(input,purchases);
					break;
				case 4:
					findCost(input,purchases);
					break;
				case 5: 
					displayPurchase(input,purchases);
					break;
				case 6:
					System.out.println("Thank you for shopping!");
					break;
				default:
					System.out.println("Please enter a number that is given on the menu: ");
			}
		}
	}
	/**
	 * this method allows the user to create a Purchase, and then it adds the new Purchase to the ArrrayList purchases
	 * @param input Scanner
	 * @param purchases adds the new Purchase to the ArrayList
	 */
	public static void newPurchase(Scanner input, ArrayList<Purchase> purchases) {
		input.nextLine();
		System.out.println("What is your name? ");
		String name = input.nextLine();
		//call a method to find the PurchasedItem the user is buying(which consists of an Item and a quantity)
		PurchasedItem purchasedItem = readPurchasedItem(input);
		//instantiate a purchase object
		Purchase buy = new Purchase(purchasedItem.getItem(),purchasedItem.getQuantity(),LocalDate.now(),name);
		input.nextLine();
		//ask the user if he would like to enter another item
		System.out.println("Would you like to add another item to your purchase? \nEnter y for yes or any key to complete the purchase.");
		char add = input.nextLine().toLowerCase().charAt(0);
		//while the user wishes to add another item to the Purchase
		while (add == 'y') {
			//call a method to find the PurchasedItem the user is buying(which consists of an Item and a quantity)
			PurchasedItem p = readPurchasedItem(input);
			//add the PurchasedItem to the Purchase
			buy.addPurchasedItem(p);
			input.nextLine();
			System.out.println("Would you like to add another item to your purchase? \nEnter y for yes or any key to complete the purchase.");
			add = input.nextLine().toLowerCase().charAt(0);
		}
		//add the Purchase to the ArrayList parameter purchases
		purchases.add(buy);
		System.out.println("Your purchase is complete.");
	}
	/**
	 * this method creates a PurchasedItem
	 * @param input
	 * @return PurchasedItem
	 */
	public static PurchasedItem readPurchasedItem(Scanner input) {
		//12 digit barcode
		int[] barcode1 = {0,3,6,0,0,0,2,4,1,4,5,7};
		//13 digit barcode
		int[] barcode2 = {7,2,9,0,0,0,0,5,7,2,6,7,3};
		//Items
		Item scissors = new Item("scissors",barcode1,1.50);
		Item crayons = new Item("crayons",barcode2,1.99);
		//FoodItems
		FoodItem apple = new FoodItem("apple",barcode1, 2,LocalDate.of(2020, 9, 22));
		FoodItem pear = new FoodItem("pear",barcode2, 1.45, "2020-10-15");
		//put the Items into an array so that it can be passed to another method
		Item[] items = {scissors,crayons,apple,pear};
		System.out.println("Enter the apropriate number to purchase an item: \n1:scissors\n2:crayons\n3:apple\n4:pear\n ");
		int numOfPurchase = input.nextInt();
		//input validation
		while (numOfPurchase !=1 && numOfPurchase != 2 && numOfPurchase != 3 && numOfPurchase != 4) {
			System.out.println("Please enter a number from the choice given above: ");
			numOfPurchase = input.nextInt();
		}
		Item item;
		//let item equal the appropriate item
		if(numOfPurchase == 1) {
			item = items[0];
		}
		else if(numOfPurchase == 2) {
			item = items[1];
		}
		else if(numOfPurchase == 3) {
			item = items[2];
		}
		else {
			item = items[3];
		}
		System.out.println("How many would you like? ");
		int quantity = input.nextInt();
		//input validation
		while (quantity < 0) {
			System.out.println("A negative value was entered. Please enter a positive value: ");
			quantity = input.nextInt();
		}
		//return a PurchasedItem
		return new PurchasedItem(item,quantity);
	}
	/**
	 * this method finds the Purchase in the ArrayList
	 * @param input
	 * @param purchases
	 * @return the index of the Purchase in the ArrayList
	 */
	public static int findPurchase(Scanner input, ArrayList<Purchase> purchases) {
		//swallow up the line
		input.nextLine();
		//ask the user for name and date to find the Purchase
		System.out.println("Enter the name of the person who made the purchase: ");
		String name = input.nextLine();
		System.out.println("Enter the date of the purchase formatted as yyyy-mm-dd (ex: 2007-12-03):");
		String date = input.nextLine();
		boolean found = false;
		//find the purchase
		for(int i = 0; i < purchases.size(); i++) {
			//check for the corresponding name and date
			if(purchases.get(i).getCustomer().equalsIgnoreCase(name)) {
				//if the name is equal check if the date is equal
				//parse the String date to LocalDate
				if(purchases.get(i).getDate().equals(LocalDate.parse(date))) {
					System.out.println("Your purchase is found. It is purchase " + (i+1));
					//set found to true
					found = true;
					return i;
				}
			}
		}
		//if the purchase was not found
		if(found == false) {
			System.out.println("Your purchase was not found.");
		}
		return -1;
	} 
	/**
	 * this method finds the total tax of a Purchase
	 * @param input
	 * @param purchases
	 */
	public static void findTax(Scanner input, ArrayList<Purchase>purchases) {
		//find out which Purchase the tax should be calculated on
		int index = findPurchase(input,purchases);
		if(index != -1) {
			System.out.println("The total tax is " + purchases.get(index).getTotalTax());
		}
		//if the Purchase is not found in the ArrayList
		else {
			System.out.println("This results in the tax not being able to be calculated.");
		}
	}
	/**
	 * this method finds the total cost of a Purchase
	 * @param input
	 * @param purchases
	 */
	public static void findCost(Scanner input, ArrayList<Purchase> purchases) {
		int index = findPurchase(input,purchases);
		if(index != -1) {
			System.out.println("The total cost is " + purchases.get(index).getTotalCost());
		}
		else {
			System.out.println("This results in the cost not being able to be calculated.");
		}
	}
	/**
	 * this method displays a given Purchase
	 * @param input
	 * @param purchases
	 */
	public static void displayPurchase(Scanner input, ArrayList<Purchase>purchases) {
		int index = findPurchase(input,purchases);
		if(index != -1) {
			//display the purchase
			System.out.println(purchases.get(index));
		}
		else {
			System.out.println("This results in the purchase not being able to be displayed.");
		}
	}

}
