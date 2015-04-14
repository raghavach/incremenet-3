package com.example.bussinessanalsis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.bussinessanalsis.database.DataBaseConector;

import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AcountsScreen extends Activity {
	DataBaseConector dbconnector;
	 ItemsDetails itemdetails;
	 String discrption,price,tag,note,date,sppinervalue,status;
	 ListView list;
	 ArrayList<HashMap<String, String>> arraylist;
	 HashMap<String, String> map = new HashMap<String, String>();
	 ArrayList<String> lists = new ArrayList<String>();
	 String result;
	 int spentprice,Budjetprice,totalprice;
	 TextView spent_text,remainintext;
	@Override
	public void onCreate(Bundle save)
	{
		super.onCreate(save);
		setContentView(R.layout.acountslist); 
	 list= (ListView)findViewById(R.id.account_list);
	 spent_text=(TextView)findViewById(R.id.spent_amount);
	 remainintext=(TextView)findViewById(R.id.remaining_amount);
	 dbconnector=new DataBaseConector(this);
		SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
		String userid = prefs.getString("userid", "");
		List<ItemsDetails>	itemlist=dbconnector.getAllItems(userid);
		 System.out.println("itemlist details: " + itemlist.size());
		
itemdetails=new ItemsDetails();
		
		for( ItemsDetails itemdetails:itemlist)
		{  
			arraylist = new ArrayList<HashMap<String, String>>();
			
		String Price= itemdetails.getPrice();
		String Date=	itemdetails.getDate();
		String Discription=	itemdetails.getDiscrption();
		String sppinerval	=itemdetails.getSppinervalue();
		String notes=itemdetails.getNote();
		String tag=itemdetails.getTag();
		String status=itemdetails.getStatus();
		result="Date:"+Date+" "+"ItemName:" +sppinerval+" \n"+" Status:"+status+" "+"Price:"+Price;
		map.put("Date", Date);
		map.put("Discription", Discription);
		map.put("sppinerval", sppinerval);
		map.put("notes", notes);
		map.put("Date", Date);
		map.put("Price", Price);
		map.put("Date", Date);
		
		 arraylist.add(map);
         System.out.println("map details: " + arraylist.size());
	
		Log.i("the result values",result);
	    lists.add(result);
		}
		ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lists);
		list.setAdapter(adapter1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
		}
	});
	int spentprice=dbconnector.price_list();
	 Log.v("tagActivity", "@ price  status " + spentprice);
	int Budjetprice=dbconnector.budget_price_list();
	 Log.v("tagActivity", "@ price  status " + Budjetprice);
	if(spentprice!=0)
	{
		 spent_text=(TextView)findViewById(R.id.spent_amount);
		
		spent_text.setText(String.valueOf(spentprice));
		if(Budjetprice!=0)
		{
			 remainintext=(TextView)findViewById(R.id.remaining_amount);
			int totalprice=Budjetprice-spentprice;
			remainintext.setText(String.valueOf(totalprice));
		}
	}
	
	}

}
