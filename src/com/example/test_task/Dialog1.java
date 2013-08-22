package com.example.test_task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;



public class Dialog1 extends DialogFragment implements OnClickListener, android.content.DialogInterface.OnClickListener {

  final String LOG_TAG = "myLogs";
  String message_text ;
 
  public Dialog1 (String message_text ){
	  this.message_text=message_text;
  }


public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
        .setTitle("Info!").setPositiveButton(R.string.ok, this)
        .setMessage(message_text);
    return adb.create();
  }

  public void onClick(DialogInterface dialog, int which) {
    int i = 0;
    switch (which) {
    case Dialog.BUTTON_POSITIVE:
      i = R.string.ok;
      break;
    default:
    	break;
    }
    if (i > 0)
      Log.d(LOG_TAG, "Dialog 1: " + getResources().getString(i));
  }

  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    Log.d(LOG_TAG, "Dialog 1: onDismiss");
  }

  public void onCancel(DialogInterface dialog) {
    super.onCancel(dialog);
    Log.d(LOG_TAG, "Dialog 1: onCancel");
  }

@Override
public void onClick(View arg0) {
	// TODO Auto-generated method stub
	
}
}