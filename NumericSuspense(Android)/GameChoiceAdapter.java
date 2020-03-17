package com.navarrosoft.numericsuspense;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by student on 12/2/16.
 */
public class GameChoiceAdapter extends ArrayAdapter<GameChoice> {

    private MediaPlayer mediaPlayer;

    public GameChoiceAdapter(Context context, ArrayList<GameChoice> gameChoices) {
        super(context ,0 ,gameChoices);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

         View listGameChoice = convertView;
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.button_click);
        if(listGameChoice == null){
            listGameChoice = LayoutInflater.from(getContext()).inflate(R.layout.game_view, parent, false);
        }

        GameChoice game = getItem(position);

        TextView gameChoiceView = (TextView) listGameChoice.findViewById(R.id.game_choice_view);

        gameChoiceView.setText(game.getGameMode());
        gameChoiceView.setTextColor(getContext().getResources().getColor(game.getTextColor()));
        gameChoiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                GameChoice game = getItem(position);
                getContext().startActivity(new Intent(getContext(), game.getNextClass()));
            }
        });

        TextView questionButton = (TextView)listGameChoice.findViewById(R.id.question_text_view);
        final View finalListGameChoice = listGameChoice;
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
                TextView help = (TextView) finalListGameChoice.findViewById(R.id.text_view_help);
                GameChoice game = getItem(position);
                if(help.getText().equals(getContext().getString(R.string.click_up_here))){
                    help.setText(game.getInfoText());
                }
                else{
                    help.setText(getContext().getString(R.string.click_up_here));
                }
            }
        });

        return listGameChoice;
    }

}
