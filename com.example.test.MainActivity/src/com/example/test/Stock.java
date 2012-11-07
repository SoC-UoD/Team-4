package com.example.test;

/* Class represents a stock option... that is a investment in one stock symbol */ 

public class Stock {
	
	private String _symbol;
	private double _previousFridayClosePrice;
	private String _previousFridayDate;
	
	private double _currentPrice;
	private double _marketCap;
	private int _volume;
	private double _percentChange;
	
	private int _numberOfSharesOwned;
	
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
		return _previousFridayDate;
	}
	
	public void setPreviousFriDate(String newDate) 
	{
		_previousFridayDate = newDate;
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
}
