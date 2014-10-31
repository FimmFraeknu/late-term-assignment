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
		TicVal value = CheckDiagonalVictory();
		if (value != null) return value;
		value = CheckHorizontalVictory();
		if (value != null) return value;
		return CheckVerticalVictory(); 
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
	
	private TicVal CheckHorizontalVictory() {
		TicVal currentPotentialVictor = TicVal.U;
		
		for (RowVal row : RowVal.values()) {
			
			for (int col = 0; col < COLUMNS; col++) {
				TicVal valueInSquare = board.get(row)[col];
				
				if (valueInSquare == TicVal.U) continue; 
				else {
					if (currentPotentialVictor == TicVal.U) currentPotentialVictor = valueInSquare;
					else {
						if ((col == COLUMNS - 1) && valueInSquare == currentPotentialVictor) return currentPotentialVictor;
						else if (valueInSquare != currentPotentialVictor) continue; 
					}
				}
			}
		}
		
		return null; 
	}
	
	private TicVal CheckVerticalVictory() {
		TicVal currentPotentialVictor = TicVal.U;
		
		for (int col = 0; col < COLUMNS; col++) {
			for (RowVal row : RowVal.values()) {
				TicVal valueInSquare = board.get(row)[col];
				
				if (valueInSquare == TicVal.U) break; 
				else {
					if (currentPotentialVictor == TicVal.U) currentPotentialVictor = valueInSquare;
					else {
						if ((row == RowVal.values()[RowVal.values().length - 1]) && valueInSquare == currentPotentialVictor) return currentPotentialVictor;
						else if (valueInSquare != currentPotentialVictor) break; 
					}
				}
			}
		}
		
		return null; 
	}
	
	private boolean SquareIsTaken(RowVal row, int col) {
		return (board.get(row)[col] != TicVal.U);
	}
	
	void DisplayBoard() {
		
		/*
		 * Print header.
		 */
		System.out.print("    ");
		for(int i = 0; i < COLUMNS; i++) {
			System.out.print("  " + (i + 1) + "   ");
		}System.out.println();
		
		/*
		 * Print values for each cell.
		 */
		for(RowVal row : RowVal.values()) {
			System.out.print(row + "  ");
			TicVal[] cols = this.GetColumns(row);
			for(int j = 0; j < cols.length; j++) {
				System.out.print("|__");
				if(cols[j] != TicVal.U)
					System.out.print(cols[j]);
				else
					System.out.print("_");
				System.out.print("__");
			} System.out.println("|");
		}
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
