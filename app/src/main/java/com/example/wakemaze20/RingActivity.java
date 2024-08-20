package com.example.wakemaze20;

import static com.example.wakemaze20.R.id.btnSnooze;
import static com.example.wakemaze20.R.id.btnStopAlarm;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.util.Random;

public class RingActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button stopButton;
    private Button snoozeButton;
    private FrameLayout fragmentContainer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);

        mediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        stopButton = findViewById(R.id.btnStopAlarm);
        snoozeButton = findViewById(btnSnooze);
        fragmentContainer = findViewById(R.id.fragment_container);

        stopButton.setOnClickListener(v -> stopAlarm());

        // Randomly choose between snooze button, math problem, or random sentence
        Random random = new Random();
        int choice = random.nextInt(3);

        if (choice == 0) {
            setRandomSnoozeButtonPosition();
            snoozeButton.setVisibility(View.VISIBLE);
            snoozeButton.setOnClickListener(v -> stopAlarm());
        } else {
            snoozeButton.setVisibility(View.GONE);
            showChallenge(choice);
        }
    }

    private void stopAlarm() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        finish();
    }

    private void setRandomSnoozeButtonPosition() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snoozeButton.getLayoutParams();
        Random random = new Random();

        // Get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        // Calculate random position within screen bounds
        int maxX = screenWidth - snoozeButton.getWidth();
        int maxY = screenHeight - snoozeButton.getHeight();
        int randomX = random.nextInt(maxX);
        int randomY = random.nextInt(maxY);

        params.leftMargin = randomX;
        params.topMargin = randomY;
        snoozeButton.setLayoutParams(params);
    }

    private void showChallenge(int choice) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (choice == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new MathProblemFragment())
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, new RandomSentenceFragment())
                    .commit();
        }
    }
}
