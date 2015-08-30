package com.keepsearching.teststopwatch;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;


import android.os.SystemClock;

import android.app.Activity;
import android.graphics.Color;

import android.view.View;
import android.view.View.OnClickListener;

public class StopwatchActivity extends AppCompatActivity {

    Button butnstart, butnreset;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int hrs = 0;
    int milliseconds = 0;
    Handler handler = new Handler();
    TextView mill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);


        butnstart = (Button)findViewById(R.id.start);
        butnreset = (Button)findViewById(R.id.reset);
        time = (TextView) findViewById(R.id.timer);
        mill = (TextView) findViewById(R.id.milli);
        Typeface font = Typeface.createFromAsset(getAssets(),"digital-7.ttf");
        time.setTypeface(font);
        mill.setTypeface(font);


        butnstart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub

                if (t == 1) {
                    butnstart.setText("Pause");
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
                    butnstart.setText("Start");
                    time.setTextColor(Color.BLUE);
                    mill.setTextColor(Color.BLUE);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }}
        });



        butnreset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                starttime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedtime = 0L;
                t = 1;
                secs = 0;
                mins = 0;
                milliseconds = 0;
                butnstart.setText("Start");
                handler.removeCallbacks(updateTimer);
                time.setText("00:00:00");
                mill.setText(":00");
            }});


    }


    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            hrs = mins/60;
            mins = mins%60;
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000 -1);
            time.setText(String.format("%02d",hrs)+":" + String.format("%02d",mins) + ":" + String.format("%02d", secs));
                    //+ ":"
                    //+ String.format("%03d", milliseconds));
            mill.setText(String.format(":"+"%03d",milliseconds));
            time.setTextColor(Color.RED);
            mill.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }};






    @Override
         public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stopwatch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
