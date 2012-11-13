import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.test.Portfolio;
import com.example.test.Stock;


public class TestPortfolioErrorCases {

	private Portfolio testPortfolio;
	private Stock bp;
	
	@Before
	public void setUp() throws Exception 
	{
		testPortfolio = new Portfolio();
		
		bp = new Stock();
		bp.setSymbol("BP.L");
		testPortfolio.addStock(bp);
	}
	
	@Test
	public void testErrorInCurrentData() 
	{
		bp.setCurrentPrice(50001);
		assertEquals("Errors retrieving current data for:\nBP.L", testPortfolio.getErrors());
	}
	
	@Test
	public void testErrorInHistoricalData() 
	{
		bp.setCurrentPrice(30);
		bp.setNumberOfSharesOwned(5001);
		assertEquals("Errors retrieving current data for:\nBP.L\n\nErrors retrieving historical data for: \nBP.L", testPortfolio.getErrors());
	}

}
