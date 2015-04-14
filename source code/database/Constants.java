package com.example.bussinessanalsis.database;


import com.example.bussinessanalsis.Login;
import com.example.bussinessanalsis.Splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

public class Constants {

	//public static String LoginID, Password, EMailID;
	public static SharedPreferences preferencesBack;
	public static SharedPreferences.Editor editorBack;
	//public static ProgressDialog dialog;
	static Context  context;
	static ConnectivityManager c;
	static NetworkInfo w,m;
	public static void put(String key, String Value)
	{
		context=Splash.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		Constants.editorBack = Constants.preferencesBack.edit();
		Constants.editorBack.putString(key,Value);
		Constants.editorBack.commit();
	}
	public static String get(String VariableName,String value)
	{
		context=Splash.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		Constants.editorBack = Constants.preferencesBack.edit();
		return Constants.preferencesBack.getString(VariableName,value);
	}
	public static void clear()
	{
		context = Splash.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		preferencesBack.edit().clear().commit();
	}
	public static void put1(String key,int Value)
	{
		context=Login.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		Constants.editorBack = Constants.preferencesBack.edit();
		Constants.editorBack.putLong(key,Value);
		Constants.editorBack.commit();
	}
	public static long get1(String VariableName,int value)
	{
		context=Login.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		Constants.editorBack = Constants.preferencesBack.edit();
		return Constants.preferencesBack.getLong(VariableName,value);
	}
	public static void clear1()
	{
		context = Login.context;
		preferencesBack = PreferenceManager.getDefaultSharedPreferences(context);
		preferencesBack.edit().clear().commit();
	}
	
	/*public static Boolean isNetworkAvailable(){
	c = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	w = c.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	m = c.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	if(w.isConnected()||m.isConnected()){
		return true;
	}else{
		return false;
	}
	}*/
}

