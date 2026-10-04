package com.ebookfrenzy.triviabytesapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ebookfrenzy.triviabytesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    // Example only. Replace with the ids of your own 3 string resources
    private final int[] questionBank = {
            R.string.question_1,
            R.string.question_2,
            R.string.question_3
    };

    // true/false answer for each question above, in the same order. Ensure not all are true, and not all are false.
    private final boolean[] answerBank = {true, false, true};

    private int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.questionText.setText(questionBank[currentIndex]);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    public void checkTrue(View view) {
        checkAnswer(true);
    }

    public void checkFalse(View view) {
        checkAnswer(false);
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = answerBank[currentIndex];

        if (userAnswer == correctAnswer) {
            binding.answerText.setText(R.string.correct_text);
        } else {
            binding.answerText.setText(R.string.incorrect_text);
        }
    }

    public void nextQuestion(View view) {
        currentIndex = (currentIndex + 1) % questionBank.length;
        binding.questionText.setText(questionBank[currentIndex]);
        binding.answerText.setText("");
    }

}