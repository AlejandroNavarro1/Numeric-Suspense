package com.navarrosoft.numericsuspense;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ArrayList<GameChoice> GameLists = new ArrayList<GameChoice>();
        GameLists.add(new GameChoice(getString(R.string.arithmetic),R.color.mathBlue, getString(R.string.arithmetic_help), ArithmeticGameIntermediate.class));
        GameLists.add(new GameChoice(getString(R.string.SumToTen), R.color.Sum10, getString(R.string.sum_to_ten_help), SumToTen.class));
        GameLists.add(new GameChoice(getString(R.string.Backwards), R.color.backwards, getString(R.string.backward_help), Backwards.class));
        GameLists.add(new GameChoice(getString(R.string.SixtySec), R.color.sixysecs, getString(R.string.sixtysec_help), SixtySeconds.class));

        GameChoiceAdapter choiceAdapter = new GameChoiceAdapter(this, GameLists);

        ListView list = (ListView)findViewById(R.id.listview_games);
        list.setAdapter(choiceAdapter);

    }
}
