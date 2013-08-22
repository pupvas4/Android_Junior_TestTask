package com.example.test_task;
/*
 Cписок с загрузкой данных напр€мую с сервера 
 (¬ качестве данных - начало web страницы, полученной 
 по HTTP запросу www.google.com.ua). ѕервые четыре "слова"
 (набор символов, разделенных пробелами)будем считать нашими данными.
 */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



public class Fragment2 extends ListFragment {
	final String LOG_TAG = "myLogs";
	//default listFragment data. Try to change it
	String[] str_data = new String[]{ "one", "two", "three", "four" };
	
	
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
	    View v =  inflater.inflate(R.layout.fragment2, null);
	    /*start new thread for receiving data from http server(we have no choice).
	    First 4 "words" (symbols separated by space) of it will be our data. 
	    Put it to ListFragment.
	    */
	    try{
	    	Thread th = new thread();
	    	th.start(); 
	    	th.join();//wait for receiving data
	    }catch (InterruptedException e){
	    	e.printStackTrace();
	    	Log.d(LOG_TAG,"exception caught "+e);
	    }
	    return v; 
	  }
	  
	  
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    
	  }
	  public void onDestroy() {
		    super.onDestroy();
		    //try to stop our thread
		    Log.d(LOG_TAG, "Fragment2 onDestroy");
		  }
 
	    // New Thread for working with HTTP 
		public class thread extends Thread{
	    @Override
	    public void run() {
	        try {
	        	Log.d(LOG_TAG,"New Thread starting....");
	        	URL url;
	        	url= new URL("http://www.google.com.ua");HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		        InputStream in = urlConnection.getInputStream();
		        Log.d(LOG_TAG,"getInputStream");
		        InputStreamReader isw = new InputStreamReader(in);
		        Log.d(LOG_TAG,"InputStreamReader");
		        //read from stream of format our 4 "words"
		        int data = isw.read();
		        String result = "";
		        int i = 0;
		        while (data != -1) {
		            char current = (char) data;
		            result+=current;
		            if (current==' ') {
		            	if (i==4) break;
		            	str_data[i]=result;
		            	Log.d(LOG_TAG, result);
		            	result="";
		            	i++;
		            }
		            data = isw.read();
		        }
		        isw.close();
		        Log.d(LOG_TAG,"isw.close()");
		        //Put our words into ListFragment
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		    	android.R.layout.simple_list_item_1, str_data);
		    	setListAdapter(adapter);
		    	
	        } catch (Exception e) {
	            e.printStackTrace();
	            Log.d(LOG_TAG,"exception caught "+e);
	        }
	    }
	};

	
	
	}