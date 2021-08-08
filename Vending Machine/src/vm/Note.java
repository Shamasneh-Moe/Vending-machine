package vm;

/**
 * @author Muhammad
 *
 */

public enum Note {

TWENTYDOLLARS(20),FIFTYDOLLARS(50) ;
	
	private int notesValue;
	
	Note(int i) {
		
		this.notesValue = i * 100 ;
	}
	public int getNotesValue() {
		return notesValue;
	}
	
}
