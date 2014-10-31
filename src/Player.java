
public abstract class Player {
	protected TicVal symbol;
	
	Player(TicVal symbol) {
		this.symbol = symbol; 
	}
	
	private void Insert() {
		
	}
	
	public abstract void getMove(TicTacToe tacToe);
}
