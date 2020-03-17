package com.navarrosoft.numericsuspense;

/**
 * Created by Alejandro Navarro on 12/2/16.
 */
public class GameChoice {

    //Name of the Game Mode
    private String mGameMode;
    //The textcolor of the name of a certain Game Mode
    private int mTextColor;
    //Popup Text to be displayed when the spinner opens
    private String mInfoText;

    private Class mClass;


    /*
    * Creates a new GameChoice Object
    *
    * @param GameMode is Name of GameMode
    * @param TextColor is the string format of a color
    * @param PopupText is string that is displayed during popup
    *
     */
    public GameChoice(String GameMode, int color, String InfoText, Class object){
        mGameMode = GameMode;
        mTextColor = color;
        mInfoText = InfoText;
        mClass = object;
    }

    public String getGameMode(){
        return mGameMode;
    }

    public int getTextColor(){
        return mTextColor;
    }

    public String getInfoText(){
        return mInfoText;
    }

    public Class getNextClass(){
        return mClass;
    }

}
