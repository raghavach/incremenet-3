package com.example.bussinessanalsis;

import java.util.ArrayList;
import java.util.HashMap;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Listviewadapter  extends BaseAdapter{
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ArrayList<HashMap<String, String>> arraylist;
	   private String[] mKeys;
	   HashMap<String, String> resultp = new HashMap<String, String>();
	public Listviewadapter(ViewItemsList viewItemsList,
			ArrayList<HashMap<String, String>> arraylist2) {
		this.context = context;
		data = arraylist2;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 System.out.println("getCount: "+data.size() );
		 System.out.println("getCount: "+data.toString() );
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.view_items, parent, false);
		resultp = data.get(position);
		TextView date=(TextView)itemView.findViewById(R.id.view_date);
		TextView item=(TextView)itemView.findViewById(R.id.view_itemname);
		TextView price=(TextView)itemView.findViewById(R.id.view_price);
		TextView status=(TextView)itemView.findViewById(R.id.view_status);
		date.setText(resultp.get(ViewItemsList.Date));
		item.setText(resultp.get(ViewItemsList.sppinerval));
		price.setText(resultp.get(ViewItemsList.Price));
		status.setText(resultp.get(ViewItemsList.status));
	
		//resultp = data.get(position);
		return null;
	}

}
