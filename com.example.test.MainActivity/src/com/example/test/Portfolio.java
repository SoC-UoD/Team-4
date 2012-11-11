package com.example.test;

import java.util.ArrayList;

public class Portfolio {
	
	private int _numberOfStockOptions;
	private ArrayList<Stock> _optionsPortfolio;
	private String _errors;
	
	public Portfolio()
	{
		_errors = "";
		_numberOfStockOptions = 0;
		_optionsPortfolio = new ArrayList<Stock>();
	}
	
	public int getNumberOfStockOptionsInPortfolio()
	{
		return _numberOfStockOptions;
	}
	
	public void addStock(Stock newStock)
	{
		_numberOfStockOptions++;
		_optionsPortfolio.add(newStock);
	}

	public String getErrors ()
	{
		return _errors;
	}
	
	public long getPortfolioValuePrevFriday() {
		double totalPortfolioValuePrevFri = 0;
		for (int i = 0; i < _optionsPortfolio.size(); i++)
		{
			if (_optionsPortfolio.get(i).checkErrorOnHistoricalData())
			{
				_errors += _optionsPortfolio.get(i).getSymbol();
			}
			else
			{
				totalPortfolioValuePrevFri += _optionsPortfolio.get(i).getValueForPrevFriSet();
			}
		}
		return Math.round(totalPortfolioValuePrevFri);
	}
			
	public Stock[] getPortfolioOptions()
	{
		return _optionsPortfolio.toArray(new Stock[1]);
	}
	
	public String currentQuery()
	{
		return "http://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22BP.L%22%2C%22EXPN.L%22%2C%22HSBA.L%22%2C%22MKS.L%22%2C%22SN.L%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
	}
}
