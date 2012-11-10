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
				_errors += " " + _optionsPortfolio.get(i).getSymbol();
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
}
