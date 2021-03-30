package com.example.android.health;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
 boolean running =false;
  SensorManager sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        running=true;

        sb=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        // Set the content of the activity to use the activity_main.xml layout file

    }



    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor sns=null;
        if(sb!=null) {
            Toast.makeText(this,"sb is not null",Toast.LENGTH_SHORT).show();
            sns= sb.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        if( sns==null){
            //Toast.makeText(this,"sns is null",Toast.LENGTH_SHORT).show();
        }
        else{
            sb.registerListener(this, sns,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        running=true;
        Sensor sns=null;
        if(sb!=null) {
            Toast.makeText(this,"sb is not null",Toast.LENGTH_SHORT).show();
            sns= sb.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }
        if( sns==null){
            //Toast.makeText(this,"sns is null",Toast.LENGTH_SHORT).show();
        }
        else{
            sb.registerListener(this, sns,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(running) {
            running=false;
            sb.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
if (running) {
    TextView tt=(TextView)findViewById(R.id.Value);
    if(event.values!=null)
    tt.setText(""+event.values[0]);
}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}