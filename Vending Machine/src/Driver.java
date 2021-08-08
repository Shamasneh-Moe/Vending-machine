import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import vm.Coin;
import vm.Note;
import vm.Item;
import vm.Slot;
import vm.Card;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SnacksVM vendingMachine = new SnacksVM();
		System.out.println("-------------------------------------------------------------");
		vendingMachine.itemInventory.getInventory().keySet().forEach((item)-> {
			
			System.out.println("\t");
				System.out.println("\t"+item.getName().toString() +
						",\tprice: " + (item.getPrice() >
						100 ? item.getPrice()/100+"$" :item.getPrice()+" Cent") );
								
		});
		runMain(vendingMachine);
	}
public static void runMain (SnacksVM vendingMachine) {
		
		int choice = 0;
		
		System.out.println("------------------------------------------------------------------------");

		System.out.print("\n Select item : ");
		
		Scanner user = new Scanner(System.in);
		
		choice = user.nextInt();
		System.out.println(choice);
		user.close();
		switch (choice) {
		
		case 1 :
			
			choosenItem (vendingMachine,"Pie 3");
			break;
			
		case 2 :
			choosenItem (vendingMachine,"Sandwich 2");
			break;
			
		case 3:
			choosenItem (vendingMachine,"chocolate 1");
			break;
			
		case 4:
			choosenItem (vendingMachine,"chips 1");
			break;
			
		case 5 :
			choosenItem (vendingMachine,"Indomie 5");
			break;
			
		case 6 :
			choosenItem (vendingMachine,"Sandwich 5");
			break;
			
		case 7 :
			choosenItem (vendingMachine,"chips 4");
			break;
			
		case 8 :
			choosenItem (vendingMachine,"Indomie 1");
			break;
			
		case 9 :
			choosenItem (vendingMachine,"Pie 1");
			break;
			
		case 10 :
			choosenItem (vendingMachine,"chocolate 4");
			break;
			
		case 11:
			choosenItem (vendingMachine,"Sandwich 3");
			break;
			
		case 12 :
			choosenItem (vendingMachine,"Indomie 4");
			break;
			
		case 13 :
			choosenItem (vendingMachine,"chocolate 5");
			break;
			
		case 14 :
			choosenItem (vendingMachine,"Sandwich 4");
			break;
			
		case 15 :
			choosenItem (vendingMachine,"chocolate 3");
			break;
			
		case 16 :
			choosenItem (vendingMachine,"chips 3");
			break;
			
		case 17 :
			choosenItem (vendingMachine,"Pie 4");
			break;
			
		case 18 :
			choosenItem (vendingMachine,"Indomie 3");
			break;
			
		case 19 :
			choosenItem (vendingMachine,"chocolate 2");
			break;
			
		case 20 :
			choosenItem (vendingMachine,"Sandwich 1");
			break;
		case 21 :
			choosenItem (vendingMachine,"Pie 2");
			break;
		case 22 :
			choosenItem (vendingMachine,"chips 5");
			break;
			
		case 23 :
			choosenItem (vendingMachine,"chips 2");
			break;
				
		case 24 :
			choosenItem (vendingMachine,"Pie 5");
			break;
				
		case 25 :
			choosenItem(vendingMachine,"Indomie 2");
			break;		
			default:
				
				System.out.println("Invalid input");
				choosenItem(vendingMachine,"noItem");

		}
	}
public static void choosenItem (SnacksVM vendingMachine,String itemName) {
	
	Scanner user = new Scanner(System.in);
	try {
		Item item = new Item(itemName);
		int itemPrice =	vendingMachine.selectItemGetPrice(item);
		System.out.println("------------------------------------------------");

		System.out.println("\n\tSelected Item:"+ " "  + item.getName());
		System.out.println("\tSelected Item Price:"+ " " + itemPrice+" cent");
		
		System.out.println("------------------------------------------------");
		
		
		
		if(itemPrice != 0){
//			System.out.println("Cash or card, 1 for cash 2 for card:");
//			int choice = user.nextInt();
//			if(choice==1) {
				List <Note> insertedNotes =new ArrayList<Note>();
				List <Coin> insertedCoins = new ArrayList<Coin>();
				
		//		System.out.println("insert Notes, we only accept 20$ and 50$");
		//		boolean option=true;
		//		do {
		//			System.out.println("enter -value to stop inserting Notes:");
		//			int value = user.nextInt();
		//			if(value<0) {option=false;}
		//			else if(value==20) {insertedNotes.add(Note.TWENTYDOLLARS);}
		//			else if(value==50) {insertedNotes.add(Note.TWENTYDOLLARS);}
		//			else {System.out.println("Not valid Money Note");}
		//		}while(option);
		//		System.out.println("insert Coins, we only accept 10c,20c,50c,1$:");
		//		do {
		//			System.out.println("enter -value to stop inserting Coins:");
		//			int value = user.nextInt();
		//			if(value<0) {option=false;}
		//			else if(value==20) {insertedCoins.add(Coin.TWENTYCENTS);}
		//			else if(value==50) {insertedCoins.add(Coin.FIFTYCENTS);}
		//			else if(value==10) {insertedCoins.add(Coin.TENCENTS);}
		//			else if(value==1) {insertedCoins.add(Coin.ONEDOLLAR);}
		//			else {System.out.println("Not valid Money Note");}
		//		}while(option);
				insertedNotes.add(Note.FIFTYDOLLARS);
				insertedNotes.add(Note.TWENTYDOLLARS);
				insertedCoins.add(Coin.FIFTYCENTS);
				insertedCoins.add(Coin.TWENTYCENTS);
				insertedCoins.add(Coin.TENCENTS);
				insertedCoins.add(Coin.ONEDOLLAR);
			
		
				Optional<Slot> slot = vendingMachine.insertedMoney(insertedCoins, insertedNotes);
				
				vendingMachine.displayInsertedMoney(insertedCoins,insertedNotes);
				
				if(slot.isPresent()){
					Slot myItem = slot.get();
					if(myItem.getItem() !=null){
						System.out.println("\n\tEnjoy :)");
					}
				}
//			}else if(choice==2) {
//				List <Card> insertedCard =new ArrayList<Card>();
//				System.out.println("Enter Card Type");
//				insertedCard.add(Card.ANYTYPE);
//				int value = user.nextInt();
//				SnacksVM vm = new SnacksVM();
//				vm.displayCardPaymet(itemPrice);
//				
//			}else {
//				System.out.println("invalid method, Bye!");
//			}
			
		}
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	

}
