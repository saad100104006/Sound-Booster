package com.pregnancy.soundbooster;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivitytest extends Activity {

   private Button modeBtn;
   private Button increaseBtn;
   private Button decreaseBtn;
   private RadioButton normal;
   private RadioButton silent;
   private RadioGroup ringGroup;
   private TextView status;
   private AudioManager myAudioManager;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main2);

      normal = (RadioButton) findViewById(R.id.radioNormal);
      silent = (RadioButton) findViewById(R.id.radioSilent);
      status = (TextView) findViewById(R.id.text);
      ringGroup = (RadioGroup) findViewById(R.id.radioRinger);
      
      modeBtn = (Button)findViewById(R.id.mode);
      modeBtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int selectedId = ringGroup.getCheckedRadioButtonId();
			 
			// find which radioButton is checked by id
		        if(selectedId == silent.getId()) {
		        	silentEnable(v);
		        } else if(selectedId == normal.getId()) {
		        	 normalEnable(v);
		        } else {
		        	vibrateEnable(v);
		        }	
		}
	  });
      
      increaseBtn = (Button) findViewById(R.id.increase);
      increaseBtn.setOnClickListener(new OnClickListener() {
  		
  		@Override
  		public void onClick(View v) {
  			// increase the volume and show the ui
  			 myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
  			Toast.makeText(getApplicationContext(), "increase volume", 
 					 Toast.LENGTH_SHORT).show();
  		}
  	  });
      
      decreaseBtn = (Button) findViewById(R.id.decrease);
      decreaseBtn.setOnClickListener(new OnClickListener() {
  		
  		@Override
  		public void onClick(View v) {
  			// decrease the volume and show the ui
  			 myAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
  			 Toast.makeText(getApplicationContext(), "decrease volume", 
  					 Toast.LENGTH_SHORT).show();
  		}
  	  });
      // get the instance of AudioManager class
      myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

   }

   public void vibrateEnable(View view){
	   // set the ring mode to vibrate
       myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
       status.setText("Current Status: Vibrate Mode");
   }
   public void normalEnable(View view){
	  // set the ring mode to loud
      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
      status.setText("Current Status: Ring Mode");
   }
   public void silentEnable(View view){
	  // set the ring mode to silent
      myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
      status.setText("Current Status: Silent Mode");
   }
}
