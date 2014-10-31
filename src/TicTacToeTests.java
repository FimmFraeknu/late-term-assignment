
import static org.junit.Assert.*;
import org.junit.*;

public class TicTacToeTests {

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

}
