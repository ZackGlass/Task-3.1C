package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    TextView congratsTextView;
    TextView scoreLabelTextView;
    TextView scoreTextView;
    Button newQuizButton;
    Button finishButton;
    String name;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        congratsTextView = findViewById(R.id.congratTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        newQuizButton = findViewById(R.id.newQuizButton);
        finishButton = findViewById(R.id.finishButton);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        score = intent.getStringExtra("score");

        congratsTextView.setText("Congratulations " + name + "!");
        scoreTextView.setText(score + "/5");

        newQuizButton.setOnClickListener(this);
        finishButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newQuizButton:
                Intent restartIntent = new Intent(this, MainActivity.class);
                //restartIntent.putExtra("name", name);
                startActivity(restartIntent);
                break;
            case R.id.finishButton:
                this.finishAffinity();
                break;
        }
    }
}