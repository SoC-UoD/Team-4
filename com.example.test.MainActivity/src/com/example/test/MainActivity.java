package com.example.test;

//comment

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements DownloaderCallBack {
	
	TextView tv_view;
	String displayDate;
	Portfolio thePortfolio;
	int previousFridayDate[];
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        previousFridayDate = UtilityFunctions.previousFridayDate();
        
        tv_view = (TextView)findViewById(R.id.tv_view);
        //tv_view.setTextSize(50);
  
        thePortfolio = new Portfolio();
        Stock bp = new Stock();
        bp.setSymbol("BP.L");
        bp.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        bp.setNumberOfSharesOwned(192);
        
        thePortfolio.addStock(bp);
        
        Stock expn = new Stock();
        expn.setSymbol("EXPN.L");
        expn.setNumberOfSharesOwned(258);
        expn.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(expn);
        
        Stock hsba = new Stock();
        hsba.setSymbol("HSBA.L");
        hsba.setNumberOfSharesOwned(343);
        hsba.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(hsba);
        
        Stock mks = new Stock();
        mks.setSymbol("MKS.L");
        mks.setNumberOfSharesOwned(485);
        mks.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(mks);
        
        Stock sn = new Stock();
        sn.setSymbol("SN.L");
        sn.setNumberOfSharesOwned(1219);
        sn.setPreviousFriDate(previousFridayDate[0], previousFridayDate[1], previousFridayDate[2]);
        thePortfolio.addStock(sn);
        
        DownloadHistoricalStockData historicalDownloader = new DownloadHistoricalStockData();
        historicalDownloader.downloadHistoricalFor(thePortfolio, this);
    }
    
    // called by downloader when download starts
    public void downloading()
    {
    	tv_view.setText("Loading data from web...");
    }
    
    // called by downloader when download ends
    public void update()
    {
    	String message = "Total portfolio value: £ " + 
    					UtilityFunctions.formatCommas(
    					UtilityFunctions.convertToPoundsRounded(thePortfolio.getPortfolioValuePrevFriday()));
    	
    	message += " for " + previousFridayDate[0] + "/" + previousFridayDate[1] + "/" + previousFridayDate[2];
    	
    	if (!thePortfolio.getErrors().equals(""))
    	{
    		message += "\nErrors were encountered retrieving the following symbols:\n" + thePortfolio.getErrors();
    	}
    	tv_view.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
        
}
