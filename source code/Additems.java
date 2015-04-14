package com.example.bussinessanalsis;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.example.bussinessanalsis.database.DataBaseConector;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView.FixedViewInfo;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Additems extends Activity implements OnClickListener {
	   DatePicker picker;
		Button displayDate;
		EditText et;
		private SharedPreferences prefs;
	    private String prefName = "spinner_value";
	    int id=0;
	    Button cal;
	    String oper = "";
	    String discrption,price,tag,note,date,sppinervalue,status;
	    Spinner sp;
	    DataBaseConector dbconnector;
	    ArrayList<HashMap<String, String>> arraylist;
	    //UI References
	 EditText fromDateEtxt,et_discription,et_tag,et_price,et_note;
	 
	    Button b1,clear;
	    EditText etNum1;
		  EditText etNum2;

		  Button btnAdd;
		  Button btnSave,btnSub;
		  Button btnMult;
		  Button btnDiv;
		  CheckBox chkSelect;
	    private DatePickerDialog fromDatePickerDialog;
	   
	    
	    private SimpleDateFormat dateFormatter;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.add_register_itam);
	        cal=(Button)findViewById(R.id.btn_cal);
	        btnSave=(Button)findViewById(R.id.btn_save);
	        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	        dbconnector=new DataBaseConector(this);
	        
	     /*   etNum1 = (EditText) findViewById(R.id.etNum1);
		    etNum2 = (EditText) findViewById(R.id.etNum2);*/
		    
		    et_discription = (EditText) findViewById(R.id.editText1);
		    et_tag = (EditText) findViewById(R.id.editText2);
		    et_price = (EditText) findViewById(R.id.editText3);
		    et_note = (EditText) findViewById(R.id.editText4);
		    chkSelect=(CheckBox)findViewById(R.id.checkBox1);
		    

		    btnSave.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					tag =	et_tag.getText().toString();
					price=	et_price.getText().toString();
					date=fromDateEtxt.getText().toString();
					discrption=	et_discription.getText().toString();
					if(chkSelect.isChecked())
				    {
				    	Log.i("clead on check box","status cleared");
				    	status="Cleared";
				    }
				    else
				    {
				    	status="Pending";
				    }
					if(date.length()==0)
					{
						fromDateEtxt.setError("	Date Field is Empty");
						fromDateEtxt.requestFocus();
						return;
					}
					if(discrption.length()==0)
					{
						et_discription.setError("	Discrption Field is Empty");
						et_discription.requestFocus();
						return;
					}
					if(price.length()==0)
					{
						et_price.setError("	Price Field is Empty");
						et_price.requestFocus();
						return;
					}
					if(tag.length()==0)
					{
						et_tag.setError("	tag Field is Empty");
						et_tag.requestFocus();
						return;
					}
					
					SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
					String userid = prefs.getString("userid", "");
				
					discrption = discrption.replace(" " , "");
					Log.i("discrption",discrption);
					
					tag = tag.replace(" " , "");
					Log.i("the tag",tag);
					note=et_note.getText().toString();
					
					sppinervalue=sp.getSelectedItem().toString();
					Log.i("the vlaues",discrption.trim()+"||"+price+"||"+tag.trim()+"||"+note+"||"+date+"||"+sppinervalue);
					ContentValues additems= new ContentValues();
					//(reg_userid text, discrption text, price text,tag text,note text,date text,sppinervalue text )";		
					additems.put("reg_userid", userid);
					additems.put("discrption", discrption.trim());
					additems.put("price", price);
					additems.put("tag", tag.trim());
					additems.put("note", note);
					additems.put("date", date);
					additems.put("sppinervalue", sppinervalue);
					additems.put("status", status);
					Log.i("the inserted items",additems.toString());
					dbconnector.insert_items(additems);
					clear();
					Intent i = new Intent(Additems.this,ViewItemsList.class);
					startActivity(i);
				}
			}) ;

		  
	        cal.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater li = LayoutInflater.from(getBaseContext());
					View promptsView = li.inflate(R.layout.calc, null);
					
					    
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							Additems.this);
					alertDialogBuilder.setView(promptsView);
					alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
					    	
					    }
					  })
					  .setNegativeButton("Cancel",
							  new DialogInterface.OnClickListener() {
							    public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							    }
							  });
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
		 
					}
					    
					
				
			});
	        findViewsById();
	        
	        setDateTimeField();
	        List<String> list=new ArrayList<String>();
	        list.add("Select a categorized");
	        list.add("Uncategorized");
	        list.add("Bills");
	        list.add("Clothing");
	        list.add("Deposit");
	        list.add("EatingOut");
	        list.add("Entertainment");
	        list.add("Gifts");
	        list.add("Groceries");
	        list.add("Insurance");
	        list.add("Medical");
	        list.add("Payment");
	        list.add("Rent");
	        list.add("Salary");
	        list.add("Shopping");
	        list.add("Transfer");
	        list.add("Transportation");
	        list.add("Utilities");
	        
	         sp=(Spinner) findViewById(R.id.spinner1);
	       b1=(Button)findViewById(R.id.button2);
	       clear=(Button)findViewById(R.id.button3);
	       b1.setTextColor( Color.RED );
	       
	        b1.setOnClickListener( new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	               
	                b1.setText("Income");
	                b1.setTextColor(Color.GREEN );
	            }
	        });
	        
	        ArrayAdapter<String> adp= new ArrayAdapter<String>(this,
	                                    android.R.layout.simple_list_item_1,list);
	        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        sp.setAdapter(adp);

	        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	        id=prefs.getInt("last_val",0);
	        sp.setSelection(id);

	        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

	            @Override
	            public void onItemSelected(AdapterView<?> arg0, 
	                View arg1,int pos, long arg3) {

	            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
	            SharedPreferences.Editor editor = prefs.edit();
	            //---save the values in the EditText view to preferences---
	            editor.putInt("last_val", pos);

	            editor.commit();

	            Toast.makeText(getBaseContext(), 
	                sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
	        }
	        @Override
	        public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub                   
	        }


	    });               
	    
	    clear.setOnClickListener(new OnClickListener() {
	    	 
			@Override
			public void onClick(View arg0) {
	 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Additems.this);
	 
				// set title
				alertDialogBuilder.setTitle("Do You Want To Clear");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Clear form?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
							clear();
							//Additems.this.finish();
						}

						
					  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
				}
			});
	    }
	    
		
	    private void findViewsById() {
	        fromDateEtxt = (EditText) findViewById(R.id.editText5);    
	        fromDateEtxt.setInputType(InputType.TYPE_NULL);
	        fromDateEtxt.requestFocus();
	        
	        
	    }
	 
	    private void setDateTimeField() {
	        fromDateEtxt.setOnClickListener(this);
	     
	        
	        Calendar newCalendar = Calendar.getInstance();
	        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
	 
	            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	                Calendar newDate = Calendar.getInstance();
	                newDate.set(year, monthOfYear, dayOfMonth);
	                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
	            }
	 
	        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	        
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    private void clear() {
	    	fromDateEtxt.setText(" ");
	    	et_discription.setText("");
	    	et_tag.setText(" ");
	    	et_price.setText(" ");
	    	et_note.setText(""); 
		}
	   
	    @Override
	    public void onClick(View view) {
	        if(view == fromDateEtxt) {
	            fromDatePickerDialog.show();
	        
	  	  /*  // TODO Auto-generated method stub
	  	    float num1 = 0;
	  	    float num2 = 0;
	  	    float result = 0;

	  	    // check if the fields are empty
	  	    if (TextUtils.isEmpty(etNum1.getText().toString())
	  	        || TextUtils.isEmpty(etNum2.getText().toString())) {
	  	      return;
	  	    }

	  	    // read EditText and fill variables with numbers
	  	    num1 = Float.parseFloat(etNum1.getText().toString());
	  	    num2 = Float.parseFloat(etNum2.getText().toString());

	  	    // defines the button that has been clicked and performs the corresponding operation
	  	    // write operation into oper, we will use it later for output
	  	    switch (view.getId()) {
	  	    case R.id.btnAdd:
	  	      oper = "+";
	  	      result = num1 + num2;
	  	      break;
	  	    case R.id.btnSub:
	  	      oper = "-";
	  	      result = num1 - num2;
	  	      break;
	  	    case R.id.btnMult:
	  	      oper = "*";
	  	      result = num1 * num2;
	  	      break;
	  	    case R.id.btnDiv:
	  	      oper = "/";
	  	      result = num1 / num2;
	  	      break;
	  	    default:
	  	      break;
	  	      
	  	    }
	        }    */
	    } 
	       
	    } 
	    
	}


