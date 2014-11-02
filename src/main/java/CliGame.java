package main.java;

public class CliGame {
   private TicTacToe board;
   
   CliGame() {
	   board = new TicTacToe();
   }
   
   public boolean GameOver() {
	   return (board.BoardFull() || (board.GetWinner() != null));
   }
   
   public void GetMove(Player player) {
	   player.GetMove(board);
   }
   
   public TicVal GetWinner() {
	   return board.GetWinner();
   }
   
   public void DisplayBoard() {
	   board.DisplayBoard();
   }
   
   public static void main(String[] args) {
	   TicTacGame game = new TicTacGame();
	   HumanPlayer playerX = new HumanPlayer(TicVal.X), playerO = new HumanPlayer(TicVal.O);
	   System.out.println("---====Welcome to the thunderdome.====---");
	   System.out.println("Here we play TicTacToe.");
	   System.out.println("Only rule is: Play hard, or go home.");
	   
	   System.out.println("This will be your board this evening:");
	   game.DisplayBoard();
	   
	   while (!game.GameOver()) {
		   game.GetMove(playerX);
		   game.DisplayBoard();
		   if (game.GameOver()) break;
		   game.GetMove(playerO);
		   game.DisplayBoard();
		   if (game.GameOver()) break;
	   }
	   
	   // Check why we have GameOver, draw or winner.
	   TicVal winner = game.GetWinner();
	   if (winner == TicVal.X) {
		   System.out.println("Congratulations PlayerX, you win!");
	   }
	   
	   else if (winner == TicVal.O) {
		   System.out.println("Congratulations PlayerO, you win!");
	   }
	   
	   else {
		   System.out.println("Congratulations players, you both lose!");
	   }
   }
}
