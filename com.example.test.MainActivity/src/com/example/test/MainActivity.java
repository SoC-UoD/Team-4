package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements DownloaderCallBack {
	
	TextView total;
	TextView status;
	TextView errors;
	String displayDate;
	Portfolio thePortfolio;
	int previousFridayDate[];
	StockArrayAdapter stockListView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        previousFridayDate = UtilityFunctions.previousFridayDate();
        
        total = (TextView)findViewById(R.id.total);
        status = (TextView)findViewById(R.id.status);
        errors = (TextView)findViewById(R.id.error_text);
        //total.setTextSize(50);
  
        thePortfolio = new Portfolio();
        Stock bp = new Stock();
        bp.setSymbol("BP.L");
        bp.setCompany("BP Amoco plc");
        bp.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        bp.setNumberOfSharesOwned(192);
        
        thePortfolio.addStock(bp);
        
        Stock expn = new Stock();
        expn.setSymbol("EXPN.L");
        expn.setCompany("Experian Ordinary");
        expn.setNumberOfSharesOwned(258);
        expn.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(expn);
        
        Stock hsba = new Stock();
        hsba.setSymbol("HSBA.L");
        hsba.setCompany("HSBC Holdings plc Ordinary");
        hsba.setNumberOfSharesOwned(343);
        hsba.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(hsba);
        
        Stock mks = new Stock();
        mks.setSymbol("MKS.L");
        mks.setCompany("Marks and Spencer Ordinary");
        mks.setNumberOfSharesOwned(485);
        mks.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(mks);
        
        Stock sn = new Stock();
        sn.setSymbol("SN.L");
        sn.setCompany("Smith & Nephew plc Ordinary");
        sn.setNumberOfSharesOwned(1219);
        sn.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(sn);
        
        DownloadHistoricalStockData historicalDownloader = new DownloadHistoricalStockData();
        historicalDownloader.downloadHistoricalFor(thePortfolio, this);
        
<<<<<<< HEAD
        DownloadCurrentStockData currentDownloader = new DownloadCurrentStockData();
        currentDownloader.downloadCurrentFor(thePortfolio, this);
=======
        stockListView = new StockArrayAdapter(this, thePortfolio.getPortfolioOptions());
        ListView displayList = (ListView)findViewById(R.id.stocksList);
        displayList.setAdapter(stockListView);
>>>>>>> 8b007cc23b1c14e4d107595f7e6a7bedca422ee8
    }
    
    // called by downloader when download starts
    public void downloading()
    {
    	status.setText("Loading data from web...");
    }
    
    // called by downloader when download ends
    public void update()
    {
<<<<<<< HEAD
    	Stock[] sets = thePortfolio.getPortfolioOptions();
    	String message = "";
    	
    	tv_view.setText("");
    	
    	for(int i = 0; i < sets.length; i++)
    	{
    			message += tv_view.getText() + "\n" + sets[i].getSymbol() + ": £ " +
				UtilityFunctions.formatCommas(
				UtilityFunctions.convertToPoundsRounded(sets[i].getValueForSet()));
    	}
    	
    	/*String message = "Total portfolio value: £ " + 
    					UtilityFunctions.formatCommas(
    					UtilityFunctions.convertToPoundsRounded(thePortfolio.getPortfolioValuePrevFriday()));
    	
    	message += " for " + previousFridayDate[0] + "/" + previousFridayDate[1] + "/" + previousFridayDate[2];*/
=======
    	status.setText("Updated: " + UtilityFunctions.currentDateAndTime());
    	
    	total.setText("  Total portfolio value: £ " + 
    					UtilityFunctions.formatCommas(
    					UtilityFunctions.convertToPoundsRounded(
    							thePortfolio.getPortfolioValuePrevFriday()))
    					
    				+ " for " + previousFridayDate[0] + "/" + previousFridayDate[1] + "/" + previousFridayDate[2]);
>>>>>>> 8b007cc23b1c14e4d107595f7e6a7bedca422ee8
    	
    	if (!thePortfolio.getErrors().equals(""))
    	{
    		 errors.setText("\nErrors were encountered retrieving data for:\n" + thePortfolio.getErrors());
    	}
    	
    	stockListView.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
        
}
