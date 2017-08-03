package com.pregnancy.soundbooster;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    ToggleButton t1, t2, t3;
    ImageView imageView1, imageView2, imageView3;
    TextView percents;
    int flag = 0;
    int flag2 = 0;
    int flag3 = 0;
    MediaPlayer mp = null;
    Singleton m_Inst = Singleton.getInstance();
    private AudioManager myAudioManager;
    int tempPer = 2;

    // AudioManager mobilemode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (ToggleButton) findViewById(R.id.toggle1);
        t2 = (ToggleButton) findViewById(R.id.toggle2);
        t3 = (ToggleButton) findViewById(R.id.toggle3);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        percents = (TextView) findViewById(R.id.text);


       /* NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(n.isNotificationPolicyAccessGranted()) {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else{
            // Ask the user to grant access
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }*/


        //  mobilemode = (AudioManager)this.getSystemService(this.AUDIO_SERVICE);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);





        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (flag == 0) {
                    imageView1.setImageResource(R.drawable.musical_on);
                    // managerOfSound();

                    flag = 1;
                } else {
                    imageView1.setImageResource(R.drawable.musical_off);

                    flag = 0;
                }


            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (flag2 == 0) {

                    imageView2.setImageResource(R.drawable.telephone_on);
                    // managerOfSound();
                    flag2 = 1;
                } else {
                    imageView2.setImageResource(R.drawable.telephone_off);
                    flag2 = 0;
                }


            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (flag3 == 0) {

                    imageView3.setImageResource(R.drawable.alarm_on);
                    // managerOfSound();
                    flag3 = 1;
                } else {
                    imageView3.setImageResource(R.drawable.alarm_off);
                    flag3 = 0;
                }


            }
        });


        // Scaling mechanism, as explained on:
        // http://www.pocketmagic.net/2013/04/how-to-scale-an-android-ui-on-multiple-screens/
        m_Inst.InitGUIFrame(this);

        //  RelativeLayout panel = new RelativeLayout(this);

        RelativeLayout panel = (RelativeLayout) findViewById(R.id.top);
        setContentView(panel);

        TextView tv = new TextView(this);
        tv.setText("Rotary knob control\nRadu Motisan 2013\nwww.pocketmagic.net");
        tv.setGravity(Gravity.CENTER);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        panel.addView(tv, lp);

        //final TextView tv2 = new TextView(this); tv2.setText("");
        percents.setText("");
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        //percents.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //  panel.addView(percents);
        if (percents.getParent() != null)
            ((ViewGroup) percents.getParent()).removeView(percents); // <- fix
        panel.addView(percents, lp);


        RoundKnobButton rv = new RoundKnobButton(this, R.drawable.stator, R.drawable.rotoron, R.drawable.rotoroff,
                m_Inst.Scale(350), m_Inst.Scale(350));
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv, lp);

        rv.setRotorPercentage(0);
        rv.SetListener(new RoundKnobButton.RoundKnobButtonListener() {
            public void onStateChange(boolean newstate) {
                Toast.makeText(MainActivity.this, "New state:" + newstate, Toast.LENGTH_SHORT).show();
            }

            public void onRotate(final int percentage) {
                percents.post(new Runnable() {
                    public void run() {
                        percents.setText("\n" + percentage + "%\n");

                      //  setVolumeControlStream(AudioManager.STREAM_ALARM);
                        if (percentage > tempPer) {

                            //myAudioManager.setStreamVolume(AudioManager.STREAM_RING,AudioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);

                            myAudioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

                            myAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, percentage/10, 0);
                            myAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, percentage/10, 0);
                          //  myAudioManager.setStreamVolume(AudioManager.STREAM_RING, percentage/10, 0);

                        }
                        else if(percentage<tempPer){


                            myAudioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);

                            myAudioManager.setStreamVolume(AudioManager.STREAM_ALARM, percentage/10, 0);
                            myAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, percentage/10, 0);
                           // myAudioManager.setStreamVolume(AudioManager.STREAM_RING, percentage/10, 0);

                        }
                        //

                        // String temp = percents.getText().toString();
                        //  temp = temp.replace("%", "");
                        //  temp = temp.replace(" ", "");

                        // mobilemode.setStreamVolume(AudioManager.STREAM_MUSIC, percentage, 0);

                        tempPer = percentage;
                    }
                });
            }
        });


        //  AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


/*
        AudioManager mobilemode = (AudioManager)this.getSystemService(this.AUDIO_SERVICE);
//   int streamMaxVolume = mobilemode.getStreamMaxVolume(AudioManager.STREAM_RING);
        switch (mobilemode.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                Log.i("MyApp","Silent mode");

                mobilemode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                mobilemode.setStreamVolume(AudioManager.STREAM_RING,mobilemode.getStreamMaxVolume(AudioManager.STREAM_RING),0);
             //   mobilemode.setStreamVolume (AudioManager.STREAM_MUSIC,mobilemode.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                Log.i("MyApp","Vibrate mode");
                mobilemode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                mobilemode.setStreamVolume (AudioManager.STREAM_MUSIC,mobilemode.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                Log.i("MyApp","Normal mode");
                break;
        }*/


    }

    protected void managerOfSound() {
        if (mp != null) {
            mp.reset();
            mp.release();
        }

        mp.start();
    }
}
