package com.example.bussinessanalsis.database;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	private String tagActivity = "DatabaseOpenHelper";
	
	
	public DatabaseOpenHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//discrption+"||"+price+"||"+tag+"||"+note+"||"+date+"||"+sppinervalue
		String login= "CREATE TABLE Login(loginid INTEGER PRIMARY KEY AUTOINCREMENT,lo_userid text ,lo_logintime TEXT,lo_logouttime	TEXT,password TEXT,lo_isactive INTEGER)";
		String  register= "CREATE TABLE Register (reg_id INTEGER PRIMARY KEY AUTOINCREMENT, reg_userid text, password text,reg_isactive INTEGER)";
		String additems="CREATE TABLE AddItems (reg_userid text, discrption text, price text,tag text,note text,date text,sppinervalue text,status text)";
		String Budgetitems="CREATE TABLE Budgetitems(reg_userid text,budgetname text, price text,budgettype text,month text,startdate text,enddate text )";
		try{
			db.execSQL(register);
			db.execSQL(Budgetitems);
			db.execSQL(login);
			db.execSQL(additems);
			
		}catch(Exception ex){
			Log.v(tagActivity, "@@ ERROR at creating database"+db.getPath());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
        db.execSQL("DROP TABLE IF EXISTS Login"); 
        db.execSQL("DROP TABLE IF EXISTS Register"); 
        db.execSQL("DROP TABLE IF EXISTS additems"); 
        db.execSQL("DROP TABLE IF EXISTS Budgetitems"); 
        onCreate(db);
	}
	

	
	

	
}