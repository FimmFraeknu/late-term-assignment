package main.java;

import spark.*;
import static spark.Spark.*;
import spark.servlet.SparkApplication;

public class TicTacGame implements SparkApplication {
   private TicTacToe board;
   private TicVal lastPlayer;

   TicTacGame() {
	   board = new TicTacToe();
           lastPlayer = TicVal.U;
   }
   
   public boolean GameOver() {
	   return (board.BoardFull() || (board.GetWinner() != null));
   }
   
   public void GetMove(Player player) {
	   player.GetMove(board);
   }

   
   public Object InsertMove(String move, Response response) {
      SwitchLastPlayer();
      int col = -1;
      RowVal row = null;

      row = RowVal.valueOf(String.valueOf(move.charAt(0)));
      col = Character.getNumericValue(move.charAt(1));

      if (!board.Insert(row, col, lastPlayer)) {
         SwitchLastPlayer(); //Allow the same player to play again
         response.status(400);
         return response; //Bad request
      }

      else {
         if (GameOver()) {
            return GetWinner(); //Null if no winner
         }

         else {
            response.status(200);
            return response;
         }
      }
   }

   private void SwitchLastPlayer() {
      if (lastPlayer == TicVal.U) {
         lastPlayer = TicVal.X;
      }
      else if (lastPlayer == TicVal.X) {
         lastPlayer = TicVal.O;
      }
      else {
         lastPlayer = TicVal.X;
      }
   }

   public TicVal GetWinner() {
	   return board.GetWinner();
   }
   
   public void DisplayBoard() {
	   board.DisplayBoard();
   }
   
   public void init() {
      get(new Route("/") {
         @Override
         public Object handle(Request request, Response response) {
            StringBuilder html = new StringBuilder();
            
            html.append("<html lang=\"en\"><body>Hello World</body></html>");
            return html.toString();
         }
      });
   }

   public static void main(String[] args) {
      staticFileLocation("/public");
      SparkApplication tacGame = new TicTacGame();
      String port = System.getenv("PORT");
      if (port != null) {
         setPort(Integer.valueOf(port));
      }
      tacGame.init();
   }
  /* public static void main(String[] args) {
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
   } */
}
