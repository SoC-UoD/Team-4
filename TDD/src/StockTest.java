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
		testStock.setPreviousFriClosePrice(120.51);
	}
	
	@Test
	public void testCase1()
	{
		testStock.setCurrentPrice(-1);
		testStock.setNumberOfSharesOwned(0);
		assertTrue(testStock.checkErrorOnCurrentData());
	}
	
	@Test
	public void testCase2()
	{
		testStock.setCurrentPrice(50001);
		testStock.setNumberOfSharesOwned(0);
		assertTrue(testStock.checkErrorOnCurrentData());
	}
	
	@Test
	public void testCase3()
	{
		testStock.setCurrentPrice(1);
		testStock.setNumberOfSharesOwned(-1);
		assertTrue(testStock.checkErrorOnCurrentData());
		assertTrue(testStock.checkErrorOnHistoricalData());
	}
	
	@Test
	public void testCase4()
	{
		testStock.setCurrentPrice(1);
		testStock.setNumberOfSharesOwned(5001);
		assertTrue(testStock.checkErrorOnCurrentData());
		assertTrue(testStock.checkErrorOnHistoricalData());
	}
	
	@Test
	public void testCase5()
	{
		testStock.setCurrentPrice(0);
		testStock.setNumberOfSharesOwned(0);
		assertEquals(0, testStock.getValueForSet());
	}
	
	@Test
	public void testCase6()
	{
		testStock.setCurrentPrice(1);
		testStock.setNumberOfSharesOwned(1);
		assertEquals(1, testStock.getValueForSet());
	}
	
	@Test
	public void testCase7()
	{
		testStock.setCurrentPrice(49999);
		testStock.setNumberOfSharesOwned(4999);
		assertEquals(249945001, testStock.getValueForSet());
	}
	
	@Test
	public void testCase8()
	{
		testStock.setCurrentPrice(50000);
		testStock.setNumberOfSharesOwned(5000);
		assertEquals(250000000, testStock.getValueForSet());
	}
	
	@Test
	public void testCase9()
	{
		testStock.setCurrentPrice(49999.98);
		testStock.setNumberOfSharesOwned(5000);
		assertEquals(249999900.00, testStock.getValueForSet(), 1);
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
	public void testPreviousFridayClosePrice() 
	{
		assertEquals(120.51, testStock.getPreviousFriClosePrice(), 0.0001);
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
	
	@Test
	public void testCompanyName()
	{
		testStock.setCompany("ACME Stocks");
		assertEquals("ACME Stocks", testStock.getCompany());
	}
	
	@Test
	public void testResetDataErrors()
	{
		testStock.setErrorOnCurrentData();
		testStock.setErrorOnHistoricalData();
		
		testStock.resetCurrentErrors();
		testStock.resetHistoricalErrors();
		
		assertFalse(testStock.checkErrorOnCurrentData());
		assertFalse(testStock.checkErrorOnHistoricalData());
	}
	
	
}
