import static org.junit.Assert.*;

import org.junit.*;

import com.example.test.*;

public class testPortfolio {

	private Portfolio testPortfolio;
	private Stock bp;
	private Stock mks;
	
	@Before
	public void setUp() throws Exception 
	{
		testPortfolio = new Portfolio();
		
		bp = new Stock();
		bp.setSymbol("BP.L");
		bp.setCompany("BP Amoco plc");
		bp.setPreviousFriClosePrice(415.26);
		bp.setNumberOfSharesOwned(101);
		
		mks = new Stock();
		mks.setSymbol("MKS.L");
		mks.setCompany("Marks and Spencer ordinary");
		mks.setPreviousFriClosePrice(200.014);
		mks.setNumberOfSharesOwned(500);
	}

	@Test
	public void testAddAStock() 
	{
		testPortfolio.addStock(bp);
		Stock testArray[] = new Stock[1];
		testArray[0] = bp;
		
		assertArrayEquals(testArray, testPortfolio.getPortfolioOptions());
	}
	
	@Test 
	public void testAddTwoStocks()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		Stock testArray[] = new Stock[2];
		testArray[0] = bp;
		testArray[1] = mks;
		
		assertArrayEquals(testArray, testPortfolio.getPortfolioOptions());
	}
	
	@Test
	public void testPortfolioValuePrevFri()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		assertEquals(141948, testPortfolio.getPortfolioValuePrevFriday());
	}
	
	@Test
	public void testGetErrors()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		assertEquals("", testPortfolio.getErrors());
	}
	
	@Test
	public void testCurrentQuery()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		assertEquals("http://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22BP.L%22%2C%22MKS.L%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=", testPortfolio.currentQuery());
	}
	
	@Test
	public void testErrorInCurrentData() 
	{
		testPortfolio.addStock(bp);
		bp.setCurrentPrice(50001);
		assertEquals("Errors retrieving current data for:\nBP Amoco plc\n", testPortfolio.getErrors());
	}
	
	@Test
	public void testErrorInHistoricalData() 
	{
		testPortfolio.addStock(bp);
		bp.setCurrentPrice(30);
		bp.setNumberOfSharesOwned(5001);
		assertEquals("Errors retrieving historical data for: \nBP Amoco plc\n\n\nErrors retrieving current data for:\nBP Amoco plc\n", testPortfolio.getErrors());
	}
}
