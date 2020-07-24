package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {
//    Player representation:
//    0 - X
//    1 - O
    int activePlayer = 0;
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
//    State meanings:
//    0 - X
//    1 - O
//    2 - Null
    int [][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};
    boolean gameActive = true;
    boolean tie;
    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 2 && gameActive)
        {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f);
        }

        for (int [] winPosition : winPositions)
        {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] !=2)
            {
                tie = false;
                //Somebody has won!
                String winner;
                if (gameState[winPosition[0]] == 0)
                {
                    winner = "X has won!";
                }
                else
                {
                    winner = "O has won!";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winner);
                gameActive = false;
            }
        }
        for (int i=0; i<gameState.length; i++)
        {
            if(gameState[i]==2){
                tie = false;
                break;
            }
            else{
                tie = true;
            }

        }
        if (tie)
        {
            TextView status = findViewById(R.id.status);
            status.setText("Tie!!!");
            gameActive = false;
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i=0; i<gameState.length; i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.iv0)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv1)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv2)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv3)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv4)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv5)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv6)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv7)).setImageResource(0);
        ((ImageView)findViewById(R.id.iv8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}