package com.example.android.quiz.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quiz.R;

public class WelcomeActivity extends AppCompatActivity {

    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        nameField = findViewById(R.id.user_name);

        Button onStartQuiz = findViewById(R.id.startQuiz);
        onStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adding person name to String by EditText field
                String personName = nameField.getText().toString();
                // Show an error message as a toast when the name has not been entered
                if (personName.trim().length() == 0) {
                    Toast.makeText(WelcomeActivity.this, getString(R.string.no_name_toast), Toast.LENGTH_SHORT).show();
                    // Exit this method early because user should enter Name first
                    return;
                }
                Intent intent = new Intent(WelcomeActivity.this, com.example.android.quiz.activity.MainActivity.class);
                intent.putExtra("PERSON_NAME", personName);
                startActivity(intent);

            }

        });
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
}