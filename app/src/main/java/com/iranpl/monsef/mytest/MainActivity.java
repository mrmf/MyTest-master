package com.iranpl.monsef.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MyLogs","Creating APP On Phone");
        }

@Override
public void onStart()
        {
        super.onStart();
        Log.d("MyLogs","Starting APP On Phone");
        }

@Override
protected void onResume() {
        super.onResume();
        Log.d("MyLogs","Resuming APP On Phone");
        }

@Override
protected void onPause() {
        super.onPause();
        Log.d("MyLogs","Pausing APP On Phone");
        }

@Override
protected void onStop() {
        super.onStop();
        Log.d("MyLogs","Stopping APP On Phone");
        }

@Override
protected void onDestroy() {
        super.onDestroy();
        Log.d("MyLogs","Destroying APP On Phone");
        }

@Override
protected void onRestart() {
        super.onRestart();
        Log.d("MyLogs","Restarting APP On Phone");
        }
}
