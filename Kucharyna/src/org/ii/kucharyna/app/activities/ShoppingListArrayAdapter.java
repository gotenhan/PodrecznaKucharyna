package org.ii.kucharyna.app.activities;

import podreczna.kucharyna.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
 
public class ShoppingListArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public ShoppingListArrayAdapter(Context context, String[] values) {
		super(context, R.layout.shoppinglistitem, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.shoppinglistitem, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.shoppingListItemName);
		textView.setText(values[position]);
 
		return rowView;
	}

}
