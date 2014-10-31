import java.util.HashMap;

public class TicTacToe {
	private final int ROWS = 3, COLUMNS = 3;
	private int count; //Number of squares filled 
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
    
	//Checks whether all squares are taken 
	public boolean BoardFull() {
		return count == (ROWS * COLUMNS);
	}
	
	public TicVal GetWinner() {
		return CheckDiagonalVictory();
	}
	/* Check whether someone wins by having a diagonal line. 
	 * Return null if no winner found. Otherwise the TicVal of the winner. */
	private TicVal CheckDiagonalVictory() {
		int diagonalVictoryAColumn = 0, diagonalVictoryBColumn = COLUMNS - 1;
		boolean victoryAPossible = true, victoryBPossible = true; 
		TicVal potentialVictorA = TicVal.U, potentialVictorB = TicVal.U;
		
		//Victory A is the diagonal line from A1 to .. the last row/last column.
		//Victory B is the diagonal line from the last row/1 to A/last column. 
		
		for (RowVal row : RowVal.values()) {
			TicVal[] values = board.get(row);
			//Case: If square has the value U (untaken), then victory is not possible for that row.
			if (victoryAPossible) {
				TicVal valueInSquare = values[diagonalVictoryAColumn];
				if (valueInSquare == TicVal.U) victoryAPossible = false;
				else {
					if (potentialVictorA == TicVal.U) potentialVictorA = valueInSquare;
					else {
						//Check whether this is the same guy as in the last square
						if (potentialVictorA != valueInSquare) victoryAPossible = false;
					}
					
					diagonalVictoryAColumn++;
				}
			}
			
			if (victoryBPossible) {
				TicVal valueInSquare = values[diagonalVictoryBColumn];
				if (valueInSquare == TicVal.U) victoryBPossible = false;
				else {
					if (potentialVictorB == TicVal.U) potentialVictorB = valueInSquare;
					else {
						//Check whether this is the same guy as in the last square
						if (potentialVictorB != valueInSquare) victoryBPossible = false;
					}
					
					diagonalVictoryBColumn--;
				}
			}
		}
		
		if (victoryAPossible) return potentialVictorA;
		else if (victoryBPossible) return potentialVictorB;
		else return null; 
	}
	
	private boolean SquareIsTaken(RowVal row, int col) {
		return (board.get(row)[col] != TicVal.U);
	}
	
	void DisplayBoard() {
		
	}
	//Attempts to mark a square on the board, returns false if it's taken.
	public boolean Insert(RowVal row, int col, TicVal symbol) {
		ColOutOfBoundsCheck(col - 1); 
		
		if (!SquareIsTaken(row, col - 1)) {
			TicVal[] values = board.get(row);
			values[col - 1] = symbol;
			count++; 
			return true; 
		}
		
		return false; 
	}
	
	private void ColOutOfBoundsCheck(int col) {
		if (col >= COLUMNS || col < 0) {
			throw new IndexOutOfBoundsException("Column out of bounds.");
		}
	}
}
