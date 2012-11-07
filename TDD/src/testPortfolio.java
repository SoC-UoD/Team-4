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
		bp.setPreviousFriClosePrice(415.26);
		bp.setNumberOfSharesOwned(101);
		
		mks = new Stock();
		mks.setSymbol("MKS.L");
		mks.setPreviousFriClosePrice(200.014);
		mks.setNumberOfSharesOwned(500);
	}

	@Test
	public void testAddAStock() 
	{
		testPortfolio.addStock(bp);
		assertEquals(1, testPortfolio.getNumberOfStockOptionsInPortfolio());
	}
	
	@Test 
	public void testAddTwoStocks()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		assertEquals(2, testPortfolio.getNumberOfStockOptionsInPortfolio());
	}
	
	@Test
	public void testPortfolioValuePrevFri()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		assertEquals(141948, testPortfolio.getPortfolioValuePrevFriday());
	}
	
	@Test
	public void testGetPortfolioOptions()
	{
		testPortfolio.addStock(bp);
		testPortfolio.addStock(mks);
		Stock testArray[] = new Stock[2];
		testArray[0] = bp;
		testArray[1] = mks;
		
		assertArrayEquals(testArray, testPortfolio.getPortfolioOptions());
	}
	
}
