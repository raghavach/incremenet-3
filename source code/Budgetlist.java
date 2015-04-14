package com.example.bussinessanalsis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.bussinessanalsis.database.DataBaseConector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

public class Budgetlist extends Activity 
{
	Button addbud_btn;
	ListView listitem;
	 DataBaseConector dbconnector;
	 ArrayList<HashMap<String, String>> arraylist;
	 String result;
	 BudjetDetails budjetitems;
	  ArrayList<String> lists = new ArrayList<String>();
	  HashMap<String, String> map = new HashMap<String, String>();
	  String budgettype,budgetname,Price,Startdate,enddate,month;
	  CheckBox chkSelect;
	
@Override
public void onCreate(Bundle savestate)
{
super.onCreate(savestate);
setContentView(R.layout.budjetlist); 
listitem=(ListView)findViewById(R.id.budjetlist);
addbud_btn=(Button)findViewById(R.id.button1);

dbconnector=new DataBaseConector(this);
SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
String userid = prefs.getString("userid", "");
List<ItemsDetails>	itemlist=dbconnector.getAllItems(userid);
List<BudjetDetails> budjetlistitem=dbconnector.getbudjetitem(userid);
System.out.println("itemlist details: " + itemlist.size());
budjetitems=new BudjetDetails();

for( BudjetDetails budjetitems:budjetlistitem)
{  
	arraylist = new ArrayList<HashMap<String, String>>();
String budgettype=budjetitems.getBudgettype();	
String budgetname= budjetitems.getBudgetname();
String Price= budjetitems.getPrice();
String Startdate=	budjetitems.getStartdate();
String enddate=	budjetitems.getEnddate();
String month=budjetitems.getMonth();

result="BudgetName:"+budgetname +"\n"+" BudgetType:"+month+" "+"Price:"+Price+"\n"+"Startdate:"+Startdate+" "+"Enddate:"+enddate;
map.put("budgettype", month);
map.put("budgetname", budgetname);
map.put("Price", Price);
map.put("Startdate", Startdate);
map.put("enddate", enddate);
map.put("Price", Price);
map.put("month", budgettype);
 arraylist.add(map);
 System.out.println("map details: " + arraylist.size());
// result=Date+" "+price+";"+Discription+";"+sppinerval+";"+notes+";"+sppinerval;
Log.i("the result values",result);
lists.add(result);
}
ArrayAdapter adapter1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,lists);
listitem.setAdapter(adapter1);
listitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		final String  selecteditem=listitem.getItemAtPosition(arg2).toString();
		Log.i("selecteditem",selecteditem);
		String result[]=selecteditem.split(" ");
		 
		  budgettype=result[0];
		  Log.i("budgettype",budgettype);
		  budgetname=result[1];
		  Log.i("budgetname",budgetname);
		  Price=result[2];
		  Log.i("Price",Price);
		  Startdate=result[3];
		  Log.i("Startdate",Startdate);
		  budgettype=result[0];
		  budgettype=result[0];
	//	18/03/2015 Eating Out  Price:100
	//	Description:hotel tag:public
		/*String arr[]=selecteditem.split(" ");
		 dates=arr[0];
		Log.i("selecteddates",dates);
		 item=arr[1];
		Log.i("item",item);
		 tag=arr[2];
		Log.i("selectedprice",tag);
		 Description=arr[3];
		Log.i("Description",Description);
	   price=arr[4];
		Log.i("tag",price);*/
	
		
		final AlertDialog.Builder adb=new AlertDialog.Builder(new ContextThemeWrapper(Budgetlist.this, R.style.AlertDialogCustom));
		adb.setTitle("Selected Items");
	
		adb.setMessage(budgettype+budgetname+"\n"+Price+"\n"+Startdate);
		

		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
            public void onClick(DialogInterface dialog,int id) {

                

             
               

            }

          });
		adb.setPositiveButton("Edit", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

                // go to a new activity of the app

            	  Intent editintent = new Intent(getApplicationContext(),

            			  EditbugetList.class);
                    
            	   editintent.putExtra("budgettype",budgettype);
            	   editintent.putExtra("budgetname",budgetname);
            	   editintent.putExtra("Price",Price);
            	   editintent.putExtra("Startdate",Startdate);
            	 
            	   Log.i(" budgettype values",budgettype+budgetname+","+Price+","+Startdate);
            	 
                    startActivity(editintent);

            }

          });
		adb.setNeutralButton("Delete", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {
            	final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            			Budgetlist.this);
				alertDialogBuilder.setTitle("Delete Item");
				alertDialogBuilder.setMessage(" Are you want to delete selected item");
				alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub



                         SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
				         String user_id = prefs.getString("userid", "");
				         String arr[]=budgettype.trim().split(":");
				         budgettype=arr[1];
				         Log.i("budgettype",budgettype);
            	        dbconnector.delete_Budget(budgettype, user_id);
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
addbud_btn.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(Budgetlist.this,Budget.class);
		startActivity(i);
	}
});



	
}
}
