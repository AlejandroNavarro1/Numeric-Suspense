package com.navarrosoft.numericsuspense;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ArithmeticGameIntermediate extends AppCompatActivity {

    private int count = 1;
    private int[] numberCount = new int[4];
    public int score = 0;
    private Random random = new Random();
    private TextView info;
    private TextView timer;
    public int time = 6000;
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


    /*
     *
     * $$$This is the first game to be made$$$Let's make history$$$
     *
     * First the program checks if there is an data file for the game. The File contains the
     * highscore. Then it set all textview to respond to clicks. Each textview setOnCLickListener
     * are the same.
     *
     */
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
                                          Intent endGame = new Intent(ArithmeticGameIntermediate.this, EndGame.class);
                                          HighScoreReview();
                                          endGame.putExtra("HighScore", highScore);
                                          endGame.putExtra("Reason", getString(R.string.UserEndGame));
                                          endGame.putExtra("GameType", getString(R.string.arithmetic));
                                          endGame.putExtra("Score", score);
                                          endGame.putExtra("Intent", ArithmeticGameIntermediate.class);
                                          startActivity(endGame);
                                      }
                                      finish();
                                  }
                              });

        file = new File(this.getFilesDir(), "Arithmetic");
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

        countdownTimer = new CountDownTimer(time, 100) {
            @Override
            public void onTick(long tick) {
                timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                time = (int)tick;
            }

            @Override
            public void onFinish() {
                EndGame(true);
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
                    countdownTimer.cancel();
                    time += 500;
                    setNumberOnTiles();
                    countdownTimer = new CountDownTimer(time, 100) {
                        @Override
                        public void onTick(long tick) {
                            timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                            time = (int)tick;
                        }

                        @Override
                        public void onFinish() {
                            EndGame(true);
                        }
                    }.start();
                } else {
                    EndGame(false);
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
                    countdownTimer.cancel();
                    time += 500;
                    setNumberOnTiles();
                    countdownTimer = new CountDownTimer(time, 100) {
                        @Override
                        public void onTick(long tick) {
                            timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                            time = (int)tick;
                        }

                        @Override
                        public void onFinish() {
                            EndGame(true);
                        }
                    }.start();

                } else {
                    EndGame(false);
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
                    countdownTimer.cancel();
                    time += 500;
                    setNumberOnTiles();
                    countdownTimer = new CountDownTimer(time, 100) {
                        @Override
                        public void onTick(long tick) {
                            timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                            time = (int)tick;
                        }

                        @Override
                        public void onFinish() {
                            EndGame(true);
                        }
                    }.start();
                } else {
                    EndGame(false);
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
                    countdownTimer.cancel();
                    time += 500;
                    setNumberOnTiles();
                    countdownTimer = new CountDownTimer(time, 100) {
                        @Override
                        public void onTick(long tick) {
                            timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                            time = (int)tick;
                        }

                        @Override
                        public void onFinish() {
                            EndGame(true);
                        }
                    }.start();
                } else {
                    EndGame(false);
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
        tile1.setTextColor(getResources().getColor(R.color.mathBlue));
        tile1.setText(numberCount[0] + "");
        tile2 = (TextView) findViewById(R.id.left_top);
        tile2.setBackgroundColor(getResources().getColor(R.color.mathBlue));
        tile2.setText(numberCount[1] + "");
        tile3 = (TextView) findViewById(R.id.right_bottom);
        tile3.setBackgroundColor(getResources().getColor(R.color.mathBlue));
        tile3.setText(numberCount[2] + "");
        tile4 = (TextView) findViewById(R.id.left_bottom);
        tile4.setTextColor(getResources().getColor(R.color.mathBlue));
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
        if(endGameVerify == 0) {
            endGameVerify++;
            Intent endGame = new Intent(ArithmeticGameIntermediate.this, EndGame.class);
            HighScoreReview();
            endGame.putExtra("HighScore", highScore);
            endGame.putExtra("Reason", getString(R.string.UserEndGame));
            endGame.putExtra("GameType", getString(R.string.arithmetic));
            endGame.putExtra("Score", score);
            endGame.putExtra("Intent", ArithmeticGameIntermediate.class);
            startActivity(endGame);
        }
        finish();
    }

    public void EndGame(boolean endedOnTime){
        countdownTimer.cancel();
        if(endGameVerify == 0) {
            endGameVerify++;
            if (endedOnTime) {
                Intent endGame = new Intent(ArithmeticGameIntermediate.this, EndGame.class);
                HighScoreReview();
                endGame.putExtra("HighScore", highScore);
                endGame.putExtra("Reason", getString(R.string.OutofTime));
                endGame.putExtra("GameType", getString(R.string.arithmetic));
                endGame.putExtra("Intent", ArithmeticGameIntermediate.class);
                endGame.putExtra("Score", score);
                startActivity(endGame);
                finish();
            } else {
                Intent endGame = new Intent(ArithmeticGameIntermediate.this, EndGame.class);
                HighScoreReview();
                endGame.putExtra("HighScore", highScore);
                endGame.putExtra("Reason", getString(R.string.WrongTile));
                endGame.putExtra("Score", score);
                endGame.putExtra("GameType", getString(R.string.arithmetic));
                endGame.putExtra("Intent", ArithmeticGameIntermediate.class);
                startActivity(endGame);
                finish();
            }
        }
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

