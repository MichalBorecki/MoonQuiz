package com.example.android.quiz.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quiz.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton q01_a01;
    private RadioButton q01_a02;
    private RadioButton q01_a03;
    private RadioButton q01_a04;
    private RadioButton q02_a01;
    private RadioButton q02_a02;
    private RadioButton q02_a03;
    private RadioButton q02_a04;
    private RadioButton q03_a01;
    private RadioButton q03_a02;
    private RadioButton q03_a03;
    private RadioButton q03_a04;
    private EditText q04EditText;
    private String savedPersonName;
    private Button resultButton;
    private Button playAgainButton;
    private Button shareResultButton;
    private Button quitButton;
    private TextView resultTextView;
    private CheckBox q05_a01;
    private CheckBox q05_a02;
    private CheckBox q05_a03;
    private CheckBox q05_a04;
    private Drawable goodAnswerIcon;
    private Drawable wrongAnswerIcon;
    // finalScore is "-1" because we have to check if user checked once result of quiz when orientation was changed, if checked the finalScore should be: finalScore >= 0.
    private int finalScore = -1;
    private String finalScoreSaved = "FINAL_SCORE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savedPersonName = getIntent().getExtras().getString("PERSON_NAME");

        // Separate method for initializing the views
        initialize();

        //Changing color of icon used for mark good answers
        ColorFilter filterGreen = new LightingColorFilter(Color.GREEN, Color.BLACK);
        goodAnswerIcon = getDrawable(R.drawable.good);
        goodAnswerIcon.setColorFilter(filterGreen);

        //Changing color of icon used for mark wrong answers
        ColorFilter filterRed = new LightingColorFilter(Color.RED, Color.BLACK);
        wrongAnswerIcon = getDrawable(R.drawable.wrong);
        wrongAnswerIcon.setColorFilter(filterRed);

        // Setting visibility of buttons and text view with result
        resultTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        shareResultButton.setVisibility(View.INVISIBLE);
        quitButton.setVisibility(View.INVISIBLE);
    }

    //Use onSaveInstanceState(Bundle)
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("Q01_A01", q01_a01.isChecked());
        outState.putBoolean("Q01_A02", q01_a02.isChecked());
        outState.putBoolean("Q01_A03", q01_a03.isChecked());
        outState.putBoolean("Q01_A04", q01_a04.isChecked());

        outState.putBoolean("Q02_A01", q02_a01.isChecked());
        outState.putBoolean("Q02_A02", q02_a02.isChecked());
        outState.putBoolean("Q02_A03", q02_a03.isChecked());
        outState.putBoolean("Q02_A04", q02_a04.isChecked());

        outState.putBoolean("Q03_A01", q03_a01.isChecked());
        outState.putBoolean("Q03_A02", q03_a02.isChecked());
        outState.putBoolean("Q03_A03", q03_a03.isChecked());
        outState.putBoolean("Q03_A04", q03_a04.isChecked());

        outState.putString("ANSWER_Q04", q04EditText.getText().toString());

        outState.putBoolean("Q05_A01", q05_a01.isChecked());
        outState.putBoolean("Q05_A02", q05_a02.isChecked());
        outState.putBoolean("Q05_A03", q05_a03.isChecked());
        outState.putBoolean("Q05_A04", q05_a04.isChecked());

        outState.putInt("FINAL_SCORE", finalScore);
    }

    //onRestoreInstanceState
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        q01_a01.setChecked(savedInstanceState.getBoolean("Q01_A01"));
        q01_a02.setChecked(savedInstanceState.getBoolean("Q01_A02"));
        q01_a03.setChecked(savedInstanceState.getBoolean("Q01_A03"));
        q01_a04.setChecked(savedInstanceState.getBoolean("Q01_A04"));

        q02_a01.setChecked(savedInstanceState.getBoolean("Q02_A01"));
        q02_a02.setChecked(savedInstanceState.getBoolean("Q02_A02"));
        q02_a03.setChecked(savedInstanceState.getBoolean("Q02_A03"));
        q02_a04.setChecked(savedInstanceState.getBoolean("Q02_A04"));

        q03_a01.setChecked(savedInstanceState.getBoolean("Q03_A01"));
        q03_a02.setChecked(savedInstanceState.getBoolean("Q03_A02"));
        q03_a03.setChecked(savedInstanceState.getBoolean("Q03_A03"));
        q03_a04.setChecked(savedInstanceState.getBoolean("Q03_A04"));

        q04EditText.setText(savedInstanceState.getString("ANSWER_Q04"));

        q05_a01.setChecked(savedInstanceState.getBoolean("Q05_A01"));
        q05_a02.setChecked(savedInstanceState.getBoolean("Q05_A02"));
        q05_a03.setChecked(savedInstanceState.getBoolean("Q05_A03"));
        q05_a04.setChecked(savedInstanceState.getBoolean("Q05_A04"));

        finalScore = savedInstanceState.getInt(finalScoreSaved);
        // If user checked result before, then we have to set this result again
        if (finalScore >= 0) {
            result(null);
        }
    }

    /**
     * This method contains initializing of the views
     */
    private void initialize() {

        q01_a01 = findViewById(R.id.q01_a01);
        q01_a02 = findViewById(R.id.q01_a02);
        q01_a03 = findViewById(R.id.q01_a03);
        q01_a04 = findViewById(R.id.q01_a04);

        q02_a01 = findViewById(R.id.q02_a01);
        q02_a02 = findViewById(R.id.q02_a02);
        q02_a03 = findViewById(R.id.q02_a03);
        q02_a04 = findViewById(R.id.q02_a04);

        q03_a01 = findViewById(R.id.q03_a01);
        q03_a02 = findViewById(R.id.q03_a02);
        q03_a03 = findViewById(R.id.q03_a03);
        q03_a04 = findViewById(R.id.q03_a04);

        q04EditText = findViewById(R.id.q04_a);

        q05_a01 = findViewById(R.id.q05_a01);
        q05_a02 = findViewById(R.id.q05_a02);
        q05_a03 = findViewById(R.id.q05_a03);
        q05_a04 = findViewById(R.id.q05_a04);

        resultButton = findViewById(R.id.result);
        playAgainButton = findViewById(R.id.play_again);
        resultTextView = findViewById(R.id.resultTextView);
        shareResultButton = findViewById(R.id.share_result);
        quitButton = findViewById(R.id.quit);
    }

    /**
     * This method is called for hide keyboard when click outside of it (code from: https://stackoverflow.com/questions/4828636/edittext-clear-focus-on-touch-outside/23005236, author: zMan)
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * This method is responsible for show result and correct questions
     */
    public void result(View view) {
        int quantityChecked = 0;
        if (q05_a01.isChecked()) {
            quantityChecked++;
        }
        if (q05_a02.isChecked()) {
            quantityChecked++;
        }
        if (q05_a03.isChecked()) {
            quantityChecked++;
        }
        if (q05_a04.isChecked()) {
            quantityChecked++;
        }
        if (quantityChecked != 2) {
            Toast.makeText(MainActivity.this, getString(R.string.toast_question_05), Toast.LENGTH_LONG).show();
            // Exit this method because user should enter two answers for question 5
            return;
        }

        finalScore = calculateScore();

        resultButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        shareResultButton.setVisibility(View.VISIBLE);
        quitButton.setVisibility(View.VISIBLE);

        showResult(finalScore);

        // Change color of wrong and correct answers for question 5
        if (q05_a01.isChecked()) { // wrong answer 01
            q05_a01.setTextColor(getResources().getColor(R.color.red));
            q05_a01.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        // good answer 02
        q05_a02.setTextColor(getResources().getColor(R.color.green));
        if (q05_a02.isChecked()) {
            q05_a02.setButtonDrawable(getDrawable(R.drawable.good));
        }

        if (q05_a03.isChecked()) { // wrong answer 03
            q05_a03.setTextColor(getResources().getColor(R.color.red));
            q05_a03.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        // good answer 04
        q05_a04.setTextColor(getResources().getColor(R.color.green));
        if (q05_a04.isChecked()) {
            q05_a04.setButtonDrawable(getDrawable(R.drawable.good));
        }
    }


    /**
     * This method is called for calculate score and mark some correct and wrong answers
     */
    private int calculateScore() {
        int score = 0;
        // ---------------------------- Check q1
        if (q01_a01.isChecked()) {
            q01_a01.setTextColor(getResources().getColor(R.color.red));
            q01_a01.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q01_a02.isChecked()) {
            q01_a02.setTextColor(getResources().getColor(R.color.red));
            q01_a02.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q01_a03.isChecked()) {
            score++;
            q01_a03.setButtonDrawable(getDrawable(R.drawable.good));
        }
        q01_a03.setTextColor(getResources().getColor(R.color.green));


        if (q01_a04.isChecked()) {
            q01_a04.setTextColor(getResources().getColor(R.color.red));
            q01_a04.setButtonDrawable(getDrawable(R.drawable.wrong));
        }

        // ---------------------------- Check q2
        if (q02_a01.isChecked()) {
            q02_a01.setTextColor(getResources().getColor(R.color.red));
            q02_a01.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q02_a02.isChecked()) {
            q02_a02.setTextColor(getResources().getColor(R.color.red));
            q02_a02.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q02_a03.isChecked()) {
            score++;
            q02_a03.setButtonDrawable(getDrawable(R.drawable.good));
        }
        q02_a03.setTextColor(getResources().getColor(R.color.green));

        if (q02_a04.isChecked()) {
            q02_a04.setTextColor(getResources().getColor(R.color.red));
            q02_a04.setButtonDrawable(getDrawable(R.drawable.wrong));
        }

        // ---------------------------- Check q3
        if (q03_a01.isChecked()) {
            score++;
            q03_a01.setButtonDrawable(getDrawable(R.drawable.good));
        }
        q03_a01.setTextColor(getResources().getColor(R.color.green));

        if (q03_a02.isChecked()) {
            q03_a02.setTextColor(getResources().getColor(R.color.red));
            q03_a02.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q03_a03.isChecked()) {
            score++;
            q03_a03.setTextColor(getResources().getColor(R.color.red));
            q03_a03.setButtonDrawable(getDrawable(R.drawable.wrong));
        }
        if (q03_a04.isChecked()) {
            q03_a04.setTextColor(getResources().getColor(R.color.red));
            q03_a04.setButtonDrawable(getDrawable(R.drawable.wrong));
        }

        // ---------------------------- Check q4
        String answer_q04 = q04EditText.getText().toString().replaceAll("\\.", "").trim();
        if (answer_q04.equalsIgnoreCase("NASA")) {
            score++;
            q04EditText.setTextColor(getResources().getColor(R.color.green));
        } else {
            q04EditText.setTextColor(getResources().getColor(R.color.red));
        }

        // ---------------------------- Check q5
        if (q05_a02.isChecked() && q05_a04.isChecked()) {
            score++;
        }
        return score;
    }

    /**
     * This method provide result to resultTextView
     */
    private void showResult(int finalScore) {
        Toast.makeText(MainActivity.this, savedPersonName + " your result is: " + finalScore + "/5", Toast.LENGTH_LONG).show();
        resultTextView.setText(savedPersonName + " your result is: " + finalScore + "/5");
    }

    /**
     * This method is called for share the results
     */
    public void shareResult(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String topic = "My " + getString(R.string.quiz_name) + " result";
        intent.putExtra(Intent.EXTRA_SUBJECT, topic);
        String result = "My result in " + getString(R.string.quiz_name) + " is: " + finalScore + "/5";
        intent.putExtra(Intent.EXTRA_TEXT, result);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called for restart the Moon Quiz App
     */
    public void playAgain(View view) {
        Intent restartQuiz = new Intent(this, WelcomeActivity.class);
        startActivity(restartQuiz);
    }

    /**
     * This method is called for quit from app
     */
    public void quit(View view) {
        this.finishAffinity();
    }
}


