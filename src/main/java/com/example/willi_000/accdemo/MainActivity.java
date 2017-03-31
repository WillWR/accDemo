package com.example.willi_000.accdemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Sensor accelerometer;
    SensorManager sm;
    TextView acceleration;

    int m1state=0;
    int m2state=0;
    int m3state=0;
    int m4state=0;
    int m5state=0;

    Button m1Open;
    Button m1Close;
    Button m2Down;
    Button m2Up;
    Button m5Left;
    Button m5Right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        acceleration=(TextView)findViewById(R.id.accelerometer);
        //BUTTONS
        //M1Open
        m1Open = (Button)findViewById(R.id.m1Close);
        m1Open.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    m1Open.setText("OPEN");
                    m1state=0;
                    return true;
                }
                m1Open.setText("HOLDING");
                m1state=1;
                return false;
            }
        });
        //M1Close
        m1Close = (Button)findViewById(R.id.m1Open);
        m1Close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    m1Close.setText("CLOSE");
                    m1state=0;
                    return true;
                }
                m1Close.setText("HOLDING");
                m1state=2;
                return false;
            }
        });
        //M2Down
        m2Down = (Button)findViewById(R.id.m2Down);
        m2Down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    m2Down.setText("DOWN");
                    m2state=0;
                    return true;
                }
                m2Down.setText("HOLDLING");
                m2state=1;
                return false;
            }
        });
        //M2UP
        m2Up=(Button)findViewById(R.id.m2Up);
        m2Up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    m2Up.setText("UP");
                    m2state=0;
                    return true;
                }
                m2Up.setText("HOLDING");
                m2state=2;
                return false;
            }
        });
        //M5left
        m5Left=(Button)findViewById(R.id.m5Left);
        m5Left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    m5Left.setText("LEFT");
                    m5state=0;
                    return true;
                }
                m5Left.setText("HOLDING");
                m5state=1;
                return false;
            }
        });
        //M5Right
        m5Right=(Button)findViewById(R.id.m5Right);
        m5Right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_UP){
                    m5Right.setText("RIGHT");
                    m5state=0;
                    return true;
                }
                m5Right.setText("HOLDING");
                m5state=2;
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onSensorChanged(SensorEvent event) {

        if((event.values[0]>=-4)&&(event.values[0]<=4)){
            m3state=0;
        }
        else if(event.values[0]<-4){
            m3state=1;
        }
        else if(event.values[0]>4){
            m3state=2;
        }

        if((event.values[1]>=-4)&&(event.values[1]<=4)){
            m4state=0;
        }
        else if(event.values[1]<-4){
            m4state=2;
        }
        else if(event.values[1]>4){
            m4state=1;
        }
        acceleration.setText("X: " +m3state+
                            "\nY: "+m4state+
                            "\nClamp: "+m1state+
                            "\nWrist: "+m2state+
                            "\nBase: "+m5state);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
