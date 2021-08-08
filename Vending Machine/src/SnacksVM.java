import API.VendingMachine;
import Money.CashManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import API.Inventory;
import vm.Coin;
import vm.Note;
import vm.Slot;
import vm.Item;

public class SnacksVM implements VendingMachine{
	public  Inventory<Item, Integer> itemInventory= new Inventory<Item, Integer>();
	private Inventory<Coin, Integer> coinInventory = new Inventory<Coin, Integer>();
	private Inventory<Note, Integer> noteInventory = new Inventory<Note, Integer>();
	private Item currentItem;
	private int currentBalance;
	
	
	
	public SnacksVM() {
		super();
		fillVindingMachine();
	}

	private void fillVindingMachine() {
				// first row
				this.itemInventory.putInventory(new Item("chips 1", 30), 30);
				this.itemInventory.putInventory(new Item("chips 2", 10), 10);
				this.itemInventory.putInventory(new Item("chips 3", 50), 10);
				this.itemInventory.putInventory(new Item("chips 4", 100), 10);
				this.itemInventory.putInventory(new Item("chips 5", 70), 10);
				// second row

				this.itemInventory.putInventory(new Item("chocolate 1", 80), 10);
				this.itemInventory.putInventory(new Item("chocolate 2", 90), 10);
				this.itemInventory.putInventory(new Item("chocolate 3", 100), 10);
				this.itemInventory.putInventory(new Item("chocolate 4", 120), 10);
				this.itemInventory.putInventory(new Item("chocolate 5", 130), 10);
				
				// third row

				this.itemInventory.putInventory(new Item("Sandwich 1", 60), 10);
				this.itemInventory.putInventory(new Item("Sandwich 2", 70), 10);
				this.itemInventory.putInventory(new Item("Sandwich 3", 80), 10);
				this.itemInventory.putInventory(new Item("Sandwich 4", 33), 10);
				this.itemInventory.putInventory(new Item("Sandwich 5", 22), 10);
				
				// fourth row

				this.itemInventory.putInventory(new Item("Pie 1", 66),10);
				this.itemInventory.putInventory(new Item("Pie 2", 70), 10);
				this.itemInventory.putInventory(new Item("Pie 3", 35), 10);
				this.itemInventory.putInventory(new Item("Pie 4", 66), 10);
				this.itemInventory.putInventory(new Item("Pie 5", 66), 10);
				
				
				// fifth row
				
				this.itemInventory.putInventory(new Item("Indomie 1", 33), 10);
				this.itemInventory.putInventory(new Item("Indomie 2", 43), 10);
				this.itemInventory.putInventory(new Item("Indomie 3", 54), 10);
				this.itemInventory.putInventory(new Item("Indomie 4", 66), 10);
				this.itemInventory.putInventory(new Item("Indomie 5", 65), 10);
				
				//cash
				this.coinInventory.putInventory(Coin.TENCENTS, 10);
				this.coinInventory.putInventory(Coin.ONEDOLLAR, 10);
				this.coinInventory.putInventory(Coin.FIFTYCENTS,10);
				this.coinInventory.putInventory(Coin.TWENTYCENTS,10);
				this.coinInventory.putInventory(Coin.TWENTYCENTS,10);
				this.noteInventory.putInventory(Note.FIFTYDOLLARS,10);
				this.noteInventory.putInventory(Note.TWENTYDOLLARS,1);
				
				this.setCurrentBalance();
				
	}
	
	private void setCurrentBalance() {
		
		if(this.coinInventory.getInventory().size() >0 || this.noteInventory.getInventory().size() >0){
		List<Integer> cashCoinList = this.coinInventory.getInventory().entrySet()
				.stream().map(e->e.getKey().getCoinValue()*e.getValue())
				.collect(Collectors.toList());
		
		List<Integer> cashNotesList = this.noteInventory.getInventory().entrySet()
				.stream().map(e->e.getKey().getNotesValue()*e.getValue())
				.collect(Collectors.toList());
		
		
		Optional<Integer> currentBalanceNotes = cashNotesList.stream().reduce(Integer::sum);			
		Optional<Integer> currentBalanceCoins = cashCoinList.stream().reduce(Integer::sum);	
		
		this.currentBalance = currentBalanceCoins.get().intValue() + currentBalanceNotes.get().intValue();
		
		}
	}
		
	
	
	
	
	
	
	@Override
	public int selectItemGetPrice(Item item) throws Exception {
		// TODO Auto-generated method stub
		List<Entry<Item, Integer>> itemPrice = this.itemInventory.getInventory().entrySet().stream().filter(e->e.getKey().getName().equals(item.getName())).collect(Collectors.toList());
		if(!itemPrice.isEmpty()){
		Item selectedItem = itemPrice.get(0).getKey();
		this.currentItem = selectedItem;
		return (int)selectedItem.getPrice();
		}else{
			throw new Exception("item Not available");
		}
	}
	
	@Override
	public Optional<Slot> insertedMoney(List<Coin> coins, List<Note> notes) throws Exception, Exception {
		
		Slot slot= null;
		if(currentItem != null){
			Optional<Integer> insertedCoinValue = coins.stream().map(e->e.getCoinValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
			Optional<Integer> insertedNoteValue = notes.stream().map(e->e.getNotesValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);

			int insertedCoinsValue = insertedCoinValue.get().intValue();
			int insertedNotesValue = insertedNoteValue.get().intValue();
			
			
			if(insertedCoinsValue < this.currentItem.getPrice() && insertedNotesValue<this.currentItem.getPrice()){
				slot=new Slot(new Item("Not a fullPaid"), coins, notes);
			}else{
				try {
					
					slot = this.getItemsAndChange(insertedCoinsValue,insertedNotesValue);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		Slot returnBucket =  slot==null ?new Slot(new Item("You Are Not Select Any Item"),coins, notes):slot;
		 Optional<Slot> opt = Optional.ofNullable(returnBucket);
		return opt;
	}
	
	@Override
	public Slot getItemsAndChange(int insertedCoins, int incertedNotes) throws Exception {
		// TODO Auto-generated method stub
		this.addToCashInventory(insertedCoins+incertedNotes);
		this.setCurrentBalance();
		
		int changeValue = this.getChanged(insertedCoins+incertedNotes, (int) this.currentItem.getPrice());
		this.subtractCoinsFromInventory(changeValue);
		this.currentBalance = this.currentBalance-changeValue;
		this.removedItemFromInventory();
		
		
		System.out.println("\n\tReturn Value: "+changeValue/100 + "$" +" and "+changeValue%100+" cent");
		CashManager cash = new CashManager();
		return new Slot(this.currentItem, cash.convertToCoin(new ArrayList<Coin>(),changeValue%100),cash.convertToNotes(new ArrayList<Note>(), changeValue));
	}
	
	public void displayInsertedMoney(List<Coin> coins ,List<Note> notes){
		Optional<Integer> insertedCoinValue = coins.stream().map(e->e.getCoinValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);
		Optional<Integer> insertedNoteValue = notes.stream().map(e->e.getNotesValue()).collect(Collectors.toList()).stream().reduce(Integer::sum);

		int insertedCoinsValue = insertedCoinValue.get().intValue();
		int insertedNotesValue = insertedNoteValue.get().intValue();

		System.out.println("------------------------------------------------");

		int accomulation=insertedCoinsValue/100;
		insertedCoinsValue=insertedCoinsValue%100;
		System.out.println("\n\tInserted Value: "+((int)(insertedNotesValue/100)+accomulation)+"$ and "+insertedCoinsValue+" cent");

		System.out.println("------------------------------------------------");

	}
	
	public void displayCardPaymet(int price) {
		System.out.println("\n\t you paid: "+price);

	}
	
	private void removedItemFromInventory(){
		int itemCount= this.itemInventory.getInventory().get(currentItem);
		this.itemInventory.getInventory().put(currentItem, itemCount-1);
	}
	
	
	private void subtractCoinsFromInventory(int changedValue){
		
		if(changedValue>=Coin.FIFTYCENTS.getCoinValue()){
			int value =  this.putCoinAndDecrement(Coin.FIFTYCENTS, changedValue);
			if(value!=0){
				subtractCoinsFromInventory(value);
			}

		}else if(changedValue>=Coin.ONEDOLLAR.getCoinValue()){
			int value = this.putCoinAndDecrement(Coin.ONEDOLLAR, changedValue);
			if(value!=0){
				subtractCoinsFromInventory(value);
			}

		}else if(changedValue>=Coin.TENCENTS.getCoinValue()){
			int value = this.putCoinAndDecrement(Coin.TENCENTS, changedValue);
			if(value!=0){
				subtractCoinsFromInventory(value);
			}

		}else if(changedValue>=Coin.TWENTYCENTS.getCoinValue()){
			int value = this.putCoinAndDecrement(Coin.TWENTYCENTS, changedValue);
			if(value!=0){
				subtractCoinsFromInventory(value);
			}
		}
		
		else subtractNoteFromInventory (changedValue);
		
	}
	
	private void subtractNoteFromInventory(int changedValue) {
		
		 if (changedValue>=Note.FIFTYDOLLARS.getNotesValue()){
			int value  = this.putNotesAndDecrement(Note.FIFTYDOLLARS, changedValue);
			if(value !=0){
				subtractCoinsFromInventory(value);
			}

		}else if(changedValue>=Note.TWENTYDOLLARS.getNotesValue()){
			int value = this.putNotesAndDecrement(Note.TWENTYDOLLARS, changedValue);
			if(value!=0){
				subtractCoinsFromInventory(value);
			}
		}
	}
	
	private void addToCashInventory(int insertedMoney){
			if(insertedMoney>=Coin.TENCENTS.getCoinValue()){
				int value = this.putCoinAndIncreament(Coin.TENCENTS, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}

			}else if(insertedMoney>=Coin.FIFTYCENTS.getCoinValue()){
				int value = this.putCoinAndIncreament(Coin.FIFTYCENTS, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}
			}else if(insertedMoney>=Coin.TWENTYCENTS.getCoinValue()){
				int value = this.putCoinAndIncreament(Coin.TWENTYCENTS, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}
			}else if(insertedMoney>=Coin.ONEDOLLAR.getCoinValue()){
				int value = this.putCoinAndIncreament(Coin.ONEDOLLAR, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}
				
			}else if(insertedMoney>=Note.FIFTYDOLLARS.getNotesValue()){
				int value = this.putNotesAndIncrement(Note.FIFTYDOLLARS, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}
			}else if(insertedMoney>=Note.TWENTYDOLLARS.getNotesValue()){
				int value = this.putNotesAndIncrement(Note.TWENTYDOLLARS, insertedMoney);
				if(value!=0){
					addToCashInventory(value);
				}
			}
	}
	
	private int putCoinAndDecrement(Coin coin, int changedValue){
		int reminder = changedValue/coin.getCoinValue();
		int numberOfCoin =this.coinInventory.getInventory().get(coin);
		if(numberOfCoin>reminder)
			numberOfCoin=numberOfCoin-reminder;
		this.coinInventory.getInventory().put(coin, numberOfCoin);
		int value  =  changedValue-(reminder*coin.getCoinValue());
		return value;
	}
	
	private int putNotesAndDecrement(Note note, int changedValue){
		int reminder = changedValue/note.getNotesValue();
		int numberOfNote =this.noteInventory.getInventory().get(note);
		if(numberOfNote>reminder)
			numberOfNote=numberOfNote-reminder;
		this.noteInventory.getInventory().put(note, numberOfNote);
		int value  =  changedValue-(reminder*note.getNotesValue());
		return value;
	}
	
	private int putCoinAndIncreament(Coin coin, int insertedCoinValue){
		int reminder = insertedCoinValue/coin.getCoinValue();
		int numberOfCoin =this.coinInventory.getInventory().get(coin);
		numberOfCoin=reminder+numberOfCoin;
		this.coinInventory.getInventory().put(coin, numberOfCoin);
		int value  =  insertedCoinValue-(reminder*coin.getCoinValue());
		return value;
	}
	
	
	private int putNotesAndIncrement(Note note, int insertedCoinValue){
		int reminder = insertedCoinValue/note.getNotesValue();
		int numberOfNote =this.noteInventory.getInventory().get(note);
		numberOfNote=reminder+numberOfNote;
		this.noteInventory.getInventory().put(note, numberOfNote);
		int value  =  insertedCoinValue-(reminder*note.getNotesValue());
		return value;
	}
	
	private int getChanged(int insertedValue, int itemPrice){
		if(insertedValue > itemPrice){
			return insertedValue-itemPrice;
		}else{
			
			return itemPrice - insertedValue;
		}
	}
	
	public void reset() {
		this.currentItem = null;
	}
	
	

}
