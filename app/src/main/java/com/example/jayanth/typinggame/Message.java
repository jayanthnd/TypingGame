package com.example.jayanth.typinggame;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jayanth Nallapothula on 9/8/14.
 * This class is used for debugging that creates a Toast for every any message you
 * wish to pass
 */
public class Message {

    public static void message(Context context, String messageText){
        Toast.makeText(context, messageText, Toast.LENGTH_LONG).show();
    }
}
