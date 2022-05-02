package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView welcomeTextView;
    TextView progressText;
    ProgressBar progressBar;
    TextView qHeaderTextView;
    TextView qBodyTextView;
    String name;
    String finalScore;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button nextButton;
    List<Button> options = new ArrayList<>();
    String[] qHeaderArray = {"Question 1", "Question 2", "Question 3", "Question 4", "Question 5"};
    String[] qBodyArray = {"Which of the following is not one of the 4 possible activity lifecycle states?",
            "Which method is used to send additional data with an Intent?",
            "Which of the following is not a valid lifecycle callback?",
            "Which of the following data types can’t be saved using shared preferences?",
            "Which of the following is not a valid method for saving changes when writing to shared preferences?"};

    String[] button1Options = {"Partially Hidden", "Intent.addData()", "onBoot()", "String", "apply()"};
    String[] button2Options = {"Destroyed", "Intent.putExtra()", "onResume()", "Set<String>", "commit()"};
    String[] button3Options = {"Paused", "Intent.putData()", "onDestroy()", "ArrayList<String>", "write()"};
    int[] correctAnswerButtonIndex = {2, 1, 0, 2, 2};
    int selection;
    int progress;
    int score;
    int question_idx;
    boolean submitted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        welcomeTextView = findViewById(R.id.welcomeTextView);
        progressText = findViewById(R.id.progressTextView);
        progressBar = findViewById(R.id.progressBar);
        qHeaderTextView = findViewById(R.id.qHeaderTextView);
        qBodyTextView = findViewById(R.id.qBodyTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        nextButton = findViewById(R.id.nextButton);
        options.add(answer1Button);
        options.add(answer2Button);
        options.add(answer3Button);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        welcomeTextView.setText("Welcome " + name + "!");

        answer1Button.setBackgroundResource(R.drawable.answerbutton);
        answer2Button.setBackgroundResource(R.drawable.answerbutton);
        answer3Button.setBackgroundResource(R.drawable.answerbutton);
        nextButton.setBackgroundResource(R.drawable.navbutton);

        score = 0;
        progress = 1;
        selection = -1;
        question_idx = 0;
        setQuestionData(question_idx);

        answer1Button.setOnClickListener(this);
        answer2Button.setOnClickListener(this);
        answer3Button.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }


    public void onClick(View view){

        switch(view.getId()){
            case R.id.answer1Button:
                selection = 0;
                break;

            case R.id.answer2Button:
                selection = 1;
                break;

            case R.id.answer3Button:
                selection = 2;
                break;

            case R.id.nextButton:

                if (submitted != true){

                    if (selection < 0 || selection > 2)
                    {
                        Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if (selection == correctAnswerButtonIndex[question_idx]){
                            options.get(selection).setBackgroundResource(R.drawable.correctanswerbutton);
                            score++;
                        }
                        else
                        {
                            options.get(correctAnswerButtonIndex[question_idx]).setBackgroundResource(R.drawable.correctanswerbutton);
                            options.get(selection).setBackgroundResource(R.drawable.wronganswerbutton);
                        }

                        nextButton.setText("Next");
                        submitted = true;


                    }

                }
                else{
                    question_idx++;
                    progress++;

                    if (progress > 5){

                        Intent intent = new Intent(this, ThirdActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("score", String.valueOf(score));
                        startActivity(intent);

                    }
                    else{
                        answer1Button.setBackgroundResource(R.drawable.answerbutton);
                        answer2Button.setBackgroundResource(R.drawable.answerbutton);
                        answer3Button.setBackgroundResource(R.drawable.answerbutton);
                        welcomeTextView.setText("");
                        setQuestionData(question_idx);
                    }

                }
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    public void setQuestionData(int index){

        qHeaderTextView.setText(qHeaderArray[index]);
        qBodyTextView.setText(qBodyArray[index]);
        answer1Button.setText(button1Options[index]);
        answer2Button.setText(button2Options[index]);
        answer3Button.setText(button3Options[index]);
        nextButton.setText("Submit");

        submitted = false;
        selection = -1;
        progressText.setText(progress + "/5");
        progressBar.setProgress(progress);

    }
}