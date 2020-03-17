package com.navarrosoft.numericsuspense;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Random;

/**
 * Created by Alejandro Navarro on 11/23/16.
 */

public class SumToTen extends AppCompatActivity {

    private int[] numberList;
    private int numberOne = 0, numberTwo = 0;
    private TextView tile1, tile2, tile3, tile4, timer, info;
    private int[][] SumsOfTens = {{1,9},{2,8},{3,7},{4,6},{5,5}};
    private int time = 5000;
    private CountDownTimer countDownTimer;
    private File file;
    private int score = 0;
    private int highscore = 0;
    private Random random = new Random();
    private boolean t1isclicked = true,t2isclicked = true,t3isclicked = true,t4isclicked = true;
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
                countDownTimer.cancel();
                EndGameObj(true);
            }
        });

        file = new File(this.getFilesDir(), "SumToTen");

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
                highscore = Integer.parseInt(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
            highscore = 0;
        }

        timer = (TextView)findViewById(R.id.timer);
        info = (TextView)findViewById(R.id.info);
        info.setText(String.format(getString(R.string.info), score + ""));
        tile2 = (TextView) findViewById(R.id.left_top);
        tile1 = (TextView) findViewById(R.id.right_top);
        tile4 = (TextView) findViewById(R.id.left_bottom);
        tile3 = (TextView) findViewById(R.id.right_bottom);

       CountDown();
        RandomNumbersList();
        SetTiles();

        tile1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(numberOne == 0){
                    numberOne = numberList[0];
                    tile1.setBackgroundResource(R.drawable.sumto10borderwhite);
                    t1isclicked = false;
                }else if(t1isclicked){
                    tile1.setBackgroundResource(R.drawable.sumto10borderwhite);
                    numberTwo = numberList[0];
                    if(numberOne + numberTwo == 10){
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                        countDownTimer.cancel();
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                score++;
                                info.setText(String.format(getString(R.string.info), score + ""));
                                time += 1000;
                                RandomNumbersList();
                                SetTiles();
                                CountDown();
                            }
                        }.start();

                    }
                    else if(numberOne + numberTwo != 10){
                        countDownTimer.cancel();
                        tile1.setBackgroundResource(R.drawable.sumto10wrong);
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                time -= 1000;
                                SetTiles();
                                CountDown();
                            }
                        }.start();
                    }
                }
            }
        });

        tile2.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(numberOne == 0){
                    numberOne = numberList[1];
                    tile2.setBackgroundResource(R.drawable.sumto10border);
                    t2isclicked = false;
                }else if(t2isclicked){
                    tile2.setBackgroundResource(R.drawable.sumto10border);
                    numberTwo = numberList[1];
                    if(numberOne + numberTwo == 10){
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                        countDownTimer.cancel();
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                score++;
                                info.setText(String.format(getString(R.string.info), score + ""));
                                time += 1000;
                                RandomNumbersList();
                                SetTiles();
                                CountDown();
                            }
                        }.start();


                    }
                    else if(numberOne + numberTwo != 10){
                        countDownTimer.cancel();
                        tile2.setBackgroundResource(R.drawable.sumto10wrong);
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                time -= 1000;
                                SetTiles();
                                CountDown();
                            }
                        }.start();
                    }
                }
            }
        });

        tile3.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(numberOne == 0){
                    numberOne = numberList[2];
                    tile3.setBackgroundResource(R.drawable.sumto10border);
                    t3isclicked = false;
                }else if(t3isclicked){
                    tile3.setBackgroundResource(R.drawable.sumto10border);
                    numberTwo = numberList[2];
                    if(numberOne + numberTwo == 10){
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                        countDownTimer.cancel();
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                score++;
                                info.setText(String.format(getString(R.string.info), score + ""));
                                time += 1000;
                                RandomNumbersList();
                                SetTiles();
                                CountDown();
                            }
                        }.start();


                    }
                    else if(numberOne + numberTwo != 10){
                        countDownTimer.cancel();
                        tile3.setBackgroundResource(R.drawable.sumto10wrong);
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                time -= 1000;
                                SetTiles();
                                CountDown();
                            }
                        }.start();
                    }
                }
            }
        });

        tile4.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(numberOne == 0){
                    numberOne = numberList[3];
                    tile4.setBackgroundResource(R.drawable.sumto10borderwhite);
                    t4isclicked = false;
                }else if(t4isclicked){
                    numberTwo = numberList[3];
                    tile4.setBackgroundResource(R.drawable.sumto10borderwhite);
                    if(numberOne + numberTwo == 10){
                        mediaPlayer.seekTo(0);
                        mediaPlayer.start();
                        countDownTimer.cancel();
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                score++;
                                info.setText(String.format(getString(R.string.info), score + ""));
                                time += 1000;
                                RandomNumbersList();
                                SetTiles();
                                CountDown();
                            }
                        }.start();


                    }
                    else if(numberOne + numberTwo != 10){
                        countDownTimer.cancel();
                        tile4.setBackgroundResource(R.drawable.sumto10wrong);
                        countDownTimer = new CountDownTimer(100, 100) {
                            @Override
                            public void onTick(long l) {

                            }

                            @Override
                            public void onFinish() {
                                time -= 1000;
                                SetTiles();
                                CountDown();
                            }
                        }.start();

                    }
                }
            }
        });
    }



    /*
     *
     * Generates random arrays of numbers using the SumsOfTen variable
     *
     */
    private void RandomNumbersList(){
        int pos1,pos2,pos;
        pos = random.nextInt(5);
        pos1 = random.nextInt(5);
        if(pos1 == pos)pos1 = pos == 0 ? pos1 + 1 : pos1 - 1;
        pos2 = random.nextInt(5);
        if(pos1 == pos2 || pos2 == pos)while(pos1 == pos2 || pos2 == pos)pos2 = random.nextInt(5);
        int[] randomNumberList = new int[]{SumsOfTens[pos][0], SumsOfTens[pos][1],
                SumsOfTens[pos1][random.nextInt(1)],SumsOfTens[pos2][random.nextInt(1)]};
        for(int x = 0; x < 6; x++){
            int changePos = random.nextInt(4);
            int data = randomNumberList[changePos];
            randomNumberList[changePos] = randomNumberList[0];
            randomNumberList[0] = data;
        }

        numberList = randomNumberList;
    }

    /*
     *
     * Resets all the tiles
     *
     */
    private void SetTiles(){
        t1isclicked = true; t3isclicked = true; t4isclicked = true; t2isclicked = true;
        numberOne = 0; numberTwo = 0;
        tile1.setTextColor(getResources().getColor(R.color.Sum10));
        tile1.setBackgroundColor(Color.WHITE);
        tile2.setBackgroundColor(getResources().getColor(R.color.Sum10));
        tile4.setTextColor(getResources().getColor(R.color.Sum10));
        tile4.setBackgroundColor(Color.WHITE);
        tile3.setBackgroundColor(getResources().getColor(R.color.Sum10));
        tile1.setText(numberList[0] + "");
        tile2.setText(numberList[1] + "");
        tile3.setText(numberList[2] + "");
        tile4.setText(numberList[3] + "");
    }

    /*
     *
     * Resets the CountDownTimer to start over with the respects to the $time variable
     *
     */
    private void CountDown(){
        countDownTimer = new CountDownTimer(time , 100) {
            @Override
            public void onTick(long tick) {
                timer.setText(String.format(getString(R.string.time), ((double) tick / 1000.0) + ""));
                time = (int) tick;
            }

            @Override
            public void onFinish() {
               EndGameObj(false);
            }
        }.start();
    }

    /*
     *
     * Sends the game results and information to the next activity
     *
     */
    private void EndGameObj(boolean UserEndedGame){
        countDownTimer.cancel();
        if(endGameVerify == 0) {
            endGameVerify++;
            HighScoreReview();
            Intent endGame = new Intent(SumToTen.this, EndGame.class);
            endGame.putExtra("HighScore", highscore);
            if (UserEndedGame) {
                endGame.putExtra("Reason", getString(R.string.UserEndGame));
            } else {
                endGame.putExtra("Reason", getString(R.string.OutofTime));
            }
            endGame.putExtra("GameType", getString(R.string.SumToTen));
            endGame.putExtra("Score", score);
            endGame.putExtra("Intent", SumToTen.class);
            startActivity(endGame);
            finish();
        }finish();
    }

    /*
   *
   * Checks if the Highscore is truly the highest.
   *
    */
    private void HighScoreReview(){
        if(highscore < score){
            highscore = score;
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(highscore + "");
                writer.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed(){
        countDownTimer.cancel();
        EndGameObj(true);
    }


    @Override
    public void onPause(){
        super.onPause();
        countDownTimer.cancel();
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
