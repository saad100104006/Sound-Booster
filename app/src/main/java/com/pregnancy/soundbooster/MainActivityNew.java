package com.pregnancy.soundbooster;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivityNew extends Activity {

	SeekBar alarm, mediaPlayer, ringer, notification ;
	AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        
        alarm = (SeekBar)findViewById(R.id.seekBar1);
        mediaPlayer = (SeekBar)findViewById(R.id.seekBar2);
        ringer = (SeekBar)findViewById(R.id.seekBar3);
        notification = (SeekBar)findViewById(R.id.seekBar4);
        
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        
        alarm.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM));
        
        mediaPlayer.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        
        ringer.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
        
        notification.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION));
        
        
     
        alarm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
       	 @Override
       	 public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
 
       		 audioManager.setStreamVolume(AudioManager.STREAM_ALARM, i, 0);
       	 }

       	 @Override
       	 public void onStartTrackingTouch(SeekBar seekBar) {

       	 }

       	 @Override
       	 public void onStopTrackingTouch(SeekBar seekBar) {

       	 }
       	 });
        
        
        mediaPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          	 @Override
          	 public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    
          		 audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
          	 }

          	 @Override
          	 public void onStartTrackingTouch(SeekBar seekBar) {

          	 }

          	 @Override
          	 public void onStopTrackingTouch(SeekBar seekBar) {

          	 }
          	 });
        
        
        ringer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          	 @Override
          	 public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    
          		 audioManager.setStreamVolume(AudioManager.STREAM_RING, i, 0);
          	 }

          	 @Override
          	 public void onStartTrackingTouch(SeekBar seekBar) {

          	 }

          	 @Override
          	 public void onStopTrackingTouch(SeekBar seekBar) {

          	 }
          	 });
        
        
        notification.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          	 @Override
           	 public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
     
           		 audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, i, 0);
           	 }

           	 @Override
           	 public void onStartTrackingTouch(SeekBar seekBar) {

           	 }

           	 @Override
           	 public void onStopTrackingTouch(SeekBar seekBar) {

           	 }
           	 });
    }
}
