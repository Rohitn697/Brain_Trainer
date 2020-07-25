package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button Gobutton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectans;
    TextView ansTextView;
    int score = 0;
    int noOfQues = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button PlayAgain;
    TextView sumTextView;
    TextView timerTextView;
    ConstraintLayout gameLayout;

    public void PlayAgain(View view){
        score=0;
        noOfQues=0;
        timerTextView.setText("30s");
        PlayAgain.setVisibility(View.INVISIBLE);
        ansTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(noOfQues));
        newQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000+ "s"));
            }

            @Override
            public void onFinish() {
                ansTextView.setText("Game Over!");
                PlayAgain.setVisibility(View.VISIBLE);

            }
        }.start();
    }

    public void newQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(20);
        int b = rand.nextInt(20);
        sumTextView.setText(Integer.toString(a)+ "X" + Integer.toString(b));
        locationOfCorrectans = rand.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++) {
            if (locationOfCorrectans == i) {
                answer.add(a * b);
            } else {
                int wrongAnswer = rand.nextInt(400);
                while (wrongAnswer==a*b) {
                    wrongAnswer = rand.nextInt(400);
                }
                answer.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
    public void chooseAns(View view){
        if(Integer.toString(locationOfCorrectans).equals(view.getTag().toString())){
            ansTextView.setText("CORRECT!");
            ansTextView.setVisibility(View.VISIBLE);
            score++;
        }
        else{
            ansTextView.setText("WRONG:(");
            ansTextView.setVisibility(View.VISIBLE);
        }
        noOfQues++;
        scoreTextView.setText(Integer.toString(score)+ "/" + Integer.toString(noOfQues));
        newQuestion();

    }
    public void start(View view){
        Gobutton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        PlayAgain(PlayAgain);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        Gobutton=findViewById(R.id.goButton);
         sumTextView = findViewById(R.id.randTextView);
        ansTextView = findViewById(R.id.resultTextView);
        ansTextView.setVisibility(View.INVISIBLE);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        PlayAgain = findViewById(R.id.playagain);
        gameLayout = findViewById(R.id.startlayout);
        gameLayout.setVisibility(View.INVISIBLE);
        Gobutton.setVisibility(View.VISIBLE);



    }
}