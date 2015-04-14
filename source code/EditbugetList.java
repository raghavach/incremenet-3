package com.example.bussinessanalsis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.bussinessanalsis.database.DataBaseConector;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditbugetList  extends Activity implements  AdapterView.OnItemSelectedListener,OnClickListener{
	DataBaseConector dbconnector;
	  int result;
	  String budjetname,price,budgettype,montbudjet,date,total,startdate,enddate,status;
	  EditText prictext,fromDateEtxt;
	  TextView bdgtext;
	  Button savebtn,canselbtn;
	   private SimpleDateFormat dateFormatter;
	   private DatePickerDialog fromDatePickerDialog;
	   private SimpleDateFormat dateFormatter1;
	   Bundle extras;
	   Intent intent;

	   
	   
	    String[] budget = {"MonthlyBudget","WeeklyBudget","BiweeklyBudget","YearlyBudget","EndlessBudget"};  
	    String[] begins = {"First month","Second month","Third month","Fourth month","Fifth  month","Sixth  month","Seventh  month","Eight month","Ninenth month","Tenth month","Eleventh month","Twelveth month"};  
	@Override
	public void onCreate(Bundle saveinstance)
	{
		  super.onCreate(saveinstance);  
	        setContentView(R.layout.editbudjet);  
	        intent  = getIntent();
	        if(intent!=null)
	        {
	        	budjetname=intent.getStringExtra("budgettype");	
	        	Log.i("budjetname",budjetname);
	        	total=intent.getStringExtra("Price");
	        	Log.i("price",total);
	        	total.replace("\n", ",");
	        	String pri[]=total.split(",");
	        	price=pri[0];
	        
	        	budgettype= intent.getStringExtra("budgettype");
	        	Log.i("budjetname",budjetname);
	        	enddate=	intent.getStringExtra("Startdate");
	        
	        }
	        Spinner spin = (Spinner) findViewById(R.id.spinner_montly);  
	        Spinner spin2 = (Spinner) findViewById(R.id.spinners);  
	        spin.setOnItemSelectedListener(this);  
	        spin2.setOnItemSelectedListener(this);  
	        bdgtext=(TextView)findViewById(R.id.budjet_text);
	        prictext=(EditText)findViewById(R.id.et_Price);
	        savebtn=(Button)findViewById(R.id.update_btn);
	        canselbtn=(Button)findViewById(R.id.btn_can);
	     
	        findViewsById();
	        setDateTimeField();
	        dateFormatter1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	        Date date1 = new Date();
	        date=fromDateEtxt.getText().toString();
	        String arr[]=budjetname.split(":");
	        budjetname=arr[1];
	        bdgtext.setText(budjetname);
	        String arr1[]=price.split(":");
	        price=arr1[1];
	        Log.i("price",price);
	        String arr4[]=price.split("\n");
	        price=arr4[0];
	        prictext.setText(price);
	        String arr2[]=enddate.split(":");
	        enddate=arr2[1];
	        fromDateEtxt.setText(enddate);
	        
	        Log.i("budjetname",budjetname+price+enddate);
	        
	        
	        
	        //Creating the ArrayAdapter instance having the country list  
	        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,budget);  
	        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	        //Setting the ArrayAdapter data on the Spinner  
	        spin.setAdapter(aa);  
	        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,begins);  
	        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
	        //Setting the ArrayAdapter data on the Spinner  
	        spin2.setAdapter(aa2);
	        final String stringDate =dateFormatter.format(date1);
	        dbconnector=new DataBaseConector(this);
	        result=dbconnector.price_list();
	        Log.v("tagActivity", "@ price  status " + result);
	        savebtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
					String userid = prefs.getString("userid", "");
					
					price=prictext.getText().toString();
					budjetname=bdgtext.getText().toString();
					budjetname.trim();
					budjetname=budjetname.replace(" " , "");
					 date=fromDateEtxt.getText().toString();
					ContentValues editbudget= new ContentValues();
					//reg_userid text,budgetname text, price text,budgettype text,month text,startdate text, text	
					editbudget.put("price", price);
					editbudget.put("budgetType", budgettype);
					editbudget.put("month", montbudjet);
					editbudget.put("startdate", stringDate);
					Log.i("the end date is",date);
					editbudget.put("enddate", date);
					result=dbconnector.update_budgetitems(editbudget,budjetname,userid);
				 Intent i = new Intent(EditbugetList.this,Budgetlist.class);
				 startActivity(i);
				 
					
				}
			});

	    }  
	    private void findViewsById() {
	        fromDateEtxt = (EditText) findViewById(R.id.end_date);    
	        fromDateEtxt.setInputType(InputType.TYPE_NULL);
	        fromDateEtxt.requestFocus();
	        
	        
	    }
	 
	    private void setDateTimeField() {
	    	Log.i("THE DATE EDIT TEXT","edittext");
	        fromDateEtxt.setOnClickListener(this);
	     
	        
	        Calendar newCalendar = Calendar.getInstance();
	        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
	 
	            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	                Calendar newDate = Calendar.getInstance();
	                newDate.set(year, monthOfYear, dayOfMonth);
	                fromDateEtxt.setText(dateFormatter1.format(newDate.getTime()));
	            }
	 
	        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	        
	    }
	  
	      
	    //Performing action onItemSelected and onNothing selected  
	    @Override  
	    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {  
	    	
	        Toast.makeText(getApplicationContext(),budget[position] ,Toast.LENGTH_LONG).show();
	        Toast.makeText(getApplicationContext(),begins[position] ,Toast.LENGTH_LONG).show();
	       budgettype=begins[position];
	    	montbudjet=budget[position];
	    }  
	  
	    @Override  
	    public void onNothingSelected(AdapterView<?> arg0) {  
	        // TODO Auto-generated method stub  
	          
	    }
		@Override
		public void onClick(View v) {
			  if(v == fromDateEtxt) {
		            fromDatePickerDialog.show();
			  }
			
		}  
	  
	  
}