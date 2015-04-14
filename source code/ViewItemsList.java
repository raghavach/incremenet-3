package com.example.bussinessanalsis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.bussinessanalsis.database.DataBaseConector;



import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class ViewItemsList  extends Activity{
	 DataBaseConector dbconnector;
	 ListView list;
	 ItemsDetails itemdetails;
	 private ArrayAdapter<String> listAdapter ; 
	 Listviewadapter adapter;
	 ArrayList<HashMap<String, String>> arraylist;
	 String result;
	  ArrayList<String> lists = new ArrayList<String>();
	  HashMap<String, String> map = new HashMap<String, String>();
	  String dates,item,price,Description,tag;
	  static String Date="date";
	  static String Discription="Discription";
	  static String notes="notes";
	static String Price="Price";
	static String sppinerval="sppinerval";
	static String status="status";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item_list);
        list=(ListView)findViewById(R.id.listView1);
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
		Log.i("the spinner value",sppinerval);
		String notes=itemdetails.getNote();
		String tag=itemdetails.getTag();
		String status=itemdetails.getStatus();
		result=Date+""+" "+sppinerval +" "+" Price:"+Price+"\n"+"Tag:"+tag+" "+"Description:"+Discription;
		map.put("Date", Date);
		map.put("Discription", Discription);
		map.put("sppinerval", sppinerval);
		map.put("notes", notes);
		map.put("Date", Date);
		map.put("Price", Price);
		map.put("Date", Date);
		/*map.put(Date, Date);
		map.put(Discription, Discription);
		map.put(sppinerval, sppinerval);
		map.put(notes, notes);
		map.put(Date, Date);
		map.put(Price, Price);
		map.put(status,status);*/
		 arraylist.add(map);
         System.out.println("map details: " + arraylist.size());
	// result=Date+" "+price+";"+Discription+";"+sppinerval+";"+notes+";"+sppinerval;
		Log.i("the result values",result);
	    lists.add(result);
		}
		
	 adapter = new Listviewadapter(ViewItemsList.this, arraylist);
		
		//String result =discrption+";"+price+";"+tag+";"+note+";"+date+";"+sppinervalue;*/
		ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lists);
		list.setAdapter(adapter1);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				final String  selecteditem=list.getItemAtPosition(arg2).toString();
				Log.i("selecteditem",selecteditem);
				
			//	18/03/2015 Eating Out  Price:100
			//	Description:hotel tag:public
				String arr[]=selecteditem.split(" ");
				 dates=arr[0];
				Log.i("selecteddates",dates);
				 item=arr[1];
				Log.i("item",item);
				 tag=arr[2];
				Log.i("selectedprice",tag);
				 Description=arr[3];
				Log.i("Description",Description);
			   price=arr[4];
				Log.i("tag",price);
			
				
				final AlertDialog.Builder adb=new AlertDialog.Builder(new ContextThemeWrapper(ViewItemsList.this, R.style.AlertDialogCustom));
				adb.setTitle("Selected Items");
			
				adb.setMessage("Date:"+dates+"\n"+"ItemName:"+item+"\n"+price+"\n"+Description+"\n"+tag);
				
		
				adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
	                public void onClick(DialogInterface dialog,int id) {

	                    
 }                   

	              });
				adb.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

	                public void onClick(DialogInterface dialog,int id) {

	                    // go to a new activity of the app

	                	   Intent editintent = new Intent(getApplicationContext(),

		                            EditActivity.class);
		                    
	                	   editintent.putExtra("date",dates);
	                	   editintent.putExtra("item",item);
	                	   editintent.putExtra("price",price);
	                	   editintent.putExtra("Description",Description);
	                	   editintent.putExtra("tag",tag);
	                	   Log.i("the price value",price);
	                	   Log.i(" thee values",dates+item+","+price+","+tag+","+Description);
	                	  // "tag",tag
		                    startActivity(editintent);

	                }

	              });
				adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {

	                public void onClick(DialogInterface dialog,int id) {
	                	
						final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								ViewItemsList.this);
						alertDialogBuilder.setTitle("Delete Item");
						alertDialogBuilder.setMessage(" Are you want to delete selected item");
						alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
								String user_id = prefs.getString("userid", "");
								 int result=	dbconnector.delete_item(item, user_id);
								 Intent intent = getIntent();
								    finish();
								    startActivity(intent);
							}
						});
                 alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								adb.show();
							}
						});
						
                 alertDialogBuilder.show();
	               

             }

	              });
				
				
				adb.show();
				
				
			}
		});
		Log.i("the result values+",itemdetails.toString());
		
		
	}

}
