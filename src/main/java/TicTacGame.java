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
            return GetWinner().toString(); //Null if no winner
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
            board = new TicTacToe(); //Refresh board on refresh..
            lastPlayer = TicVal.U; 

            StringBuilder html = new StringBuilder();
            
            html.append("<!DOCTYPE html>");
	    html.append("<html lang=\"en\">");
            html.append("<head>");
   	    html.append("<meta charset=\"utf-8\">");
   	    html.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
   	    html.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
   	    html.append("<title>Tic Tac Toe</title>");
            html.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.");
            html.append("3.0/css/bootstrap.min.css\">");
    	    html.append("<style>");
     	    html.append("body {");
       	    html.append("width: 70%;");
       	    html.append("margin: 0 auto;");
            html.append("}");
     	    html.append("td {");
       	    html.append("text-align: center;");
      	    html.append("}");
            html.append("</style>");
            html.append("</head>");
            html.append("<body>");
            html.append("<p class=\"pull-left\" id=\"output\"></p>");
            html.append("<a href=\"#\" class=\"btn btn-primary pull-left\" id=\"newmatch\">New Match</a>");
            html.append("<table class=\"table table-bordered\">");
            html.append("<tr>");
            html.append("<td data-square=\"A1\"></td>");
            html.append("<td data-square=\"A2\"></td>");
            html.append("<td data-square=\"A3\"></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td data-square=\"B1\"></td>");
            html.append("<td data-square=\"B2\"></td>");
            html.append("<td data-square=\"B3\"></td>");
            html.append("</tr>");
            html.append("<tr>");
            html.append("<td data-square=\"C1\"></td>");
            html.append("<td data-square=\"C2\"></td>");
            html.append("<td data-square=\"C3\"></td>");
            html.append("</tr>");
            html.append("</table>");
            html.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.");
            html.append("min.js\"></script>");
            html.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap");
            html.append(".min.js\"></script>");

            html.append("<script>\n");
            html.append("$(document).ready(function() {\n");
            html.append("var currentPlayer = \"X\";\n");
            html.append("$(\"td\").on(\"click\", function() {\n");
            html.append("var square = $(this).data(\"square\");\n");
            html.append("var squareTD = $(this);\n");
            html.append("console.log(square);\n");
            html.append("$.ajax({\n");
            html.append("type: 'post',\n");
            html.append("url: '/InsertMove',\n");
            html.append("data: 'move=' + square,\n");
            html.append("statusCode: {\n");
            html.append("200: function (data) {\n");
            html.append("//insert successful\n");
            html.append("$(squareTD).html(currentPlayer);\n");
            html.append("if (currentPlayer === \"X\") {\n");
            html.append("currentPlayer = \"O\";\n");
            html.append("}\n");
            html.append("else {\n");
            html.append("currentPlayer = \"X\";\n");
            html.append("}\n");
            html.append("},\n");
            html.append("400: function(data) {\n");
            html.append("//Insert unsuccessful\n");
            html.append("}\n");
            html.append("}\n");
            html.append("}).done(function(winner) {\n");
            html.append("if (winner === \"X\") {\n");
            html.append("$(\"#output\").html(\"Congratulations player X, you win!\");\n");
            html.append("}\n");
            html.append("else if (winner === \"O\") {\n");
            html.append("$(\"#output\").html(\"Congratulations player O, you win!\");\n");
            html.append("}\n");
	    html.append("else if (winner === \"U\") {\n");
	    html.append("$(\"#output\").html(\"Draw\");\n");
	    html.append("}\n");
            html.append("}).fail(function() {\n");
            html.append("});\n");
            html.append("event.preventDefault();\n");
            html.append("});\n");

            html.append("$(\"#newmatch\").on(\"click\", function() {\n");
            html.append("$.each(\"td\", function(index, value) {\n");
            html.append("$(value).html(\"\");\n");
            html.append("});\n");
            html.append("});\n");

            html.append("});\n");
            html.append("</script>");
            html.append("</body>");
            html.append("</html>");
            return html.toString();
         }
      });

      post(new Route("/InsertMove") {
         @Override
         public Object handle(Request request, Response response) {
            String move = request.queryParams("move");
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
		  TicVal winner = GetWinner();
		  if(winner == null) {
		     return "U";
		  }
                  return GetWinner().toString(); //Null if no winner
               }
          
               else {
                  response.status(200);
                  return response;
               }
            }
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
