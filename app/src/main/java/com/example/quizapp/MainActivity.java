package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TextView title;
    //TextView namePrompt;
    EditText nameEditText;
    Button startButton;
    String NAME;
    SharedPreferences sharedPreferences;

    public void startQuiz (View view)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, nameEditText.getText().toString());
        editor.apply();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", nameEditText.getText().toString());
        startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameInputEditText);
        startButton = findViewById(R.id.startButton);
        sharedPreferences = getSharedPreferences("com.example.quizapp", MODE_PRIVATE);
        checkSharedPreferences();

    }

    public void checkSharedPreferences(){
        String name = sharedPreferences.getString(NAME, "");
        nameEditText.setText(name);
    }
}