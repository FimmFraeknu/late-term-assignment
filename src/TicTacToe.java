import java.util.HashMap;


public class TicTacToe {
	private final int ROWS = 3;
	HashMap<RowVal, TicVal[]> board;
	
	TicTacToe() {
		board = new HashMap<RowVal, TicVal[]>(ROWS);
		
		for (RowVal row : RowVal.values()) {
			board.put(row, new TicVal[3]);
		}
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
