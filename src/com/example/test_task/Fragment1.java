package com.example.test_task;
/*
 Cписок с загрузкой данных с сервера через SQL базу.
 */
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class Fragment1 extends ListFragment {
	
  String data[] = new String[] { "one", "two", "three", "four" };
  
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
	  View v = inflater.inflate(R.layout.fragment1, null);
	  
	  
	  return v;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_1, data);
    setListAdapter(adapter);
  }
  
}