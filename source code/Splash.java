package com.example.bussinessanalsis;



import com.example.bussinessanalsis.database.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.widget.ImageView;

public class Splash
	extends Activity {  
		public static Context context;
		Intent in;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			
			setContentView(R.layout.splash);
			
			
			
			 Thread logoTimer = new Thread() {
		            public void run(){
		                try{
		                    int logoTimer = 0;
		                    while(logoTimer < 5000){
		                        sleep(100);
		                        logoTimer = logoTimer +100;
		                    };  
		                    in = new Intent(getApplicationContext(),Login.class);
							startActivity(in);
		                  
		                } 
		                 
		                catch (InterruptedException e) {
		                    // TODO Auto-generated catch block
		                    e.printStackTrace();
		                }
		                 
		                finally{
		                    finish();
		                }
		            }
		        };
		         
		        logoTimer.start();
		    }

		

	}