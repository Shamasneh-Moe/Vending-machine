package vm;

/**
 * @author Muhammad
 *
 */

import java.util.List;



public class Slot {
	
	private Item item;
	private List<Coin> coin;
	private List<Note> note;
	
	public Slot() {}

	public Slot(Item item, List<Coin> coin, List<Note> note) {
		super();
		this.item = item;
		this.coin = coin;
		this.note = note;
	}

	public Slot(List<Coin> coin) {
		super();
		this.coin = coin;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Coin> getCoin() {
		return coin;
	}

	public void setCoin(List<Coin> coin) {
		this.coin = coin;
	}

	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}
	
	

}
