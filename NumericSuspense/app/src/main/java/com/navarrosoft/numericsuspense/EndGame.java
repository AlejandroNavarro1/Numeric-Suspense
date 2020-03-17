package com.navarrosoft.numericsuspense;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class EndGame extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    boolean menuAd = false;
    boolean playAgainAd = false;
    private int highScore = 0;
    private int score = 0;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_click);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3186408820394073/9807355543");
        requestNewInterstitial();

        MobileAds.initialize(getApplicationContext(), getString(R.string.google_app_id));
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (menuAd) {
                    finish();
                } else if (playAgainAd) {
                    startActivity(new Intent(EndGame.this, (Class<?>) getIntent().getExtras().get("Intent")));
                    finish();
                }
            }
        });


        final Intent lastIntent = getIntent();
        String reason = lastIntent.getStringExtra("Reason");
        String gametype = lastIntent.getStringExtra("GameType");
        score = lastIntent.getExtras().getInt("Score");
        highScore = lastIntent.getExtras().getInt("HighScore");

        int[] txtViewSize = setTextViewSize();
        TextView reasonView = (TextView) findViewById(R.id.reason_of_loss);
        reasonView.setTextSize(txtViewSize[1]);

        TextView gametypeView = (TextView) findViewById(R.id.game_type);
        gametypeView.setTextSize(txtViewSize[0]);

        TextView scoreView = (TextView) findViewById(R.id.OverAll_Score);
        scoreView.setTextSize(txtViewSize[3]);

        TextView highScoreView = (TextView) findViewById(R.id.High_Score_View);
        highScoreView.setTextSize(txtViewSize[2]);

        TextView yourScoreView = (TextView)findViewById(R.id.Your_score);
        yourScoreView.setTextSize(txtViewSize[1]);

        Button menuButton = (Button) findViewById(R.id.menu_button);
        menuButton.setTextSize(txtViewSize[1]);

        Button againButton = (Button) findViewById(R.id.playagain_button);
        againButton.setTextSize(txtViewSize[1]);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                if (score <= highScore / 2 && mInterstitialAd.isLoaded()) {
                    menuAd = true;
                    mInterstitialAd.show();
                } else {
                    finish();
                }
            }
        });

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();

                if (score >= highScore / 2 && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    playAgainAd = true;
                } else {
                    startActivity(new Intent(EndGame.this, (Class<?>) getIntent().getExtras().get("Intent")));
                    finish();
                }

            }
        });

        highScoreView.setText(getString(R.string.High_Score) + " " + highScore);
        gametypeView.setText(gametype);
        reasonView.setText(reason);
        scoreView.setText(score + "");

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (score <= highScore / 2 && mInterstitialAd.isLoaded()) {
            menuAd = true;
            mInterstitialAd.show();
        } else {
            finish();
        }
    }

    public int[] setTextViewSize(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double parentWitdh = displayMetrics.widthPixels;
        int gameTypeSize = (int)(parentWitdh / 27);
        int reasonAndYourScoreSize = (int)(parentWitdh / 54);
        int highScoreSize = (int)(parentWitdh / 43.2);
        int scoreSize = (int)(parentWitdh / 7.2);
        return new int[]{gameTypeSize, reasonAndYourScoreSize, highScoreSize, scoreSize};
    }
}
