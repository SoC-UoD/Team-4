package com.example.test;

public class Stock {
	private double _previousFridayClosePrice;
	private String _previousFridayDate;
	
	private double _currentPrice;
	private double _marketCap;
	private int _volume;
	private double _percentChange;
	
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
}
