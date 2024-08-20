package com.example.wakemaze20;

import static android.app.ProgressDialog.show;
import static androidx.core.app.PendingIntentCompat.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetTimerActivity extends AppCompatActivity {
    private EditText etMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_timer);

        etMinutes = findViewById(R.id.etMinutes);

        findViewById(R.id.btnSetTimer).setOnClickListener(v -> setTimer());
    }

    private void setTimer() {
        int minutes = Integer.parseInt(etMinutes.getText().toString());
        long millis = minutes * 60 * 1000;
        Toast.makeText(getApplicationContext(), "Timer has been set", Toast.LENGTH_SHORT).show();
        new CountDownTimer(millis, 1000) {
            public void onTick(long millisUntilFinished) {
                // Update UI
            }

            public void onFinish() {
                Intent intent = new Intent(SetTimerActivity.this, RingActivity.class);
                startActivity(intent);

            }
        }.start();
    }
}
