package com.example.bussinessanalsis;

import com.example.bussinessanalsis.database.DataBaseConector;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login  extends Activity{
	
	public static Context context;
	EditText usernameedittext,passwordedittext;
	 private DataBaseConector dbConnect;
	 String userid,pswd;
	 String pswd_result;
	 Bundle extras ;
	 Intent intent;
	 SharedPreferences.Editor  preference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		ImageView login = (ImageView)findViewById(R.id.btn_login_login);
		ImageView cancelbtn = (ImageView)findViewById(R.id.btn_login_cancel);
		ImageView register = (ImageView)findViewById(R.id.btn_login_register);
		usernameedittext = (EditText)findViewById(R.id.ed_login_username);
		passwordedittext = (EditText)findViewById(R.id.ed_login_password);
		extras= getIntent().getExtras();
		dbConnect = new DataBaseConector(this);
	
		if(extras!=null)
		{
			Log.i("username",extras.toString());
			
			userid=	extras.getString("username");
	        usernameedittext.setText(userid);
		  
		}

	login.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			userid=usernameedittext.getText().toString();
			pswd=passwordedittext.getText().toString();
			if (userid.length()==0 || userid==null) {
				usernameedittext.setError("	username Field is Empty");
				usernameedittext.requestFocus();
				return;
			}
			if (pswd.length()==0 || pswd==null) {
				passwordedittext.setError("	password Field is Empty");
				usernameedittext.requestFocus();
				return;
			}
			
			String result= dbConnect.get_userdetails(userid);
			
			if(result!=null)
			{
				
				pswd_result	=dbConnect.getuser_credentials(userid,pswd);
				if(pswd_result!=null)
				{
					preference=	getSharedPreferences("", MODE_PRIVATE).edit();
					preference.putString("userid", userid);
					preference.commit();
					Toast.makeText(getBaseContext(),"login suscess",Toast.LENGTH_LONG).show();
					Intent i = new Intent(getBaseContext(),Homescreen.class);
					 clear();
					startActivity(i);
				}
				else
				{
					Toast.makeText(getBaseContext()," Please enter correct password ",Toast.LENGTH_LONG).show();
					
				}
			}
			else
			{
				Toast.makeText(getBaseContext()," user credentials doesnt exist please Register ",Toast.LENGTH_LONG).show();
				
			}
			
		
		}
		});
	
	register.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent in = new Intent(getApplicationContext(),Register.class);
			startActivity(in);

		}
	});
	cancelbtn.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
	});

	}
	public void clear()
	{
		usernameedittext.setText("");
		
		passwordedittext.setText("");
	}

}
