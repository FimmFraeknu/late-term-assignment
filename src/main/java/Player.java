package main.java;

public abstract class Player {
	protected TicVal symbol;
	
	Player(TicVal symbol) {
		this.symbol = symbol; 
	}
	
	public abstract void getMove(TicTacToe tacToe);
}
