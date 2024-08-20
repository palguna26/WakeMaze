package com.example.wakemaze20;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Random;

public class RandomSentenceFragment extends Fragment {
    private TextView sentenceTextView;
    private EditText userInputEditText;
    private Button submitButton;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random_sentence, container, false);

        sentenceTextView = view.findViewById(R.id.sentenceTextView);
        userInputEditText = view.findViewById(R.id.userInputEditText);
        submitButton = view.findViewById(R.id.submitButton);

        generateRandomSentence();

        submitButton.setOnClickListener(v -> checkSentence());

        return view;
    }

    private void generateRandomSentence() {
        String[] sentences = {
                "I love programming.",
                "The sky is blue.",
                "WakeMaze is awesome.",
                "Today is a great day."
        };
        Random random = new Random();
        String randomSentence = sentences[random.nextInt(sentences.length)];
        sentenceTextView.setText(randomSentence);
    }

    private void checkSentence() {
        String userSentence = userInputEditText.getText().toString();
        String correctSentence = sentenceTextView.getText().toString();

        if (userSentence.equals(correctSentence)) {
            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            stopAlarm();
        } else {
            Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopAlarm() {
        if (!mediaPlayer.isPlaying()) {
            return;
        }
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
