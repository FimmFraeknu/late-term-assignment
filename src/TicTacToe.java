import java.util.HashMap;

public class TicTacToe {
	private final int ROWS = 3, COLUMNS = 3;
	HashMap<RowVal, TicVal[]> board;
	
	TicTacToe() {
		board = new HashMap<RowVal, TicVal[]>(ROWS);
		
		for (RowVal row : RowVal.values()) {
			//Initialize TicVal columns
			TicVal[] columns = new TicVal[COLUMNS];
			for (int i = 0; i < COLUMNS; i++) {
				columns[i] = TicVal.U;
			}
			
			board.put(row, columns);
		}
	}
	
	public TicVal[] GetColumns(RowVal row) {
		return board.get(row);
	}
	
	public int GetNumberOfRows() {
		return ROWS;
	}
	
	public int GetNumberOfColumns() {
		return COLUMNS;
	}
    //Athugar hvort allir retir s�u teknir
	public boolean BoardFull() {
		return false;
	}
	//Allir reitir teknir/leikur unninn
	public boolean GameOver() {
		return false;
	}
	
	private boolean SquareIsTaken() {
		return false;
	}
	
	void DisplayBoard() {
		
	}
	//Attempts to mark a square on the board, returns false if it's taken.
	public boolean Insert(RowVal row, int col, TicVal symbol) {
		return false; 
	}
}
