package com.example.test;

/* Class represents a stock option... that is a investment in one stock symbol */ 

public class Stock {
	
	private String _symbol;
	private double _previousFridayClosePrice;
	
	private int _prevFriDay;
	private int _prevFriMonth;
	private int _prevFriYear;
	
	private double _currentPrice;
	private double _marketCap;
	private int _volume;
	private double _percentChange;
	private int _numberOfSharesOwned;
	
	private boolean _errorOnCurrentData;
	private boolean _errorOnHistoricalData;
	
	public Stock()
	{
		_errorOnCurrentData = false;
		_errorOnHistoricalData = false;
	}
	
	public void setErrorOnCurrentData()
	{
		_errorOnCurrentData = true;
	}
	
	public boolean checkErrorOnCurrentData()
	{
		return _errorOnCurrentData;
	}
	
	public void setErrorOnHistoricalData()
	{
		_errorOnHistoricalData = true;
	}
	
	public boolean checkErrorOnHistoricalData()
	{
		return _errorOnHistoricalData;
	}
	
	public String getSymbol()
	{
		return _symbol;
	}
	
	public void setSymbol(String newSymbol)
	{
		_symbol = newSymbol;
	}
	
	public double getPreviousFriClosePrice()
	{
		return _previousFridayClosePrice;
	}
	
	public void setPreviousFriClosePrice(double newPrice) 
	{
		_previousFridayClosePrice = newPrice;
	}
	
	public double getCurrentPrice()
	{
		return _currentPrice;
	}
	
	public void setCurrentPrice(double newPrice) 
	{
		_currentPrice = newPrice;
	}
	
	public String getPreviousFriDate()
	{
		return _prevFriDay + "/" + _prevFriMonth + "/" + _prevFriYear;
	}
	
	public void setPreviousFriDate(int day, int month, int year) 
	{
		_prevFriDay = day;
		_prevFriMonth = month;
		_prevFriYear = year; 
	}
	
	public void setMarketCap(double newCap)
	{
		_marketCap = newCap;
	}
	
	public double getMarketCap()
	{
		return _marketCap;
	}
	
	public void setVolume(int newVolume)
	{
		_volume = newVolume;
	}

	public int getVolume()
	{
		return _volume;
	}
	
	public void setPercentChange(double newPercent)
	{
		_percentChange = newPercent;
	}
	
	public double getPercentChange()
	{
		return _percentChange;
	}
	
	public void setNumberOfSharesOwned(int newNumber)
	{
		_numberOfSharesOwned = newNumber;
	}
	
	public int getNumberOfSharesOwned()
	{
		return _numberOfSharesOwned;
	}
	
	public long getValueForSet()
	{
		return Math.round(_numberOfSharesOwned * _currentPrice);
	}
	
	public long getValueForPrevFriSet()
	{
		return Math.round(_numberOfSharesOwned * _previousFridayClosePrice);
	}
	
	public String historicalQuery()
	{		
		String queryDate = _prevFriYear + "-" + _prevFriMonth + "-" + _prevFriDay;
		return "http://query.yahooapis.com/v1/public/yql?q=select%20Close%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"
				+ this.getSymbol()
				+ "%22%20and%20startDate%20%3D%20%22"
				+ queryDate
				+ "%22%20and%20endDate%20%3D%20%22"
				+ queryDate
				+ "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
	}
}
