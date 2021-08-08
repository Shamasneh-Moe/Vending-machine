package vm;

/**
 * @author Muhammad
 *
 */

public enum Coin {

NOCOINE(0),TENCENTS(10),TWENTYCENTS(20),FIFTYCENTS(50),ONEDOLLAR(100);
	
	private int coinValue;
	Coin(int i) {
		this.coinValue = i;
	}
	public int getCoinValue(){
		return this.coinValue;
	}
	
}
