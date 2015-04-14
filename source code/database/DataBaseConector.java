package com.example.bussinessanalsis.database;

import java.util.ArrayList;
import java.util.List;

import com.example.bussinessanalsis.BudjetDetails;
import com.example.bussinessanalsis.ItemsDetails;


import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class DataBaseConector {
	private String tagActivity = "DatabaseConnector";

	private static final String DB_NAME = "Bussiness_Analysis";
	private SQLiteDatabase database;
	private DatabaseOpenHelper dbOpenHelper;

	public DataBaseConector(Context context) {
		dbOpenHelper = new DatabaseOpenHelper(context, DB_NAME, null, 111);
	}

	public void open() throws SQLException {
		// open database in reading/writing mode
		database = dbOpenHelper.getWritableDatabase();
	}

	public void close() {
		if (database != null)
			database.close();
	}
	
	public long insert_register(ContentValues values) {
		// TODO Auto-generated method stub
		long result=0;
		try
		{
			open();
			Log.v(tagActivity, " Register details status " + values.toString());
			result=database.insert("Register",null,values);
			
			Log.v(tagActivity, "@ insert Register  result" + result);
			
		}
		catch(Exception e)
		{
			Log.v(tagActivity, "@ insert Register  result" + e.getMessage());
		}
		  close();
		return result;
	}
	
	public long insert_items(ContentValues values) {
		// TODO Auto-generated method stub
		long result=0;
		try
		{
			open();
			Log.v(tagActivity, " Register details status " + values.toString());
			result=database.insert("AddItems",null,values);
			
			Log.v(tagActivity, "@ insert AddItems  result" + result);
			
		}
		catch(Exception e)
		{
			Log.v(tagActivity, "@ insert Register  result" + e.getMessage());
		}
		  close();
		return result;
	}
	 public String get_userdetails(String user_id)
	   {
		   Cursor cursor = null;
		   String result=null;
		   try {
		   open();
		   String selectQuery= "SELECT reg_userid from Register  WHERE reg_userid LIKE '"+user_id+"'";
		   cursor = database.rawQuery(selectQuery, null);
		   if (cursor.moveToFirst()) {
			   do {// cursor.getString(0)
				   if (cursor.getString(0) != null
							) {
						result=cursor.getString(0);
						Log.i("get_userdetails",result);
						/*callerslist.add(cursor.getString(0));*/
						}
						} while (cursor.moveToNext());
						}

							 
						}
						catch (Exception ex) {
								Log.e("error", "@@ ERROR atget_userdetails()()", ex);
								ex.printStackTrace();
							}
						finally {
							 if (cursor != null) {
								cursor.close();
							}
							 close();
						}
						return result;
						}
	 public String getuser_credentials(String userid, String pswd)
	   {
		   Cursor cursor = null;
		   String result=null;
		   try {
		   open();
		   String selectQuery= "SELECT reg_userid from Register  WHERE password LIKE '"+pswd+"' AND reg_userid LIKE'"+userid+"'";
		   Log.i("select query",selectQuery.toString());
		   cursor = database.rawQuery(selectQuery, null);
		   if (cursor.moveToFirst()) {
			   do {// cursor.getString(0)
				   if (cursor.getString(0) != null
							) {
						result=cursor.getString(0);
						Log.i("get_userdetails",result);
						/*callerslist.add(cursor.getString(0));*/
						}
						} while (cursor.moveToNext());
						}

							 
						}
						catch (Exception ex) {
								Log.e("error", "@@ ERROR a tget_userdetails()()", ex);
								ex.printStackTrace();
							}
						finally {
							 if (cursor != null) {
								cursor.close();
							}
							 close();
						}
						return result;
						}
     public ItemsDetails getitems(String userid){
    	 ItemsDetails item_details = new ItemsDetails();
    	 Cursor cursor = null;
		   String result=null;
		   try {
		   open();
		//   discrption,price,tag,note,date,sppinervalue;
		//   reg_userid text, discrption text, price text,tag text,note text,date text,sppinervalue text 
		   String selectQuery= "SELECT discrption,price,tag,note,date,sppinervalue from AddItems  WHERE reg_userid LIKE '"+userid+"'";
		   cursor = database.rawQuery(selectQuery, null);
		   if (cursor.moveToFirst()) {
				// cursor.getString(0);
			   item_details.setDiscrption(cursor.getString(0));
			   item_details.setPrice(cursor.getString(1));
			   item_details.setTag(cursor.getString(2));
						
			   item_details.setNote(cursor.getString(3));
			   item_details.setDate(cursor.getString(4));
			   item_details.setSppinervalue(cursor.getString(5));
//				event_details.setIsAccept(cursor.getString(6));
//				Log.i("&&&&&&&&&&&&&&&&&& accept", ""+cursor.getString(6));
			}

							 
						}
						catch (Exception ex) {
								Log.e("error", "@@ ERROR getitems()()", ex);
								ex.printStackTrace();
							}
						finally {
							 if (cursor != null) {
								cursor.close();
							}
							 close();
						}
						return item_details;
    	 
     }

  public  List<ItemsDetails> getAllItems(String userid)
  {
	  List<ItemsDetails> ItemsDetaillist = new ArrayList<ItemsDetails>();  
	  Cursor cursor = null;
	  open(); 
	  String selectQuery= "SELECT discrption,price,tag,note,date, status,sppinervalue from AddItems  WHERE reg_userid LIKE '"+userid+"'";
	   cursor = database.rawQuery(selectQuery, null);
 
      // looping through all rows and adding to list  
      if (cursor.moveToFirst()) {  
          do {  
        	  ItemsDetails item_details=new ItemsDetails();
        	  item_details.setDiscrption(cursor.getString(0));
			   item_details.setPrice(cursor.getString(1));
			   item_details.setTag(cursor.getString(2));
						
			   item_details.setNote(cursor.getString(3));
			   item_details.setDate(cursor.getString(4));
			   item_details.setStatus(cursor.getString(5));
			   item_details.setSppinervalue(cursor.getString(6));
			   
			   ItemsDetaillist.add(item_details);
				Log.i( "@@ItemsDetaillist()()", ItemsDetaillist.toString());
               
          } while (cursor.moveToNext());  
      } 
      return ItemsDetaillist;  
  }
  public int update_items(ContentValues values,String itemid,String userid)
  {
	 
	  int result=0;
	  try
	  {
	 
	  open();
	  Log.v(tagActivity, " update_items details status " + values.toString()+","+userid+","+itemid);
	  result = database.update("AddItems", values,
				"sppinervalue LIKE '" + itemid + "' AND reg_userid LIKE '" + userid + "'", null);
	  Log.v(tagActivity, "@ update_event_details status " + result);

	} catch (Exception ex) {
		Log.e(tagActivity, "@@ ERROR at update_event_details :  " + ex);
		ex.printStackTrace();
	} finally {
		close();
	}
	return result;
  }

   
  public int price_list()
  {
	  Cursor cursor = null;
	  int result=0;
	  try
	  {
		  open();
	   String selectQuery= "select sum(price)from AddItems" ;
	  Log.v(tagActivity, "@ price_list " + selectQuery);
	   cursor = database.rawQuery(selectQuery, null);
	   Log.v(tagActivity, "@ price  status " + cursor.getColumnCount());
	   if (cursor.moveToFirst()) {
		   result=  Integer.parseInt(cursor.getString(0));
		   Log.v(tagActivity, "@ price  status " + result);
			
			cursor.close();
			
		}
	  
	
	  Log.v(tagActivity, "@ price  status " + result);
	  }
	  catch(Exception e)
	  {
		  Log.v(tagActivity, "@ update_event_details status " + e);
	  }
	
	return result;
	  
  }
public Long addbudjet( ContentValues values)
{

	long result=0;
	try
	{
		open();
		Log.v(tagActivity, " Budgetitems details status " + values.toString());
		result=database.insert("Budgetitems",null,values);
		
		Log.v(tagActivity, "@ insert Budgetitems  result" + result);
		
	}
	catch(Exception e)
	{
		Log.v(tagActivity, "@ insert Budgetitems  result" + e.getMessage());
	}
	  close();
	return result;
}
public  List<BudjetDetails> getbudjetitem(String userid)
{
	  List<BudjetDetails> budjetdetail = new ArrayList<BudjetDetails>();  
	  Cursor cursor = null;
	  open(); 
	  String selectQuery= "SELECT budgetname,price,budgettype,month,startdate, enddate from Budgetitems  WHERE reg_userid LIKE '"+userid+"'";
	   cursor = database.rawQuery(selectQuery, null);

    // looping through all rows and adding to list  
    if (cursor.moveToFirst()) {  
        do {  
        //	reg_userid text,budgetname text, price text,budgettype text,month text,startdate text,enddate text
        	BudjetDetails items=new BudjetDetails();
        	
        	items.setBudgetname(cursor.getString(0));
			   items.setPrice(cursor.getString(1));
			   items.setBudgettype(cursor.getString(2));
						
			   items.setMonth(cursor.getString(3));
			   items.setStartdate(cursor.getString(4));
			   items.setEnddate(cursor.getString(5));
			   
			   budjetdetail.add(items);
				Log.i( "@@ItemsDetaillist()()", budjetdetail.toString());
             
        } while (cursor.moveToNext());  
    } 
    return budjetdetail;  
}
public int update_budgetitems(ContentValues values,String budgetname,String userid)
{
	 
	  int result=0;
	  try
	  {
	 
	  open();
	  Log.v(tagActivity, " update_items details status " + values.toString()+","+userid+","+budgetname);
	  //reg_userid
	  result = database.update("Budgetitems", values,
				"budgetname LIKE '" + budgetname + "' AND reg_userid LIKE '" + userid + "'", null);
	
	  Log.v(tagActivity, "@ update_event_details Budgetitems " + result);

	} catch (Exception ex) {
		Log.e(tagActivity, "@@ ERROR at Budgetitems :  " + ex);
		ex.printStackTrace();
	} finally {
		close();
	}
	return result;
}
public int budget_price_list()
{
	  Cursor cursor = null;
	  int result=0;
	  try
	  {
		  open();
	   String selectQuery= "select sum(price)from Budgetitems" ;
	  Log.v(tagActivity, "@ price_list " + selectQuery);
	   cursor = database.rawQuery(selectQuery, null);
	   Log.v(tagActivity, "@ price  status " + cursor.getColumnCount());
	   if (cursor.moveToFirst()) {
		   result=  Integer.parseInt(cursor.getString(0));
		   Log.v(tagActivity, "@ price  status " + result);
			
			cursor.close();
			
		}
	  
	
	  Log.v(tagActivity, "@ price  status " + result);
	  }
	  catch(Exception e)
	  {
		  Log.v(tagActivity, "@ update_event_details status " + e);
	  }
	
	return result;
	  
}
public int delete_item(String itemid,String userid)
		  {
	 Cursor cursor = null;
	  int result=0;
	  try
	  {
	 
	  open();
	  String deletequery= "DELETE FROM AddItems  WHERE reg_userid LIKE '"+userid+"' AND sppinervalue LIKE '"+itemid+"'";
	  Log.i("the query vlaues",deletequery.toString());
      database.execSQL(deletequery);
	  cursor = database.rawQuery(deletequery, null);
	
	  Log.v(tagActivity, "@ deleate details status " + result);

	} catch (Exception ex) {
		Log.e(tagActivity, "@@ ERROR at update_event_details :  " + ex);
		ex.printStackTrace();
	} finally {
		close();
	}
	return result;
}

public int delete_Budget(String budgetname,String userid)
{
Cursor cursor = null;
int result=0;
try
{

open();
String deletequery= "DELETE FROM Budgetitems  WHERE reg_userid LIKE '"+userid+"' AND budgetname LIKE '"+budgetname+"'";
Log.i("the query vlaues",deletequery.toString());
database.execSQL(deletequery);

cursor = database.rawQuery(deletequery, null);

Log.v(tagActivity, "@ deleate details status " + result);

} catch (Exception ex) {
Log.e(tagActivity, "@@ ERROR at update_event_details :  " + ex);
ex.printStackTrace();
} finally {
close();
}
return result;
}
}
