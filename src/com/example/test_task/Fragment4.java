package com.example.test_task;
/*
Загружаетстя mp3 файл и воспроизводится.
http://dl.dropboxusercontent.com/u/6197740/explosion.mp3

 */
import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;



public class Fragment4 extends Fragment implements OnPreparedListener,
OnCompletionListener, OnClickListener {

	  final String LOG_TAG = "myLogs";
	  EditText etPath;
	  String DATA_HTTP;
	  MediaPlayer mediaPlayer;
	  AudioManager am;
	  CheckBox chbLoop;
	
	
	@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
		 View v = inflater.inflate(R.layout.fragment4, null);
         etPath=(EditText) v.findViewById(R.id.editText1);
		 DATA_HTTP = etPath.getText().toString() ;
		 Button play_btn = (Button) v.findViewById(R.id.play_btn);
		 Button pause_btn = (Button) v.findViewById(R.id.pause_btn);
		 play_btn.setOnClickListener(this);
		 pause_btn.setOnClickListener(this);
		 return v;
  }
	
	@Override
	public void onClick(View v) {
		Log.d(LOG_TAG, "OnClick method in Fragment4");
		releaseMP();
		try{
			switch (v.getId()) {
			case R.id.play_btn:
				Log.d(LOG_TAG, "Play btn click");
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(DATA_HTTP);
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				Log.d(LOG_TAG, "prepareAsync");
				mediaPlayer.setOnPreparedListener(this);
				mediaPlayer.prepareAsync();
				break;
			case R.id.pause_btn:
				Log.d(LOG_TAG, "Pause btn click");
				if (mediaPlayer == null) break;
				if (mediaPlayer.isPlaying())
					mediaPlayer.pause();
				else mediaPlayer.start();
				break;
			}

			if (mediaPlayer == null) return;
			mediaPlayer.setLooping(false);
			mediaPlayer.setOnCompletionListener(this);
		}catch (IOException e){
			e.printStackTrace();
			Log.d(LOG_TAG, "Exception caught: "+e);
		}
	}


	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "onComplition");
	}


	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "onPrepared");
	    mp.start();
		
	}
  
	
	private void releaseMP() {
	    if (mediaPlayer != null) {
	      try {
	        mediaPlayer.release();
	        mediaPlayer = null;
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    }
	  }
	

	
}