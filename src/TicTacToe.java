import java.util.HashMap;
import java.util.LinkedList;


public class TicTacToe {
	HashMap<RowVal, LinkedList<TicVal>> board;
	
	TicTacToe() {
		board = new HashMap<RowVal, LinkedList<TicVal>>(9); //9 reitir
	}
    //Athugar hvort allir retir séu teknir
	public boolean BoardFull() {
		return false;
	}
	//Allir reitir teknir/leikur unninn
	public boolean GameOver() {
		return false;
	}
	void DisplayBoard() {
		
	}
	//Reynir að merkja reit fyrir þennan player.
	boolean ChooseBox(int Player, RowVal row, int col) { 
		return false; //int Player verður væntanlega klasi, setti int svo compile-ist
	}
}
