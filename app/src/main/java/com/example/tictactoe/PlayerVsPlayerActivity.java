package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class PlayerVsPlayerActivity extends AppCompatActivity {
        private short activePlayer = 1;
        private short movesCounter = 0;
        private boolean isGameOver = false;
        private short[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        private short[][] winningSets = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        private void animate(ImageView counter){
            counter.setScaleX(0f);
            counter.setScaleY(0f);
            if (activePlayer == 0){
                counter.setImageResource(R.drawable.trans_shape_o);
                activePlayer = 1;
            }
            else{
                counter.setImageResource(R.drawable.trans_shape_x);
                activePlayer = 0;
            }
            counter.animate().scaleXBy(1).setDuration(200);
            counter.animate().scaleYBy(1).setDuration(200);
        }
        private void disableAllImageViews(){
            findViewById(R.id.imageView1).setClickable(false);
            findViewById(R.id.imageView2).setClickable(false);
            findViewById(R.id.imageView3).setClickable(false);
            findViewById(R.id.imageView4).setClickable(false);
            findViewById(R.id.imageView5).setClickable(false);
            findViewById(R.id.imageView6).setClickable(false);
            findViewById(R.id.imageView7).setClickable(false);
            findViewById(R.id.imageView8).setClickable(false);
            findViewById(R.id.imageView9).setClickable(false);
        }
        private void gameOver(){
            Button playButton = (Button) findViewById(R.id.menu);
            playButton.setVisibility(View.VISIBLE);

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        public void zoomIn(View view) {
            if(!isGameOver && movesCounter < 9 ){
                ImageView counter = (ImageView) view;
                int tappedCounter = Integer.parseInt(counter.getTag().toString());
                movesCounter++;
                view.setClickable(false);
                gameState[tappedCounter] = activePlayer;

                animate(counter);

                for( short[] winningSet : winningSets){
                        if(gameState[winningSet[0]] == gameState[winningSet[1]]
                                && gameState[winningSet[1]] == gameState[winningSet[2]]
                                && gameState[winningSet[0]] != 2) {

                            String winner = "Winner is: ";
                            TextView textView = findViewById(R.id.displayWinner);
                            if(activePlayer == 0)  winner += "X";
                            else winner += "O";

                            textView.setText(winner);
                            textView.setVisibility(View.VISIBLE);

                            Toast.makeText(this,"GG,WP EZ", Toast.LENGTH_LONG).show();
                            isGameOver = true;
                        }
                    }

                if(isGameOver){
                        disableAllImageViews();
                        gameOver();

                }
                else if(!isGameOver && movesCounter == 9){
                    TextView textView = findViewById(R.id.displayWinner);

                    int unicode = 0x1F60A;
                    textView.setText("now Nobody Wins "+ Character.toChars(unicode));
                    textView.setVisibility(View.VISIBLE);
                    gameOver();
                }

            }
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_vs_player);
    }
}
