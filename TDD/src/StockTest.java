import static org.junit.Assert.*;

import org.junit.*;
import com.example.test.Stock;

public class StockTest {
	
	Stock testStock;
	
	@Before
	public void setUp()
	{
		testStock = new Stock();
		testStock.setNumberOfSharesOwned(125);
		testStock.setCurrentPrice(123.45);
		testStock.setPreviousFriDate("12/06/2015");
		testStock.setMarketCap(1239999999999999.45);
		testStock.setPreviousFriClosePrice(120.51);
		testStock.setVolume(123456789);
		testStock.setPercentChange(0.24);
	}
	
	@Test
	public void testCurrentPrice() 
	{
		assertEquals(123.45, testStock.getCurrentPrice(), 0.0001);
	}
	
	@Test
	public void testPreviousFridayDate() 
	{
		assertEquals("12/06/2015", testStock.getPreviousFriDate());
	}
	
	@Test
	public void testMarketCap() 
	{
		assertEquals(1239999999999999.45, testStock.getMarketCap(), 0.0001);
	}
	
	@Test
	public void testPreviousFridayClosePrice() 
	{
		assertEquals(120.51, testStock.getPreviousFriClosePrice(), 0.0001);
	}
	
	@Test
	public void testVolume() 
	{
		assertEquals(123456789, testStock.getVolume());
	}
	
	@Test
	public void testPercentChange() 
	{
		assertEquals(0.24, testStock.getPercentChange(), 0.00001);
	}
	
	@Test
	public void testNumberOfSharesOwned() 
	{
		assertEquals(125, testStock.getNumberOfSharesOwned());
	}

	@Test
	public void testValueForSet()
	{
		assertEquals(15431, testStock.getValueForSet());
	}
	
	@Test
	public void testValueForPrevFriSet()
	{
		assertEquals(15064.0, testStock.getValueForPrevFriSet(), 0.000001);
	}
}
