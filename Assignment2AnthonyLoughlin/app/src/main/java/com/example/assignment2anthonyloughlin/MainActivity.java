package com.example.assignment2anthonyloughlin;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Rochambo rochambo = new Rochambo();
    private ImageView playerPicture;
    private ImageView gamePicture;
    private TextView resultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerPicture = findViewById(R.id.player_choice);
        gamePicture = findViewById(R.id.computer_choice);
        resultsTextView = findViewById(R.id.results);
    }

    public void selectChoice(View view) {
        int id = view.getId();
        if(id == R.id.button_rock){
            rochambo.playerMakesMove(Rochambo.ROCK);
            playerPicture.setImageResource(R.drawable.rock);
            gameResults();
        }
        else if(id == R.id.button_paper){
            rochambo.playerMakesMove(Rochambo.PAPER);
            playerPicture.setImageResource(R.drawable.paper);
            gameResults();
        }
        else if(id == R.id.button_scissors){
            rochambo.playerMakesMove(Rochambo.SCISSOR);
            playerPicture.setImageResource(R.drawable.scissors);
            gameResults();
        }
        else{
            rochambo.playerMakesMove(Rochambo.NONE);
        }
    }

    private void gameResults(){
        if(rochambo.getGameMove() == Rochambo.ROCK){
            gamePicture.setImageResource(R.drawable.rock);
        }
        else if(rochambo.getGameMove() == Rochambo.PAPER){
            gamePicture.setImageResource(R.drawable.paper);
        }
        else{
            gamePicture.setImageResource(R.drawable.scissors);
        }
        animateImages();
        resultsTextView.setText(rochambo.winLoseOrDraw());
    }

    private void animateImages(){
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(findViewById(R.id.player_choice), "rotationX", 0f, 360f).setDuration(750);
        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(findViewById(R.id.computer_choice),"rotationY", 0f, 360f).setDuration(750);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorGame, animatorPlayer);
        animatorSet.setInterpolator(new AnticipateOvershootInterpolator());
        animatorSet.start();
    }
}
