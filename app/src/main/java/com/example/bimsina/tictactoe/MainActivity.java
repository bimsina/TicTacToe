package com.example.bimsina.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int Player;
    //3 means un played
    int[] clickCounter = {3, 3, 3, 3, 3, 3, 3, 3, 3};
    int tapCounter;
    TextView player;
    boolean gameIsActive = true;

    public void click(View view) {
        player = findViewById(R.id.playerText);
        ImageView pict = (ImageView) view;
        tapCounter = Integer.parseInt(pict.getTag().toString());


        if (clickCounter[tapCounter] == 3 && gameIsActive) {
            clickCounter[tapCounter] = Player;
            Log.i("Click","Clicked");
            if (Player == 1) {
                pict.setImageResource(R.drawable.cross);
                pictAnimate(pict);
                player.setText("Player 2's turn.");
                Player = 2;
            } else if (Player == 2) {
                pict.setImageResource(R.drawable.circle);
                pictAnimate(pict);
                player.setText("Player 1's turn.");
                Player = 1;
            }
        }
    }

    public void pictAnimate(View pict) {
        pict.animate().scaleX(1f).scaleY(1f).setDuration(250);
        gameState(clickCounter);
    }

    public void gameState(int[] a) {
        LinearLayout newGameLayout = findViewById(R.id.playAgainView);
        TextView wonText = findViewById(R.id.won);
        if ((a[0] == 1 && a[1] == 1 && a[2] == 1) || (a[3] == 1 && a[4] == 1 && a[5] == 1) || (a[6] == 1 &&
                a[7] == 1 && a[8] == 1)
                || (a[0] == 1 && a[3] == 1 && a[6] == 1) || (a[1] == 1 && a[4] == 1 && a[7] == 1) || (a[2] == 1
                && a[5] == 1 && a[8] == 1)
                || (a[0] == 1 && a[4] == 1 && a[8] == 1) || (a[2] == 1 && a[4] == 1 && a[6] == 1)) {

            wonText.setText("Player 1 won!");
            gameIsActive = false;
            newGameLayout.setVisibility(View.VISIBLE);
            newGameLayout.animate().scaleX(1f).scaleY(1f).setDuration(500);
        } else if ((a[0] == 2 && a[1] == 2 && a[2] == 2) || (a[3] == 2 && a[4] == 2 && a[5] == 2) || (a[6] == 2 &&
                a[7] == 2 && a[8] == 2)
                || (a[0] == 2 && a[3] == 2 && a[6] == 2) || (a[1] == 2 && a[4] == 2 && a[7] == 2) || (a[2] == 2
                && a[5] == 2 && a[8] == 2)
                || (a[0] == 2 && a[4] == 2 && a[8] == 2) || (a[2] == 2 && a[4] == 2 && a[6] == 2)) {

            wonText.setText("Player 2 won!");
            gameIsActive = false;
            newGameLayout.setVisibility(View.VISIBLE);
            newGameLayout.animate().scaleX(1f).scaleY(1f).setDuration(500);
        }

        else
        {
            boolean gameIsOver = true;
            for(int counterState : clickCounter)
            {
                if (counterState == 3) gameIsOver =false;
            }
            if(gameIsOver){
                wonText.setText("Draw");
                gameIsActive = false;
                newGameLayout.setVisibility(View.VISIBLE);
                newGameLayout.animate().scaleX(1f).scaleY(1f).setDuration(500);
            }
        }
    }

    public void playAgain(View view)
    {
        LinearLayout newGameLayout = findViewById(R.id.playAgainView);
        newGameLayout.setVisibility(View.INVISIBLE);
        player = findViewById(R.id.playerText);
        player.setText("Player 1's turn.");
        newGameLayout.setScaleX(0f);
        newGameLayout.setScaleY(0f);
        for(int i=0;i<9;i++)
        {
            Player = 1;
            clickCounter[i] = 3;
            gameIsActive = true;
        }

        GridLayout grid = findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
            ((ImageView) grid.getChildAt(i)).setScaleX(0f);
            ((ImageView) grid.getChildAt(i)).setScaleY(0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Player = 1;
    }
}
