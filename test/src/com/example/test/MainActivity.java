package com.example.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv_view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        tv_view = (TextView)findViewById(R.id.tv_view);
        tv_view.setText("Not hello world");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void testClick(View view)
    {

    	DownloadStockData bp = new DownloadStockData();
    	bp.execute("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D%22BP.L%22&format=json&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=");
    	    	 	
    }
 
    private class DownloadStockData extends AsyncTask<String, Integer, String> {

    	String results[];
    	Boolean connectionOk;
    	
        @Override
        protected String doInBackground(String... sUrl) {
        	
        	BasicHttpParams connectionSettings = new BasicHttpParams();
        	HttpConnectionParams.setConnectionTimeout(connectionSettings, 3000);
        	
        	
        	HttpClient httpclient = new DefaultHttpClient(connectionSettings);
        	String[] str = new String[5];
        	
        	try {
        	// Prepare a request object
        	HttpGet httpget = new HttpGet(sUrl[0]);
        	
        	
        	// Execute the request
        	HttpResponse response;
        	
        	
        	
        		response = httpclient.execute(httpget);

            		Log.d("respone status value", response.getStatusLine().toString());

            		if (response.getStatusLine().getStatusCode() == 200)
            		{
            			connectionOk = true;

            		} else {
            			connectionOk = false;
            		}
            	
        		
        		// Get hold of the response entity
        		HttpEntity entity = response.getEntity();
        		// If the response does not enclose an entity, there is no need
                // to worry about connection release    		
        		
        		if (entity != null) {
        			
        			// A Simple JSON Response Read
        			InputStream instream = entity.getContent();
        			String result = convertStreamToString(instream);
        			//result=result.replace("parseExchangeRate(", "").replace(");", "");
        			Log.d("result: ", result);
        			
        			// A Simple JSONObject Creation
        			JSONObject json = new JSONObject(result.toString());
        			
        			JSONObject query = json.getJSONObject("query");
        			
        			JSONObject results = query.getJSONObject("results");
        			JSONObject quote = results.getJSONObject("quote");
        			
        			str[0] = quote.getString("symbol");
        			str[1] = quote.getString("LastTradePriceOnly");
        			Log.d(str[0], str[1]);
        			    			    			   			
        			// Closing the input stream will trigger connection release
        			instream.close();    			
        		}
        	} catch (ClientProtocolException e) {
        		Log.d("x", "ClientProtocolException");
        	} catch (IOException e) {
        		Log.d("y", "IOException " + e.getMessage());
        		connectionOk = false;
        	} catch (JSONException e) {
        		Log.d("z", "JSONException " + e.getMessage());
        		e.getMessage();
        	} 
        	
        	results = str;
        	
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

    	public String[] getResults()
    	{
    		return results;
    	}
    	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv_view.setText("loading");   
        }

    	@Override
         protected void onPostExecute(String success) {
            if (connectionOk)
            {
            	super.onPostExecute("success");
             
            	tv_view.setText(results[0] + " = " + results[1] + "\n");   
            } else 
            {
            	super.onPostExecute("failure");
            	tv_view.setText("The yahoo feed is down");
            }
      	   
         }
    }
}
