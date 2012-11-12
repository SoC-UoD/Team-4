package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StockArrayAdapter extends ArrayAdapter<Stock> {
	  private final Context _context;
	  private final Stock[] _values;

	  public StockArrayAdapter(Context context, Stock[] values) {
	    super(context, R.layout.two_col_list, values);
	    this._context = context;
	    this._values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.two_col_list, parent, false);
	    
	    TextView company = (TextView) rowView.findViewById(R.id.lv_company);
	    company.setText("  " + _values[position].getCompany());
	   
	    TextView currentPrice = (TextView)rowView.findViewById(R.id.lv_price);
	    currentPrice.setText("£" + 
	    				UtilityFunctions.formatCommas(
	    				UtilityFunctions.convertToPoundsRounded(
	    				_values[position].getCurrentPrice()
	    ))
	    + "  ");
	   
	    return rowView;
	  }
}