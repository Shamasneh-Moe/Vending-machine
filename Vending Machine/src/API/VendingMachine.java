package API;

/**
 * @author Muhammad
 *
 */

import java.util.List;
import java.util.Optional;
import vm.Coin;
import vm.Note;
import vm.Slot;
import vm.Item;
public interface VendingMachine {
	public int selectItemGetPrice(Item item) throws Exception;
	public Optional<Slot> insertedMoney(List<Coin> coins ,List<Note> notes) throws Exception, Exception;
	public Slot getItemsAndChange(int insertedCoins,int incertedNotes) throws Exception;
	
}