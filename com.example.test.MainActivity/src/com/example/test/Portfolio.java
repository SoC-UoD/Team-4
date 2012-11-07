package com.example.test;

import java.util.ArrayList;

public class Portfolio {
	
	private int _numberOfStockOptions;
	private double _totalPortfolioValuePrevFri;
	private ArrayList<Stock> _optionsPortfolio;
	
	public Portfolio()
	{
		_totalPortfolioValuePrevFri = 0;
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
		_totalPortfolioValuePrevFri += newStock.getValueForPrevFriSet();
		_optionsPortfolio.add(newStock);
	}

	public long getPortfolioValuePrevFriday() {
		return Math.round(_totalPortfolioValuePrevFri);
	}
	
	public Stock[] getPortfolioOptions()
	{
		return _optionsPortfolio.toArray(new Stock[1]);
	}
}
