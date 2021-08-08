package API;

/**
 * @author Muhammad
 *
 */

import java.util.HashMap;

public class Inventory<T, T2>{
	private HashMap<T,T2> inventory = new HashMap<T,T2>();

	public boolean hashItem() {
		return true;
	}
	
	public HashMap<T,T2> getInventory() {
		return inventory;
	}
	
	public void putInventory(T t, T2 t2){
		this.inventory.put(t, t2);
	}

	public void setInentory(HashMap<T, T2> inventory) {
		this.inventory = inventory;
	}
	
}
