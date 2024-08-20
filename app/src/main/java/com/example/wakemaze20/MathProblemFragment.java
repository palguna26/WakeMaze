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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MathProblemFragment extends Fragment {
    private TextView mathProblemTextView;
    private EditText answerEditText;
    private Button submitButton;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math_problem, container, false);

        mathProblemTextView = view.findViewById(R.id.mathProblemTextView);
        answerEditText = view.findViewById(R.id.answerEditText);
        submitButton = view.findViewById(R.id.submitButton);

        generateMathProblem();

        submitButton.setOnClickListener(v -> checkAnswer());

        return view;
    }

    private void generateMathProblem() {
        Random random = new Random();
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        mathProblemTextView.setText(a + " + " + b + " = ?");
    }

    private void checkAnswer() {
        String[] parts = mathProblemTextView.getText().toString().split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
        int correctAnswer = a + b;

        int userAnswer = Integer.parseInt(answerEditText.getText().toString());
        if (userAnswer == correctAnswer) {
            Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            stopAlarm();
        } else {
            Toast.makeText(getActivity(), "Try again!", Toast.LENGTH_SHORT).show();
        }
    }
    public void stopAlarm() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }


    }
}
