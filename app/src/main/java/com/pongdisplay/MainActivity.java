package com.pongdisplay;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openBrightnessSetupActivity(View view){
        Intent intent = new Intent(this, BrightnessSetupActivity.class);
        startActivity(intent);
    }
    public void openDateSetupActivity(View view){
        Intent intent = new Intent(this, DateSetupActivity.class);
        startActivity(intent);
    }
    public void openTimeSetupActivity(View view){
        Intent intent = new Intent(this, TimeSetupActivity.class);
        startActivity(intent);
    }

    public void openNoteSetupActivity(View view){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    public void openSensorsActivity(View view) {
        Intent intent = new Intent(this, SensorsActivity.class);
        startActivity(intent);
    }

    public void openConnectivityActiviy(View view) {
        Intent intent = new Intent(this, ConnectivityActivity.class);
        startActivity(intent);
    }
}