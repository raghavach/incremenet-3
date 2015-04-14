package com.example.bussinessanalsis;





import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.bussinessanalsis.database.DataBaseConector;



import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends Activity {
	EditText ed_username,ed_password,ed_confirmPassword;
	String username,password,confirmPassword,response,status,message;;
	ImageView registersubmit,registercancel,registerlogin;

	 private DataBaseConector dbConnect;
	AlertDialog alert;
	String id;
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		 registersubmit = (ImageView)findViewById(R.id.btn_register_submit);
		registercancel = (ImageView)findViewById(R.id.btn_registration_cancel);
		 registerlogin = (ImageView)findViewById(R.id.btn_registration_login);
		 ed_username = (EditText)findViewById(R.id.ed_register_username);
		 ed_password = (EditText)findViewById(R.id.ed_registration_password);
		 ed_confirmPassword = (EditText)findViewById(R.id.ed_registration_confirmPassword);
		 dbConnect = new DataBaseConector(this);
		 Pattern emailPattern = Patterns.EMAIL_ADDRESS;
		 Context context = this;
			String GoogleAccount="";
			Account[] accounts = AccountManager.get(context).getAccounts();
			for (Account account : accounts) {
			    if (emailPattern.matcher(account.name).matches()) {
			        GoogleAccount = account.name;
			        ed_username.setText(GoogleAccount);
			        System.out.println("MyProg-....GoogleAccount...."+GoogleAccount);
			        //if(useredit.getText().toString() != "") password.requestFocus();
			    }
			}
	
	registersubmit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			username = ed_username.getText().toString();
			password = ed_password.getText().toString();
			confirmPassword = ed_confirmPassword.getText().toString();
			if (username.length()==0 || username==null) {
				ed_username.setError("	username Field is Empty");
				ed_username.requestFocus();
				return;
			}
			if(!(username
					.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
							+ "\\@"
							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
							+ "(" + "\\."
							+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
							+ ")+"))){
				
				ed_username.setError("Enter valid email id");
				ed_username.requestFocus();
				return;
			}
			
			if (password.length()==0 || password==null) {
				ed_password.setError("username Field is Empty");
				ed_password.requestFocus();
				return;
			}
			if(password.length()<7||password.length()>10)
			{
				ed_password.setError("Password should be in between 7 and 10 characters");
				ed_password.requestFocus();
				return;
			}
			if (confirmPassword.length()==0 || confirmPassword==null) {
				ed_confirmPassword.setError("confirmPassword Field is Empty");
				ed_confirmPassword.requestFocus();
				return;
			}
			 if (!confirmPassword.equals(password)) {
					ed_confirmPassword.setError("confirmPassword is not matched");
					ed_confirmPassword.requestFocus();
					return;
				}
			if(!username.equals("")&&!password.equals("")&&confirmPassword.equals(confirmPassword)){
				
				
				Log.d("onclick","after calling execute()");
			
		String result=		dbConnect.get_userdetails(username);
		   if(result!=null)
		 {
			Toast.makeText(getBaseContext(), "user already exist", Toast.LENGTH_LONG).show();
		        }
		   else{
			
		            ContentValues values = new ContentValues();
		              values.put("reg_userid", username);
		              values.put("password", password);
		              values.put("reg_isactive", "0");
				    dbConnect.insert_register(values);
				    Toast.makeText(getBaseContext(), "Register success", Toast.LENGTH_LONG).show();
				 
				   Intent intent = new Intent(Register.this,Login.class);
				  intent.putExtra("username", username);
				   clear();
				   startActivity(intent);
				   
		   }
	}
			
			
		
		}
		});
	registercancel.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0)
		{
			Toast.makeText(getBaseContext(), "you clicked!  on cancel", Toast.LENGTH_LONG).show();
			
		}
		});
	registerlogin.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			ContentValues values = new ContentValues();
			values.put("reg_userid", username);
			values.put("password", password);
			values.put("lo_isactive", 0);
			dbConnect.insert_register(values);
			Intent intent = new Intent(Register.this,Login.class);
			startActivity(intent);
			
		}
		});

}
	public class GetResult extends AsyncTask<String, String, String>
	{
		URL url=null;
		HttpResponse httpResponse=null;
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(Register.this, "",
					"Loading. Please Wait....");
			Log.d("onPreExecute()","after creating progressDialog");
		}
		
		@Override
		protected String doInBackground(String... params)
		{
			try{
			url = new URL(params[0]);
			Log.d("url",url.toString());
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			
			HttpClient httpClient = new DefaultHttpClient();
			Log.d("doInBackground","after creating httpClient");
			HttpPost httpPost = new HttpPost(url.toString());
			Log.d("doInBackground","after creating httpPost");
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("USERNAME", username));
			parameters.add(new BasicNameValuePair("PASSWORD", password));
			
		
			
			try{
				httpPost.setEntity(new UrlEncodedFormEntity(parameters));
				Log.d("doInBackground","after adding parameters");
				httpResponse = httpClient.execute(httpPost);
				Log.d("doInBackground","after getting response");
				HttpEntity httpEntity = httpResponse.getEntity();
				Log.d("doInBackground","after creating httpEntity");
				if(httpEntity!=null)
				response = EntityUtils.toString(httpEntity);
				
				Log.d("response", response);
			}catch(Exception e){
				e.printStackTrace();
			}
			//Log.d("res",response);
			return null;
		}
		
		@Override
		protected void onPostExecute(String result)
		{
			//Log.d("Response", response);
			
			if(response!=null)
			{
				Log.d("Response",response);
				try{
				JSONObject js = new JSONObject(response);
				JSONObject json = js.getJSONObject("ResponseStatus");
				status = json.getString("status");
				message = json.getString("message");
				/*Log.d("response",response);
				Log.d("status",status);
				Log.d("message",message);
*/				
				}catch(Exception e){
					e.printStackTrace();
				}
				alert = new AlertDialog.Builder(Register.this).create();
				alert.setTitle(status);
				alert.setMessage(message);
				alert.setButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(status.equals("Success"))
						{
							//Constants.put("R_EMAIL", username);
							Intent intent = new Intent(Register.this,Login.class);
							startActivity(intent);
						}
						
					}
				});
				if(status.equals("Success"))
				{
					//alert.show();
				}
			}
			else
				Log.d("Response","null");
			alert.show();
			progressDialog.dismiss();
		}
		
	}
	public void clear()
	{
		ed_username.setText("");
		ed_password.setText("");
		ed_confirmPassword.setText("");
	}
}
