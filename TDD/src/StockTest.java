import static org.junit.Assert.*;

import org.junit.*;
import com.example.test.Stock;

/*
 * 

	double _percentChange;
 */

public class StockTest {
	
	Stock testStock;
	
	@Before
	public void setUp()
	{
		testStock = new Stock();
	}
	
	@Test
	public void testCurrentPrice() {
		testStock.setCurrentPrice(123.45);
		assertEquals(123.45, testStock.getCurrentPrice(), 0.0001);
	}
	
	@Test
	public void testPreviousFridayDate() {
		testStock.setPreviousFriDate("12/06/2015");
		assertEquals("12/06/2015", testStock.getPreviousFriDate());
	}
	
	@Test
	public void testMarketCap() {
		testStock.setMarketCap(1239999999999999.45);
		assertEquals(1239999999999999.45, testStock.getMarketCap(), 0.0001);
	}
	
	@Test
	public void testPreviousFridayClosePrice() {
		testStock.setPreviousFriClosePrice(123.45);
		assertEquals(123.45, testStock.getPreviousFriClosePrice(), 0.0001);
	}
	
	@Test
	public void testVolume() {
		testStock.setVolume(123456789);
		assertEquals(123456789, testStock.getVolume());
	}
	
	@Test
	public void testPercentChange() {
		testStock.setPercentChange(0.24);
		assertEquals(0.24, testStock.getPercentChange(), 0.00001);
	}

}
