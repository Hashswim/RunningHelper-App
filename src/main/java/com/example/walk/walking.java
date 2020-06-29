package com.example.walk;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Locale;

public class walking extends AppCompatActivity implements SensorEventListener {

        private SensorManager sensorManager;
        private Sensor stepCountSensor;
        TextView StepCount;
        landmark landmark;
        basicfragment basicfragment;
        gpsinfo gpsinfo;
        FragmentManager fm;
        FragmentTransaction tran;
        Bundle bundle;
        int  in;
        float counter, raw;
        TextToSpeech tts;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.walking);

            in = 0;
            counter = 0;
            raw = 0;
            bundle = new Bundle();
            basicfragment = new basicfragment();
            landmark = new landmark();
            gpsinfo = new gpsinfo();

            onFragmentChanged(0);

            StepCount = (TextView)findViewById(R.id.StepCount);
            sensorManager = (SensorManager)getSystemService((Context.SENSOR_SERVICE));
            stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            if(stepCountSensor == null){
                Toast.makeText(this,"No Step Detect Sensor", Toast.LENGTH_SHORT).show();
            }


        }

        @Override
        protected void onResume() {
            super.onResume();
            sensorManager.registerListener((SensorEventListener) this,stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        protected void onPause(){
            super.onPause();
            sensorManager.unregisterListener((SensorEventListener) this);
        }

        @Override
        public void onSensorChanged(SensorEvent event){
            if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER){
                if(in == 0){
                    raw = event.values[0];
                    in = 1;
                }

                counter = event.values[0] - raw;
                StepCount.setText("Step Count : " + String.valueOf((int)counter));
                //event.values[0] 이 걸음 수
                Intent intent = getIntent();
                String re = intent.getStringExtra("region");

                ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar);
                progressBar.setProgress((int) (counter/(intent.getIntExtra("distance",1))*100));
                //(intent.getIntExtra("distance",1)/event.values[0])*100)(진행도 %)이 50, 100일때 정보 표시 fragment 호출

                if(((counter/3) / (intent.getIntExtra("distance",1))*100) == 1){
                    re = re + "1";

                    bundle.putString("region", re);

                    onFragmentChanged(1);

                }

                else if(((counter/3) / (intent.getIntExtra("distance",1))*100) == 2){
                    re = re + "2";

                    bundle.putString("region", re);

                    onFragmentChanged(1);
                }
            }
        }

        @Override
        public  void onAccuracyChanged(Sensor sensor, int accuracy){

        }

        public void onFragmentChanged(int index) {
            fm = getSupportFragmentManager();
            tran = fm.beginTransaction();

            if (index == 0) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, basicfragment).commit();
            } else if (index == 1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, landmark).commit();
                landmark.setArguments(bundle);
            } else{
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, gpsinfo).commit();
            }
        }
}
