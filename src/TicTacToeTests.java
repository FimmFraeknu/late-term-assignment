
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class TicTacToeTests {
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
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
		
		fail("Not yet implemented");
	}
	
	@Test
	public void InsertTest() {
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, 1, TicVal.X);
		assertEquals(tacToe.GetColumns(RowVal.A)[0], TicVal.X);
	}
	
	@Test
	public void InsertIntoTakenSquare() {
		TicTacToe tacToe = new TicTacToe();
		
		//Fill a square
		tacToe.Insert(RowVal.A, 1,  TicVal.X);
		
		//Try to fill it with a different TicVal
		tacToe.Insert(RowVal.A, 1, TicVal.O);
		
		//Assure ourselves that the TicVal is still the original value. 
		assertEquals(tacToe.GetColumns(RowVal.A)[0], TicVal.X);
	}
	
	@Test 
	public void ColumnOutOfBoundsTest()
	{
		thrown.expect(IndexOutOfBoundsException.class);
		thrown.expectMessage("Column out of bounds.");
		TicTacToe tacToe = new TicTacToe();
		
		tacToe.Insert(RowVal.A, -1, TicVal.X);
	}
}
