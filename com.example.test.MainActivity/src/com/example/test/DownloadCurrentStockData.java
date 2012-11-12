package com.example.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadCurrentStockData {
	Portfolio thePortfolio;
	DownloaderCallBack notifyUi;
	
	protected void downloadCurrentFor(Portfolio currentPortfolio, DownloaderCallBack updateMethod)
	{
		thePortfolio = currentPortfolio;
		notifyUi = updateMethod;
		Stock portfolioOptions[] = thePortfolio.getPortfolioOptions();
		CurrentBackgroundDownloader cbdThread = new CurrentBackgroundDownloader();
		cbdThread.execute(portfolioOptions);
	}
	
	private class CurrentBackgroundDownloader extends AsyncTask<Stock, Integer, String> {
		private JSONObject downloadAStock(String url)
		{
			JSONObject results;
			
			// setup and start connection
			BasicHttpParams connectionSettings = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(connectionSettings, 3000);
			HttpClient httpclient = new DefaultHttpClient(connectionSettings);
			
			// send request for symbol
			HttpGet httpget = new HttpGet(url);
			HttpResponse response;
	
			// start processing response
			try 
			{
				response = httpclient.execute(httpget);
				if (response.getStatusLine().getStatusCode() != 200) 
				{
					results = null;
					return null;
				}
	
				HttpEntity entity = response.getEntity();
	
				if (entity != null) 
				{
					InputStream instream = entity.getContent();
					String result = UtilityFunctions.convertStreamToString(instream);
					
					// parse result string
					JSONObject json = new JSONObject(result.toString());
	
					JSONObject query = json.getJSONObject("query");
	
					if (query.getInt("count") == 0) 
					{
						results = null;
						return null;
					} 
					else 
					{
						results = query.getJSONObject("results");
					}
					instream.close();
					return results;
				}
			}
			catch (ClientProtocolException e) 
			{
				Log.d("x", "ClientProtocolException");
			} 
			catch (IOException e) 
			{
				Log.d("y", "IOException " + e.getMessage());
				results = null;
			} 
			catch (JSONException e) 
			{
				Log.d("z", "JSONException " + e.getMessage());
				e.getMessage();
				results = null;
			}
			return null;
			
		}
		
		@Override
		protected String doInBackground(Stock... theStock) 
		{
			JSONObject results = downloadAStock(thePortfolio.currentQuery());
			
			for (int i = 0; i < theStock.length; i++)
			{						
				theStock[i].resetCurrentErrors();
				if (results != null)
		         {
		        	 try
		        	 {
		        		 theStock[i].setCurrentPrice(Double.parseDouble(results.getJSONArray("quote").getJSONObject(i).getString("LastTradePriceOnly")));
		        	 }
		        	 catch (JSONException e) 
		     		{
		     			Log.d("a", "JSONException " + e.getMessage());
		     			e.getMessage();
		     			theStock[i].setErrorOnCurrentData();
		     		}
		         }
		         else
		         {
		        	 theStock[i].setErrorOnCurrentData();
		         }
			}
			
			return "success";
		}
	
		@Override
	     protected void onPreExecute() {
	         super.onPreExecute();    
	         notifyUi.downloading();
	     }
		
		@Override
	     protected void onPostExecute(String result) {
	         super.onPostExecute(result);    
	         notifyUi.update();
	     }
	}
}
