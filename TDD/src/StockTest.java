import static org.junit.Assert.*;

import org.junit.*;
import com.example.test.Stock;

public class StockTest {
	
	Stock testStock;
	
	@Before
	public void setUp()
	{
		testStock = new Stock();
		testStock.setSymbol("BP.L");
		testStock.setNumberOfSharesOwned(125);
		testStock.setCurrentPrice(123.45);
		testStock.setPreviousFriDate(12, 06, 2015);
		testStock.setMarketCap(1239999999999999.45);
		testStock.setPreviousFriClosePrice(120.51);
		testStock.setVolume(123456789);
		testStock.setPercentChange(0.24);
	}
	
	@Test
	public void testHistoricalQuery()
	{
		assertEquals("http://query.yahooapis.com/v1/public/yql?q=select%20Close%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22BP.L%22%20and%20startDate%20%3D%20%222015-6-12%22%20and%20endDate%20%3D%20%222015-6-12%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=", testStock.historicalQuery());
	}
	
	
	@Test
	public void testNoErrorOnHistoricalData()
	{
		assertFalse(testStock.checkErrorOnHistoricalData());
	}
	
	@Test
	public void testErrorOnHistoricalData()
	{
		testStock.setErrorOnHistoricalData();
		assertTrue(testStock.checkErrorOnHistoricalData());
	}
	
	@Test
	public void testMultipleErrorOnHistoricalData()
	{
		testStock.setErrorOnHistoricalData();
		testStock.setErrorOnHistoricalData();
		assertTrue(testStock.checkErrorOnHistoricalData());
	}
	
	public void testNoErrorOnCurrentData()
	{
		assertFalse(testStock.checkErrorOnCurrentData());
	}
	
	@Test
	public void testErrorOnCurrentData()
	{
		testStock.setErrorOnCurrentData();
		assertTrue(testStock.checkErrorOnCurrentData());
	}
	
	@Test
	public void testMultipleErrorOnCurrentData()
	{
		testStock.setErrorOnCurrentData();
		testStock.setErrorOnCurrentData();
		assertTrue(testStock.checkErrorOnCurrentData());
	}
	
	@Test
	public void testCurrentPrice() 
	{
		assertEquals(123.45, testStock.getCurrentPrice(), 0.0001);
	}
	
	@Test
	public void testPreviousFridayDate() 
	{
		assertEquals("12/6/2015", testStock.getPreviousFriDate());
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
