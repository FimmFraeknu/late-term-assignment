package main.java;
import java.util.Scanner;


public class HumanPlayer extends Player{
	HumanPlayer(TicVal symbol) {
		super(symbol);
	}
	public void getMove(TicTacToe tacToe) {
		System.out.println("Player " + symbol + " choose your square. (Format examples: A1, B2, C3..)");
	    @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
	    //�egar vi� setjum inn � TicTacToe, getur veri� a� column s� out of bounds (checked)
  		//E�a a� reitur s� fullur, ef fullur viljum vi� n�tt input
  		//Athugum fyrst hvort row s� valid:
	    String input = null; 
	    
	    while (true) {
		    input = s.nextLine();
		    int col = -1;
		    RowVal row = null;
		    
		    try {
		    	row = RowVal.valueOf(String.valueOf(input.charAt(0)));
		    }
		    
		    catch (Exception ex) {
		    	System.out.println("Invalid row. Please try again:");
		    	continue;
		    }
			
			try {
				col = Character.getNumericValue(input.charAt(1));
			}
			
			catch (Exception ex) {
				System.out.println("Invalid column. Please try again:");
				continue;
			}
			
			
			try {
				
				if (!IsInvalidRow(input.charAt(0))) {
					if (tacToe.Insert(row, col, symbol)) {
						return; //Ef �etta var successful, viljum vi� h�tta, annars h�ldum vi� �fram a� bi�ja um input.
					}
					
					else {
						System.out.println("The square at " + input.charAt(0) + input.charAt(1) + " is taken. Try another.");
					}
				}
				
				else {
					System.out.println("Invalid row. Please try a different input.");
				}
			}
			
			catch (IndexOutOfBoundsException ex) {
				System.out.println(ex.getMessage() + " Please try again:");
			}
	    }
	}
	
	private boolean IsInvalidRow(char rowValue) {
		for (RowVal row : RowVal.values()) {
			if (row.toString().charAt(0) == rowValue) return false; //rowValue er � RowVal.. f�nt.
		}
		
		return true; 
	}

}
