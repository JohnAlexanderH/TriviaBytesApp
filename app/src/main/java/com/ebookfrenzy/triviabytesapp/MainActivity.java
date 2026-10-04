package com.ebookfrenzy.triviabytesapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ebookfrenzy.triviabytesapp.databinding.ActivityMainBinding;
/**
 * MainActivity controls the TriviaBytesApp user interface and game logic.
 * It displays True/False questions, checks the user's answers, provides
 * feedback, and allows the user to move to the next question.
 *
 * Author: John Henderson
 * Professor: Jeremiah Doody
 * Due Date: October 4, 2026
 */
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

    /**
     * Initializes the activity, sets up view binding, and displays
     * the first trivia question.
     *
     * @param savedInstanceState contains the activity's previously saved state
     */
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
    /**
     * Handles the True button click and checks the user's answer.
     *
     * @param view the View that triggered the method
     */
    public void checkTrue(View view) {
        checkAnswer(true);
    }
    /**
     * Handles the False button click and checks the user's answer.
     *
     * @param view the View that triggered the method
     */
    public void checkFalse(View view) {
        checkAnswer(false);
    }
    /**
     * Compares the user's answer with the correct answer and displays
     * whether the response is correct or incorrect.
     *
     * @param userAnswer the True or False answer selected by the user
     */
    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = answerBank[currentIndex];

        if (userAnswer == correctAnswer) {
            binding.answerText.setText(R.string.correct_text);
        } else {
            binding.answerText.setText(R.string.incorrect_text);
        }
    }
    /**
     * Advances to the next trivia question and clears the previous
     * answer feedback.
     *
     * @param view the View that triggered the method
     */
    public void nextQuestion(View view) {
        currentIndex = (currentIndex + 1) % questionBank.length;
        binding.questionText.setText(questionBank[currentIndex]);
        binding.answerText.setText("");
    }

}