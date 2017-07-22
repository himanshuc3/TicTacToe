package com.example.himanshu.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow , 1 = red
    private int active = 0;
    private int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean isGameActive = true;
    private int[][] winningCombinations = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}
    };

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000f);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && isGameActive) {
            gameState[tappedCounter] = active;
            if (active == 0) {
                active = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                active = 0;
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningCombination : winningCombinations){
                if((gameState[winningCombination[0]] == gameState[winningCombination[1]]) &&(gameState[winningCombination[1]] == gameState[winningCombination[2]]) && gameState[winningCombination[1]] != 2){
                    //Someone has won
                    isGameActive = false;
                    TextView winner = (TextView) findViewById(R.id.whowon);


                    if(gameState[winningCombination[0]] == 1){
                        winner.setText("Red has won!");
                    }else{
                        winner.setText("Yellow has won!");
                    }

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                    layout.setVisibility(View. VISIBLE);
                }else{
                    boolean gameOver = true;
                    for(int draw: gameState){
                        if(draw == 2) gameOver = false;
                    }

                    if(gameOver) {
                        TextView winner = (TextView) findViewById(R.id.whowon);
                        winner.setText("It's a draw!");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
                        layout.setVisibility(View. VISIBLE);
                    }
                }
            }
        } // outer if end



    }

    public void playAgain(View view){

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear);
        layout.setVisibility(View. INVISIBLE);
        active = 0;
        isGameActive = true;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i< grid.getChildCount();i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
