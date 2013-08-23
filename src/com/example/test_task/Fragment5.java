package com.example.test_task;
/*
 Forma s otpravkoy dannih v json formate,
 realizovana prostaya validaciya dannih
 */


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class Fragment5 extends Fragment {

	  final String LOG_TAG = "myLogs";
	  DialogFragment dlg1;
	  EditText etName;
	  EditText etEmail;
	  EditText etPass;
	  EditText etCPass;
	 

	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		  
		  View v = inflater.inflate(R.layout.fragment5, null);
		  Button button = (Button) v.findViewById(R.id.button);
		  etName = (EditText) v.findViewById(R.id.etName);
		  etEmail = (EditText) v.findViewById(R.id.etEmail);
		  etPass = (EditText) v.findViewById(R.id.etPass);
		  etCPass = (EditText) v.findViewById(R.id.etCPass);
	      //set listener to button
		  button.setOnClickListener(new OnClickListener() {
			  public void onClick(View v) {
				  Log.d(LOG_TAG, "Button 'Submit' click in Tab5");
				  // simple input data validation
	              if (etName.getText().toString().length()<3){
	            	  dlg1 = new Dialog1("Name too short. Must be 3 symbols at least.");
	            	  dlg1.show(getFragmentManager(), "dlg1");
	            	  return;
	              }
	              if ((etEmail.getText().toString().indexOf('@')<0)||((etEmail.getText().toString().length()<5)||
	            	  etEmail.getText().toString().indexOf('.')<0))	{
	            	  dlg1 = new Dialog1("Invalid E-mail address");
	            	  dlg1.show(getFragmentManager(), "dlg1");
	            	  return;
	              }
	              if (etPass.getText().toString().length()<6){
	            	  dlg1 = new Dialog1("Password too short. Must be 6 symbols at least.");
	            	  dlg1.show(getFragmentManager(), "dlg1");
	            	  return;
	              }
	              if (!etPass.getText().toString().equals(etCPass.getText().toString())){
	            	  dlg1 = new Dialog1("Confirm password correctly.");
	            	  dlg1.show(getFragmentManager(), "dlg1");
	            	  return;
	              }
	        
	              //Try to format and send json data
	              Log.d(LOG_TAG, "Validation passed, try to format and send json data");
	              String value1 = etPass.getText().toString();
	              String value2 = etEmail.getText().toString();
	              String value3 = etPass.getText().toString();
	              HttpClient httpClient = new DefaultHttpClient();
	              try {
	            	  HttpPost request = new HttpPost("http://www.ya.ru");
	            	  StringEntity params =new StringEntity("{\"name\":"+value1+",\"email\":"+value2+",\"pass\":"+value3+"} ");
	            	  request.addHeader("content-type", "application/x-www-form-urlencoded");
	            	  request.setEntity(params);
	            	  //  HttpResponse response = httpClient.execute(request);
	              }catch (Exception e) {
	            	  e.printStackTrace();
	            	  Log.d(LOG_TAG,"exception caught "+e);
	              } finally {
	            	  httpClient.getConnectionManager().shutdown();
	              }
  
	              dlg1 = new Dialog1("Data send.");
	              dlg1.show(getFragmentManager(), "dlg1");
			  }
	    });
	    
	    return v;
	  }
}
