package Money;

/**
 * @author Muhammad
 *
 */

import java.util.List;
import vm.Coin;
import vm.Note;

public class CashManager {

	public List<Note> convertToNotes(List<Note> returnNotesArray, int changedValue){

		int reminder=0;
		if(changedValue>=Note.FIFTYDOLLARS.getNotesValue()){
			reminder = changedValue/Note.FIFTYDOLLARS.getNotesValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnNotesArray.add(Note.FIFTYDOLLARS);
			}
			}
			int value  =  changedValue-(reminder*Note.FIFTYDOLLARS.getNotesValue());
			if(value!=0){
				convertToNotes(returnNotesArray, value);
			}			
			
			
		}else if(changedValue>=Note.TWENTYDOLLARS.getNotesValue()){
			reminder = changedValue/Note.TWENTYDOLLARS.getNotesValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnNotesArray.add(Note.TWENTYDOLLARS);
			}
			}
			int value  =  changedValue-(reminder*Note.TWENTYDOLLARS.getNotesValue());
			if(value!=0){
				convertToNotes(returnNotesArray, value);
			}
		}
			
			
		return returnNotesArray;
			
			
		}
	public List<Coin> convertToCoin(List<Coin> returnCoinsArray, int changedValue){
		int reminder=0;
		if(changedValue>=Coin.TENCENTS.getCoinValue()){
			reminder = changedValue/Coin.TENCENTS.getCoinValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnCoinsArray.add(Coin.TENCENTS);
			}
			}
			int value  =  changedValue-(reminder*Coin.TENCENTS.getCoinValue());
			if(value!=0){
				convertToCoin(returnCoinsArray, value);
			}

		}else if(changedValue>=Coin.TWENTYCENTS.getCoinValue()){
			reminder = changedValue/Coin.TWENTYCENTS.getCoinValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnCoinsArray.add(Coin.TWENTYCENTS);
			}
			}
			int value  =  changedValue-(reminder*Coin.TWENTYCENTS.getCoinValue());
			if(value!=0){
				convertToCoin(returnCoinsArray, value);			}

			
			
		}else if(changedValue>=Coin.FIFTYCENTS.getCoinValue()){
			reminder = changedValue/Coin.FIFTYCENTS.getCoinValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnCoinsArray.add(Coin.FIFTYCENTS);
			}
			}
			int value  =  changedValue-(reminder*Coin.FIFTYCENTS.getCoinValue());
			if(value!=0){
				convertToCoin(returnCoinsArray, value);			}

			
		}else if(changedValue>=Coin.ONEDOLLAR.getCoinValue()){
			reminder = changedValue/Coin.ONEDOLLAR.getCoinValue();
			if(reminder>0){
			for(int i=0; i<=reminder-1;i++){
				returnCoinsArray.add(Coin.ONEDOLLAR);
			}
			}
			int value  =  changedValue-(reminder*Coin.ONEDOLLAR.getCoinValue());
			if(value!=0){
				convertToCoin(returnCoinsArray, value);			}
		
		}
		
		return returnCoinsArray;

	}
}
