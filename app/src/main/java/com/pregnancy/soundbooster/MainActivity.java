package com.pregnancy.soundbooster;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    ToggleButton t1,t2,t3;
    ImageView imageView1,imageView2,imageView3;
    TextView percents;
    int flag=0;
    int flag2=0;
    int flag3=0;
    MediaPlayer mp        = null;
    Singleton m_Inst = Singleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(ToggleButton)findViewById(R.id.toggle1);
        t2=(ToggleButton)findViewById(R.id.toggle2);
        t3=(ToggleButton)findViewById(R.id.toggle3);
        imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        percents=(TextView)findViewById(R.id.text);



        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(flag==0){
                    imageView1.setImageResource(R.drawable.musical_on);
                   // managerOfSound();

                    flag=1;
                }
                else{
                    imageView1.setImageResource(R.drawable.musical_off);

                    flag=0;
                }




            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(flag2==0){

                    imageView2.setImageResource(R.drawable.telephone_on);
                   // managerOfSound();
                    flag2=1;
                }
                else{
                    imageView2.setImageResource(R.drawable.telephone_off);
                    flag2=0;
                }




            }
        });

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(flag3==0){

                    imageView3.setImageResource(R.drawable.alarm_on);
                   // managerOfSound();
                    flag3=1;
                }
                else{
                    imageView3.setImageResource(R.drawable.alarm_off);
                    flag3=0;
                }




            }
        });


        // Scaling mechanism, as explained on:
        // http://www.pocketmagic.net/2013/04/how-to-scale-an-android-ui-on-multiple-screens/
        m_Inst.InitGUIFrame(this);

      //  RelativeLayout panel = new RelativeLayout(this);

        RelativeLayout panel = (RelativeLayout)findViewById(R.id.top);
        setContentView(panel);

        TextView tv = new TextView(this); tv.setText("Rotary knob control\nRadu Motisan 2013\nwww.pocketmagic.net"); tv.setGravity(Gravity.CENTER);
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
        if(percents.getParent()!=null)
            ((ViewGroup)percents.getParent()).removeView(percents); // <- fix
        panel.addView(percents,lp);



        RoundKnobButton rv = new RoundKnobButton(this, R.drawable.stator, R.drawable.rotoron, R.drawable.rotoroff,
                m_Inst.Scale(250), m_Inst.Scale(250));
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv, lp);

        rv.setRotorPercentage(100);
        rv.SetListener(new RoundKnobButton.RoundKnobButtonListener() {
            public void onStateChange(boolean newstate) {
                Toast.makeText(MainActivity.this,  "New state:"+newstate,  Toast.LENGTH_SHORT).show();
            }

            public void onRotate(final int percentage) {
                percents.post(new Runnable() {
                    public void run() {
                        percents.setText("\n" + percentage + "%\n");
                    }
                });
            }
        });









    }

    protected void managerOfSound() {
        if (mp != null) {
            mp.reset();
            mp.release();
        }

        mp.start();
    }
}
