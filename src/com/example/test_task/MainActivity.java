package com.example.test_task;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends FragmentActivity {

	  Fragment1 frag1;
	  Fragment2 frag2;
	  Fragment3 frag3;
	  Fragment4 frag4;
	  Fragment5 frag5;
	  FragmentTransaction fTrans;
	  OnClickListener radioListener;// listener
	  final String LOG_TAG = "myLogs";
	
    
    
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(LOG_TAG, "Main activity onCreate");
        frag1 = new Fragment1();
        frag2 = new Fragment2();
        frag3 = new Fragment3();
        frag4 = new Fragment4();
        frag5 = new Fragment5();
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.rg_1);
        //create and show first TAB(frafment1)
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, frag1);
        fTrans.commit();
        
        //set listener on radiogroup
    	radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    		
    		@Override
    		public void onCheckedChanged(RadioGroup group, int checkedId) {
    			fTrans = getSupportFragmentManager().beginTransaction();
    			//try to imitate tab switching with colors - background tabs grey.
    			RadioButton rbT1 = (RadioButton) findViewById(R.id.T1);
    			RadioButton rbT2 = (RadioButton) findViewById(R.id.T2);
    			RadioButton rbT3 = (RadioButton) findViewById(R.id.T3);
    			RadioButton rbT4 = (RadioButton) findViewById(R.id.T4);
    			RadioButton rbT5 = (RadioButton) findViewById(R.id.T5);
    			
    			switch (checkedId) {
      			case R.id.T1:
    				Log.d(LOG_TAG, "TAB1 checked");
    				rbT1.setBackgroundColor(0x000000);
    				rbT2.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT3.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT4.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT5.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				fTrans.replace(R.id.frgmCont, frag1);
     				break;
    			case R.id.T2:
    				Log.d(LOG_TAG, "TAB2 checked");
    				rbT1.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT2.setBackgroundColor(0x000000);
    				rbT3.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT4.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT5.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				fTrans.replace(R.id.frgmCont, frag2);    			
     				break;
    			case R.id.T3:
    				Log.d(LOG_TAG, "TAB3 checked");
    				rbT1.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT2.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT3.setBackgroundColor(0x000000);
    				rbT4.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT5.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				fTrans.replace(R.id.frgmCont, frag3);
    				break;
    			case R.id.T4:
    				Log.d(LOG_TAG, "#TAB4 checked");
    				rbT1.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT2.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT3.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT4.setBackgroundColor(0x000000);
    				rbT5.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				fTrans.replace(R.id.frgmCont, frag4);
       				break;
    			case R.id.T5:
    				Log.d(LOG_TAG, "TAB5 checked");
    				rbT1.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT2.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT3.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT4.setBackgroundColor(Color.parseColor("#D5D1D2"));
    				rbT5.setBackgroundColor(0x000000);
    				fTrans.replace(R.id.frgmCont, frag5);
       				break;

    			default:
    				break;
    			}
    			fTrans.commit();
    		}
    	});
    
    }
   
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



	
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Main Activity - onDestroy");
      }
    
}