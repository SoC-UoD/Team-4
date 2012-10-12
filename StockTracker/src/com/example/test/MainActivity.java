package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

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
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv_view;
	String displayDate;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv_view = (TextView)findViewById(R.id.tv_view);
        tv_view.setTextSize(50);
        
        DownloadStockData bp = new DownloadStockData();
        bp.execute("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
         
	public String prevFridayQS(String symbol) {
		Calendar myCal = Calendar.getInstance();

		if (Calendar.DAY_OF_WEEK == Calendar.FRIDAY) {
			myCal.add(Calendar.DAY_OF_WEEK, -1);
		}

		while (myCal.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			myCal.add(Calendar.DAY_OF_WEEK, -1);
		}

		int day = myCal.get(Calendar.DAY_OF_MONTH);
		int month = (myCal.get(Calendar.MONTH)) + 1;
		int year = myCal.get(Calendar.YEAR);
		String theFriday = year + "-" + month + "-" + day;
		
		if (day < 10) {
			displayDate = "0" + day + "/" + month + "/" + year;
		} else {
			displayDate = day + "/" + month + "/" + year;
		}
		
		String qs = "http://query.yahooapis.com/v1/public/yql?q=select%20Close%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"
				+ symbol
				+ "%22%20and%20startDate%20%3D%20%22"
				+ theFriday
				+ "%22%20and%20endDate%20%3D%20%22"
				+ theFriday
				+ "%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";

		return qs;
	}
    
	private class DownloadStockData extends AsyncTask<String, Integer, String> {

		String[] results;
		String[] stockSymbols;

		@Override
		protected String doInBackground(String... sUrl) {

			// list of symbols
			stockSymbols = new String[] { "BP.L", "EXPN.L", "HSBA.L", "MKS.L",
					"SN.L" };
			results = new String[5];

			// setup and start connection
			BasicHttpParams connectionSettings = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(connectionSettings, 3000);
			HttpClient httpclient = new DefaultHttpClient(connectionSettings);

			for (int i = 0; i < stockSymbols.length; i++) {
				// send request for symbol
				HttpGet httpget = new HttpGet(prevFridayQS(stockSymbols[i]));
				HttpResponse response;

				// start processing response
				try {
					response = httpclient.execute(httpget);
					if (response.getStatusLine().getStatusCode() != 200) {
						results[i] = "error";
					}

					HttpEntity entity = response.getEntity();

					if (entity != null) {
						InputStream instream = entity.getContent();
						String result = convertStreamToString(instream);

						// parse result string
						JSONObject json = new JSONObject(result.toString());

						JSONObject query = json.getJSONObject("query");

						if (query.getInt("count") == 0) {
							results[i] = "error";
						} else {
							results[i] = query.getJSONObject("results")
									.getJSONObject("quote").getString("Close");
						}
						instream.close();
					}
				} catch (ClientProtocolException e) {
					Log.d("x", "ClientProtocolException");
				} catch (IOException e) {
					Log.d("y", "IOException " + e.getMessage());
					results[i] = "error";
				} catch (JSONException e) {
					Log.d("z", "JSONException " + e.getMessage());
					e.getMessage();
				}
			}

			return null;
		}
    	
        private String convertStreamToString(InputStream is) {
        	/*
        	 * To convert the InputStream to string we use the 
        	 * BufferedReader.readLine() method. We iterate until the BufferedReader 
        	 * return null which means there's no more data to read. Each line will
        	 * appended to a StringBuilder and returned as String.
        	 */
        	
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        	StringBuilder sb = new StringBuilder();
        	
        	String line = null;
        	try {
        		while ((line = reader.readLine()) != null) {
        			sb.append(line + "\n");
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        	} finally {
        		try {
        			is.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        	return sb.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv_view.setText("Loading data from web...");   
        }

    	@Override
         protected void onPostExecute(String success) {
             super.onPostExecute("success");
    		 
             int[] multipliers = new int[] {192, 258, 343, 485, 1219};
             String[] stockComapnys = new String[] {"BP Amoco Plc", "Experian Ordinary", "HSBC Holdings Plc  Ordinary", "Marks and Spencer Ordinary", "Smith & Nephew Plc Ordinary"};

             // add up all the results and note the errors
			float total = 0;
			int errorCount = 0;
			String errorSymbols = "";
			for (int i = 0; i < stockSymbols.length; i++) {
				if (results[i].equals("error")) {
					errorSymbols += stockComapnys[i] + "\n";
					errorCount++;
				} else {
					total = total + Float.parseFloat(results[i])
							* multipliers[i];
				}
			}

			if (errorCount < 5) {
				// convert total from pence to pounds and round the result
				total /= 100;
				int roundedTotal = Math.round(total);

				// add in commas
				String portfolio = String.valueOf(roundedTotal);

				String sub1 = "";
				String sub2 = "";
				String sub3 = "";
				if (portfolio.length() > 6) {
					sub1 = portfolio.substring((portfolio.length() - 3),
							(portfolio.length()));
					sub2 = portfolio.substring((portfolio.length() - 6),
							portfolio.length() - 3);
					sub3 = portfolio.substring(0, portfolio.length() - 6);

					tv_view.setText("Total portfolio value: £" + sub3 + ","
							+ sub2 + "," + sub1 + "\n" + displayDate);
				} else if (portfolio.length() > 3) {
					sub1 = portfolio.substring((portfolio.length() - 3),
							(portfolio.length()));
					sub2 = portfolio.substring(0, portfolio.length() - 3);

					tv_view.setText("Total portfolio value: £" + sub2 + ","
							+ sub1 +  "\n" + displayDate);
				} else {
					tv_view.setText("Total portfolio value: £" + portfolio + "\n" + displayDate);
				}
			} else {
				tv_view.setText("No data could be retrieved.");
			}
			
			if (errorCount > 0 && errorCount != 5) {
				tv_view.setText(tv_view.getText()
						+ "\nResults could not be found  for: \n"
						+ errorSymbols);
			}
         }
    }
}
