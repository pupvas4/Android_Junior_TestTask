package com.example.test_task;

/*Spisok s zagruzkoy  dannih napramuu iz bazi SQL
 * (DB SQLite sozdaetsa i 4itaetsa v metode onCreate())
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class Fragment3 extends ListFragment {
	String data[] = new String[] { "one", "two", "three", "four" };
	final String LOG_TAG = "myLogs";
  DBHelper dbHelper;
  
  
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		  	View v = inflater.inflate(R.layout.fragment3, null);
		  	Log.d(LOG_TAG, "onCreateView-Fragment3" );
		  	
   //create and fil SQLight DB
   //DB data: 1-Ivanov,2-Petrov,3-Sidorov,4-Abramovich
		  	
		  	//Create Helper. Use getActivity() - we in fragment!
		  	dbHelper = new DBHelper(getActivity());
		    Log.d(LOG_TAG, "getActivity:  "+getActivity() );
		  	// create object for data, 
		    ContentValues cv = new ContentValues();
	  	    // Use DB
		    SQLiteDatabase db = dbHelper.getWritableDatabase();
		    Log.d(LOG_TAG, "SQLiteDatabase db:  "+db );
		    // put data in pairs: key-value. Get ID
	        Log.d(LOG_TAG, "--- Insert in mytable: ---");
		      	cv.put("number", "0");
		      	cv.put("name", "Ivanov");
		      	long rowID = db.insert("mytable", null, cv);
		      	Log.d(LOG_TAG, "row inserted, ID = " + rowID);
		      
		      	cv.put("number", "1");
		      	cv.put("name", "Petrov");
		      	rowID= db.insert("mytable", null, cv);
		      	Log.d(LOG_TAG, "row inserted, ID = " + rowID);
		      
		      	cv.put("number", "2");
		      	cv.put("name", "Sidorov");
		      	rowID= db.insert("mytable", null, cv);
		      	Log.d(LOG_TAG, "row inserted, ID = " + rowID);
		      
		      	cv.put("number", "3");
		      	cv.put("name", "Abramovich");
		      	rowID= db.insert("mytable", null, cv);
		      	Log.d(LOG_TAG, "row inserted, ID = " + rowID);
		      
		      
   //Output new DB in ListFragment and in LOG		  
		      Log.d(LOG_TAG, "--- Rows in mytable: ---");
		      //Make query for all data in mytable, receive Cursor
		      Cursor c = db.query("mytable", null, null, null, null, null, null);
		      //Put Cursor on firt string
		      //If there are not strings return false
		      if (c.moveToFirst()) {
		    	  //Get columns index from name
		    	  int idColIndex = c.getColumnIndex("id");
		    	  int numberColIndex = c.getColumnIndex("number");
		    	  int nameColIndex = c.getColumnIndex("name");
		    	  do {
		    		  // Put data from DB in our array.
		    		  data[Integer.parseInt(c.getString(numberColIndex))]=c.getString(nameColIndex);
		    		  Log.d(LOG_TAG,
		    				  "ID = " + c.getInt(idColIndex) + 
		    				  ", number = " + c.getString(numberColIndex) + 
		    				  ", name = " + c.getString(nameColIndex));
		    	  } while (c.moveToNext());
		      } else
		      Log.d(LOG_TAG, "0 rows");
		      c.close();
		      
		      //Clear DB on exit.
		      Log.d(LOG_TAG, "--- Clear mytable: ---");
		      int clearCount = db.delete("mytable", null, null);
		      Log.d(LOG_TAG, "deleted rows count = " + clearCount);
		      
		      dbHelper.close();  
		      
		  return v;
	  }
	
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState) {
		  	super.onActivityCreated(savedInstanceState);
		  	Log.d(LOG_TAG, "onActivityCreated");
		  	//Put data to ListFragment
		  	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		  			android.R.layout.simple_list_item_1, data);
		  	setListAdapter(adapter);
	  }
	  
	  class DBHelper extends SQLiteOpenHelper {

		    public DBHelper(Context context) {
		      super(context, "myDB1", null, 1);
		    }

		    @Override
		    public void onCreate(SQLiteDatabase db) {
		      Log.d(LOG_TAG, "--- onCreate database ---");
		      // Create DB Table (executed one time!)
		      db.execSQL("create table mytable ("
		          + "id integer primary key autoincrement," 
		          + "number text,"
		          + "name text" + ");");
		    }

			@Override
			public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

	}
}