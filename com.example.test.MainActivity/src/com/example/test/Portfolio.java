package com.example.test;

import java.util.ArrayList;

public class Portfolio {
	
	private ArrayList<Stock> _optionsPortfolio;
	private String _historicalErrors;
	private String _currentErrors;
	
	public Portfolio()
	{
		_historicalErrors = "";
		_currentErrors = "";
		_optionsPortfolio = new ArrayList<Stock>();
	}
	
	
	public void addStock(Stock newStock)
	{
		_optionsPortfolio.add(newStock);
	}

	public String getErrors ()
	{
		_currentErrors = "";
		
		for (int i = 0; i < _optionsPortfolio.size(); i++)
		{
			if (_optionsPortfolio.get(i).checkErrorOnCurrentData())
			{
				_currentErrors += _optionsPortfolio.get(i).getCompany() + "\n";
			}
		}
		
		_historicalErrors = "";
		for (int i = 0; i < _optionsPortfolio.size(); i++)
		{
			if (_optionsPortfolio.get(i).checkErrorOnHistoricalData())
			{
				_historicalErrors += _optionsPortfolio.get(i).getCompany() + "\n";
			}
		}
		
		String errors = "";
		if (!_historicalErrors.equals(""))
		{
			errors += "Errors retrieving historical data for: \n" + _historicalErrors + "\n\n";
		}
		
		if (!_currentErrors.equals(""))
		{
			errors += "Errors retrieving current data for:\n" + _currentErrors;
		}
		
		return  errors;
	}
	
	public long getPortfolioValuePrevFriday() {
		double totalPortfolioValuePrevFri = 0;
		
		for (int i = 0; i < _optionsPortfolio.size(); i++)
		{
			if (!_optionsPortfolio.get(i).checkErrorOnHistoricalData())
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
		String query = "http://query.yahooapis.com/v1/public/yql?q=select%20LastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(";
		for(int i = 0; i < _optionsPortfolio.size(); i++)
		{
			query += "%22" + _optionsPortfolio.get(i).getSymbol() + "%22";
			if(i != _optionsPortfolio.size() -1)
			{
				query += "%2C";
			}
		}
		query +=")&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
		return query;
	}
}
