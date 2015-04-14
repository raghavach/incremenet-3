package com.example.bussinessanalsis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.bussinessanalsis.database.DataBaseConector;


public class EditActivity extends Activity implements OnClickListener {
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
	  
	 EditText fromDateEtxt,et_discription,et_tag,et_price,et_note,etNum1,etNum2;
	  Button btnAdd, btnSave,btnSub,btnMult,btnDiv,b1,clear,btn_income;
	Bundle extras;
	 Intent intent;
	 CheckBox chkSelect;
	    private DatePickerDialog fromDatePickerDialog;
	    
	    
	    private SimpleDateFormat dateFormatter;
	    
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit_item);
	      
	        btn_income=(Button)findViewById(R.id.btn_income);
	        
	        et_discription = (EditText) findViewById(R.id.editText_description);
		    et_tag = (EditText) findViewById(R.id.editText_tags);
		    et_price = (EditText) findViewById(R.id.editText_price);
		    et_note = (EditText) findViewById(R.id.editText_Note);
		    chkSelect=(CheckBox)findViewById(R.id.checkBox1);
		    sp=(Spinner) findViewById(R.id.spinner);
		    b1=(Button)findViewById(R.id.btn_expense);
		    clear=(Button)findViewById(R.id.btn_cancel);
		    btnSave=(Button)findViewById(R.id.btn_update);
		     dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
		      dbconnector=new DataBaseConector(this);
		
	        
		    findViewsById();
		    intent  = getIntent();
	        
	        if(intent!=null)
	        {
	        	/*et_discription = (EditText) findViewById(R.id.ed_discription);
			    et_tag = (EditText) findViewById(R.id.editText2);
			    et_price = (EditText) findViewById(R.id.editText3);
			    et_note = (EditText) findViewById(R.id.editText4);
			    fromDateEtxt = (EditText) findViewById(R.id.editText5);*/
	        	
	        	date=intent.getStringExtra("date");
	        	
	        	sppinervalue=intent.getStringExtra("item");
	        	Log.i("the values of sppinervalue",sppinervalue);
	        	discrption=intent.getStringExtra("Description");
	        	// discrption.replace("\n", ",");
	        	Log.i("the values of discrption",discrption);
	            price=intent.getStringExtra("price");
	          
	         //  price.replace("\n", ";");
	           Log.i("the values of price",price);
	            tag=intent.getStringExtra("tag");
	            Log.i("the values of price",tag);
	            String total=sppinervalue+","+discrption+","+price+","+tag;
	          
	        	/*price.replace("\n", ",");
	        	String to[]=price.split(",");
	        	price=to[0];
	        	Log.i("the intent Description =",price);
	        	;
	        */
	        	
	/*       String  	result = tag.replaceAll("\n", "<");
	        	
	       Log.i("the result ",result);
	        	
	        	String arr[]=result.split("<");
	        	
	        	price=arr[0];
	        	
	        	Log.i("the values of price",price);
	        	tag=arr[1];
	        	Log.i("the values of tag",tag);
               String prices[]=price.split(":");
	        	
               price=prices[1];
	        	Log.i("the values of price",price);
	        
	        	
	        	String arr1[]=discrption.split(":");
	        	
	        	discrption=arr1[1];
	        	Log.i("the values of discrption",discrption);
	        	
	        	String arr2[]=tag.split(":");
	        	tag=arr2[1];
	        	Log.i("the tag",tag);
	        	
	        	Log.i("the total values",date+sppinervalue+price+tag+discrption);
	        	*/
	     	    fromDateEtxt.setText(date);
	     	    String tot1[]=price.split(":");
	     	   price=tot1[1];
	     	 
	     	 /*  String tot13[]=price.split("\n");
	     	  price=tot13[0];*/
	         	et_discription.setText(price);
	        	
	        	String tot2[]=discrption.split(":");
	        	discrption=tot2[1];
	        	String tot12[]=discrption.split("T");
	        	discrption=tot12[0];
	        	discrption.trim();
	        	tag=tot2[2];
	        	et_tag.setText(tag);
	        	et_price.setText(discrption);
	        	
	        	Log.i("the intent values",date+sppinervalue+price+tag+discrption);
	        	 
	        	
	        }
	      
	        cal=(Button)findViewById(R.id.btn_cancel);
	        
	       
	     /*   etNum1 = (EditText) findViewById(R.id.etNum1);
		    etNum2 = (EditText) findViewById(R.id.etNum2);*/
		    
		    
		    btnSave.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				        extras = getIntent().getExtras();
				       
					//price,tag,note;
				        if(chkSelect.isChecked())
					    {
					    	Log.i("clead on check box","status cleared");
					    	status="Cleared";
					    }
					    else
					    {
					    	status="Pending";
					    }
				
					SharedPreferences prefs = getSharedPreferences("", MODE_PRIVATE); 
					String user_id = prefs.getString("userid", "");
					discrption=	et_discription.getText().toString();
					discrption = discrption.replace(" " , "");
					tag =	et_tag.getText().toString();
					price=	et_price.getText().toString();
					note=et_note.getText().toString();
					date=fromDateEtxt.getText().toString();
					sppinervalue=sp.getSelectedItem().toString();
				
					Log.i("the vlaues",discrption+"||"+price+"||"+tag+"||"+note+"||"+date+"||"+sppinervalue+"||"+user_id);
					ContentValues additems= new ContentValues();
					//(reg_userid text, discrption text, price text,tag text,note text,date text,sppinervalue text )";		
				//	additems.put("reg_userid", userid);
					additems.put("discrption", discrption);
					additems.put("price", price);
					additems.put("tag", tag);
					additems.put("note", note);
					additems.put("date", date);
					additems.put("status", status);
					Log.i("the total value",additems.toString());
				   int result	=dbconnector.update_items(additems, sppinervalue, user_id);
				  if(result==1)
				 {
					Intent i = new Intent(EditActivity.this,ViewItemsList.class);
					startActivity(i);
				 }
			    
				}
			}) ;

		  
	        cal.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					LayoutInflater li = LayoutInflater.from(getBaseContext());
					View promptsView = li.inflate(R.layout.calc, null);
					/* btnAdd = (Button) findViewById(R.id.btnAdd);
					    btnSub = (Button) findViewById(R.id.btnSub);
					    btnMult = (Button) findViewById(R.id.btnMult);
					    btnDiv = (Button) findViewById(R.id.btnDiv);*/
					    
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							EditActivity.this);
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
	        list.add("Eating Out");
	        list.add("Entertainment");
	        list.add("Gifts");
	        list.add("Groceries");
	        list.add("Insurance");
	        list.add(" Medical");
	        list.add(" Payment");
	        list.add("Rent");
	        list.add("Salary");
	        list.add("Shopping");
	        list.add("Transfer");
	        list.add("Transportation");
	        list.add("Utilities");
	        
	       
	       b1.setTextColor( Color.RED );
	       
	        b1.setOnClickListener( new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	               
	            	btn_income.setVisibility(View.VISIBLE);
	            	b1.setVisibility(View.INVISIBLE);
	            	btn_income.setTextColor(Color.GREEN );
	            }
	        });
	        btn_income.setOnClickListener( new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	b1.setVisibility(View.VISIBLE);
	            	btn_income.setVisibility(View.INVISIBLE);
	            	b1.setTextColor(Color.RED);
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
	 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(EditActivity.this);
	 
				// set title
				alertDialogBuilder.setTitle("Do You Want To Cancel");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Cancel form?")
					.setCancelable(false)
					.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
						//	clear();
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
	        fromDateEtxt = (EditText) findViewById(R.id.editText_date);    
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
	    /*private void clear() {
	    	fromDateEtxt.setText(" ");
	    	et_discription.setText("");
	    	et_tag.setText(" ");
	    	et_price.setText(" ");
	    	et_note.setText(""); 
		}
	   */
	    @Override
	    public void onClick(View view) {
	        if(view == fromDateEtxt) {
	            fromDatePickerDialog.show();
	        
	  	 
	    }  
	    } 
	}