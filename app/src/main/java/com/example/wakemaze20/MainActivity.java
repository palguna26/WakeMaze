package com.example.wakemaze20;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnSetAlarm).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SetAlarmActivity.class)));
        findViewById(R.id.btnSetTimer).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SetTimerActivity.class)));
    }
}



