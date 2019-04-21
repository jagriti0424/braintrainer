package com.example.admin.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctans;
    int score = 0;
    int noquest = 0;
    Button PlayButton;
    RelativeLayout game;


    public void playagain(View view) {

        score = 0;
        noquest = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        PlayButton.setVisibility(View.INVISIBLE);
        generate();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf((millisUntilFinished / 1000) + "s"));

            }

            @Override
            public void onFinish() {


                PlayButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:" + Integer.toString(score) + "/" + Integer.toString(noquest));
            }
        }.start();

    }


    public void generate() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        correctans = rand.nextInt(4);
        answers.clear();

        int incorrect;
        for (int i = 0; i < 4; i++) {
            if (i == correctans) {
                answers.add(a + b);
            } else {
                incorrect = rand.nextInt(41);
                while (incorrect == a + b) {
                    incorrect = rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    public void chooseAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(correctans))) {
            score++;
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong!");
        }
        noquest++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(noquest));
        generate();

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        game.setVisibility(RelativeLayout.VISIBLE);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        PlayButton = (Button) findViewById(R.id.PlayButton);
        game=(RelativeLayout)findViewById(R.id.game);


        playagain(findViewById(R.id.PlayButton));


    }
}
