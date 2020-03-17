package com.navarrosoft.numericsuspense;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SixtySeconds extends Activity {

    private int count = 1;
    private int[] numberCount = new int[4];
    public int score = 0;
    private Random random = new Random();
    private TextView info;
    private TextView timer;
    public int time = 60000;
    private CountDownTimer countdownTimer;
    private TextView tile1;
    private TextView tile2;
    private TextView tile3;
    private TextView tile4;
    private File file;
    private int highScore = 0;
    private MediaPlayer mediaPlayer;
    private int endGameVerify = 0;
    private boolean gameInSession = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);

        mediaPlayer = MediaPlayer.create(this, R.raw.tile_click);


        MobileAds.initialize(getApplicationContext(),getString(R.string.google_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
                countdownTimer.cancel();
                if(endGameVerify == 0) {
                    endGameVerify++;
                    Intent endGame = new Intent(SixtySeconds.this, EndGame.class);
                    HighScoreReview();
                    endGame.putExtra("HighScore", highScore);
                    endGame.putExtra("Reason", getString(R.string.UserEndGame
                    ));
                    endGame.putExtra("GameType", getString(R.string.SixtySec));
                    endGame.putExtra("Score", score);
                    endGame.putExtra("Intent", SixtySeconds.class);
                    startActivity(endGame);
                }
                finish();
            }
        });

        file = new File(this.getFilesDir(), "SixtySeconds");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if( line != null){
                line.trim();
                highScore = Integer.parseInt(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
            highScore = 0;
        }



        info = (TextView) findViewById(R.id.info);
        info.setText(String.format(getString(R.string.info), score + ""));
        timer = (TextView) findViewById(R.id.timer);
        randomNumberSort();
        setNumberOnTiles();

        countdownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long tick) {
                timer.setText(String.format(getString(R.string.time), (int)( tick / 1000.0) + ""));
            }

            @Override
            public void onFinish() {
                if(endGameVerify == 0) {
                    endGameVerify++;
                    Intent endGame = new Intent(SixtySeconds.this, EndGame.class);
                    HighScoreReview();
                    endGame.putExtra("HighScore", highScore);
                    endGame.putExtra("Reason", getString(R.string.TimesUp));
                    endGame.putExtra("GameType", getString(R.string.arithmetic));
                    endGame.putExtra("Score", score);
                    endGame.putExtra("Intent", SixtySeconds.class);
                    startActivity(endGame);
                }
                finish();
            }
        }.start();

        tile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                if (numberCount[0] == count) {
                    count++;
                    score++;
                    info.setText(String.format(getString(R.string.info), score + ""));
                    randomNumberSort();
                    setNumberOnTiles();
                }else{
                    tile1.setBackgroundColor(Color.parseColor("#d50000"));
                    if(score > 0){
                        score--;
                        info.setText(String.format(getString(R.string.info), score + ""));
                    }
                    new CountDownTimer(100, 100){

                        @Override
                        public void onTick(long l) {}

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onFinish() {
                        tile1.setBackgroundColor(Color.WHITE);
                        }
                    }.start();
                }
            }
        });

        tile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                if (numberCount[1] == count) {
                    count++;
                    score++;
                    info.setText(String.format(getString(R.string.info), score + ""));
                    randomNumberSort();
                    setNumberOnTiles();
                }else{
                    tile2.setBackgroundColor(Color.parseColor("#d50000"));
                    if(score > 0){
                        score--;
                        info.setText(String.format(getString(R.string.info), score + ""));
                    }
                    new CountDownTimer(100, 100){

                        @Override
                        public void onTick(long l) {}

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onFinish() {
                            tile2.setBackgroundColor(getColor(R.color.sixysecs));
                        }
                    }.start();
                }
            }
        });

        tile3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                if (numberCount[2] == count) {
                    count++;
                    score++;
                    info.setText(String.format(getString(R.string.info), score + ""));
                    randomNumberSort();
                    setNumberOnTiles();
                }else{
                    tile3.setBackgroundColor(Color.parseColor("#d50000"));
                    if(score > 0){
                        score--;
                        info.setText(String.format(getString(R.string.info), score + ""));
                    }
                    new CountDownTimer(100, 100){

                        @Override
                        public void onTick(long l) {}

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onFinish() {
                            tile3.setBackgroundColor(Color.WHITE);
                        }
                    }.start();
                }
            }
        });

        tile4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                if (numberCount[3] == count) {
                    count++;
                    score++;
                    info.setText(String.format(getString(R.string.info), score + ""));
                    randomNumberSort();
                    setNumberOnTiles();
                }else{
                    tile4.setBackgroundColor(Color.parseColor("#d50000"));
                    if(score > 0){
                        score--;
                        info.setText(String.format(getString(R.string.info), score + ""));
                    }
                    new CountDownTimer(100, 100){

                        @Override
                        public void onTick(long l) {}

                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onFinish() {
                            tile4.setBackgroundColor(getResources().getColor(R.color.sixysecs));
                        }
                    }.start();
                }
            }
        });
    }

    /*
    *
    * Randomly arrange 4 numbers from count into the array numberCount.
    *
     */
    private void randomNumberSort() {

        for (int x = 0; x < 4; x++) {

            numberCount[x] = count + x;
        }
        for (int x = 0; x < 4; x++) {
            int randomNumber = random.nextInt(4);
            int y = numberCount[x];
            numberCount[x] = numberCount[randomNumber];
            numberCount[randomNumber] = y;
        }
    }

    /*
    *
    * Displays the tiles on screen with the number value.
    *
     */
    private void setNumberOnTiles() {
        tile1 = (TextView) findViewById(R.id.right_top);
        tile1.setTextColor(getResources().getColor(R.color.sixysecs));
        tile1.setText(numberCount[0] + "");
        tile2 = (TextView) findViewById(R.id.left_top);
        tile2.setBackgroundColor(getResources().getColor(R.color.sixysecs));
        tile2.setText(numberCount[1] + "");
        tile3 = (TextView) findViewById(R.id.left_bottom);
        tile3.setTextColor(getResources().getColor(R.color.sixysecs));
        tile3.setText(numberCount[2] + "");
        tile4 = (TextView) findViewById(R.id.right_bottom);
        tile4.setBackgroundColor(getResources().getColor(R.color.sixysecs));
        tile4.setText(numberCount[3] + "");
    }
    /*
   *
   * Checks if the Highscore is truly the highest.
   *
    */
    private void HighScoreReview(){
        if(highScore < score){
            highScore = score;
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(highScore + "");
                writer.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        countdownTimer.cancel();
        Intent endGame = new Intent(SixtySeconds.this, EndGame.class);
        HighScoreReview();
        endGame.putExtra("HighScore", highScore);
        endGame.putExtra("Reason", getString(R.string.UserEndGame));
        endGame.putExtra("GameType", getString(R.string.SixtySec));
        endGame.putExtra("Score", score);
        endGame.putExtra("Intent", SixtySeconds.class);
        startActivity(endGame);
        finish();
    }
    @Override
    public void onPause(){
        super.onPause();
        countdownTimer.cancel();
        gameInSession = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(!gameInSession){
            finish();
        }
    }
}
