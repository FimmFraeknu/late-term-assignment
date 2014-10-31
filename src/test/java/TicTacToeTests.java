package test.java;

import static org.junit.Assert.*;
import main.java.RowVal;
import main.java.TicTacToe;
import main.java.TicVal;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class TicTacToeTests {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void ConstructorTest() {
		TicTacToe testTacToe = new TicTacToe();
		for ( RowVal row : RowVal.values() ) {
			TicVal[] cols = testTacToe.GetColumns(row);
			for ( int j = 0; j < cols.length; ++j ) {
				assertEquals(TicVal.U, cols[j]);
			
			}
		}
	}
	
	@Test
	public void BoardFullTest() {
		TicTacToe testTacToe = new TicTacToe();
		/*
		 * We fill up the board with O's.
		 */
		for(RowVal row : RowVal.values()) {
			TicVal[] cols = testTacToe.GetColumns(row);
			for(int j = 1; j <= cols.length; j++) {
				testTacToe.Insert(row, j, TicVal.O);
			}
		}
		
		assertTrue(testTacToe.BoardFull());
	}
	
	@Test
	public void InsertTest() {
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		assertEquals(TicVal.X, tacToe.GetColumns(RowVal.A)[0]);
	}
	
	@Test
	public void InsertIntoTakenSquare() {
		TicTacToe tacToe = new TicTacToe();
		
		//Fill a square
		tacToe.Insert(RowVal.A, 1,  TicVal.X);
		
		//Try to fill it with a different TicVal
		tacToe.Insert(RowVal.A, 1, TicVal.O);
		
		//Assure ourselves that the TicVal is still the original value. 
		assertEquals(TicVal.X, tacToe.GetColumns(RowVal.A)[0]);
	}
	
	@Test 
	public void ColumnOutOfBoundsTest()
	{
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Column out of bounds.");
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, -1, TicVal.X);
	}
	
	@Test
	public void GetDiagonalWinnerTest() {
		TicTacToe tacToe = new TicTacToe();
		
		//X er winner..
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.B, 2, TicVal.X);
		tacToe.Insert(RowVal.C, 3, TicVal.X);
		
		tacToe.Insert(RowVal.A, 2, TicVal.O);
		tacToe.Insert(RowVal.A, 3, TicVal.O);
		
		assertEquals(TicVal.X, tacToe.GetWinner());
	}
	
	@Test
	public void GetDiagonalLoserTest() {
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.B, 1, TicVal.X);
		
		assertEquals(null, tacToe.GetWinner());
	}
	
	@Test
	public void CheckDiagonalLoserTest() {
		TicTacToe tacToe = new TicTacToe();
		

		tacToe.Insert(RowVal.A, 1, TicVal.O);
		tacToe.Insert(RowVal.B, 2, TicVal.X);
		tacToe.Insert(RowVal.C, 3, TicVal.X);
		
		assertEquals(null, tacToe.GetWinner());
	}

	@Test 
	public void CheckHorizontalLoserTest() {
		TicTacToe tacToe = new TicTacToe();

		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.A, 2, TicVal.X);
		tacToe.Insert(RowVal.A, 3, TicVal.O);


		assertEquals(null, tacToe.GetWinner());
	}

	@Test 
	public void CheckVerticalLoserTest(){
		TicTacToe tacToe = new TicTacToe();

		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.B, 1, TicVal.X);
		tacToe.Insert(RowVal.C, 1, TicVal.O);


		assertEquals(null, tacToe.GetWinner());
	}
	
	
	@Test
	public void CheckVerticalLoser2Test(){
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.B, 1, TicVal.X);
		tacToe.Insert(RowVal.C, 1, TicVal.O);
		tacToe.Insert(RowVal.A, 3, TicVal.X);
		tacToe.Insert(RowVal.B, 3, TicVal.X);
		tacToe.Insert(RowVal.C, 3, TicVal.O);
		
		assertEquals(null, tacToe.GetWinner());
	}
	
	
	@Test
	public void CheckVerticalMiddleLine(){
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 2, TicVal.X);
		tacToe.Insert(RowVal.B, 2, TicVal.O);
		tacToe.Insert(RowVal.C, 2, TicVal.X);
		
		assertEquals(null, tacToe.GetWinner());
	}
	
	@Test
	public void CheckVerticalMiddleLineV2(){
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.B, 1, TicVal.X);
		tacToe.Insert(RowVal.A, 1, TicVal.O);
		tacToe.Insert(RowVal.B, 2, TicVal.X);
		tacToe.Insert(RowVal.A, 2, TicVal.O);
		tacToe.Insert(RowVal.B, 3, TicVal.X);
		
		assertEquals(TicVal.X, tacToe.GetWinner());
	}
	
	@Test
	public void GetHorizontalWinnerTest() {
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.A, 2, TicVal.X);
		tacToe.Insert(RowVal.A, 3, TicVal.X);
		
		tacToe.Insert(RowVal.B, 1, TicVal.O);
		tacToe.Insert(RowVal.B, 2, TicVal.O);
		tacToe.Insert(RowVal.C, 3, TicVal.O);
		
		
		assertEquals(TicVal.X, tacToe.GetWinner());
	}
	
	@Test
	public void GetVerticalWinnerTest() {
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		tacToe.Insert(RowVal.B, 1, TicVal.X);
		tacToe.Insert(RowVal.C, 1, TicVal.X);
		
		assertEquals(TicVal.X, tacToe.GetWinner());
	}
}
