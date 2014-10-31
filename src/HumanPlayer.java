import java.util.Scanner;


public class HumanPlayer extends Player{
	HumanPlayer(TicVal symbol) {
		super(symbol);
	}
	public void getMove(TicTacToe tacToe) {
		System.out.println("Player " + symbol + " choose your square. (Format examples: A1, B2, C3..)");
	    Scanner s = new Scanner(System.in);
		
	    //Þegar við setjum inn í TicTacToe, getur verið að column sé out of bounds (checked)
  		//Eða að reitur sé fullur, ef fullur viljum við nýtt input
  		//Athugum fyrst hvort row sé valid:
	    String input = null; 
	    
	    while (true) {
		    input = s.nextLine();
			
			if (!IsInvalidRow(input.charAt(0))) {
				if (tacToe.Insert(RowVal.valueOf(String.valueOf(input.charAt(0))), (int)input.charAt(1), symbol)) {
					return; //Ef þetta var successful, viljum við hætta, annars höldum við áfram að biðja um input.
				}
			}
			
			else {
				System.out.println("Invalid row. Please try a different input.");
			}
			
			System.out.println("The square at " + input.charAt(0) + input.charAt(1) + " is taken. Try another.");
	    }
	}
	
	public boolean IsInvalidRow(char rowValue) {
		for (RowVal row : RowVal.values()) {
			if (row.toString().charAt(0) == rowValue) return false; //rowValue er í RowVal.. fínt.
		}
		
		return true; 
	}

}
