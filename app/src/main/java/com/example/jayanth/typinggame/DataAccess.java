package com.example.jayanth.typinggame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

/**
 * Created by Jayanth Nallapothula on 9/8/14.
 * Class to Access the Data from the database.
 * Alter the return type of the functions as per functionality
 */
public class DataAccess {

    SQLiteHelper.MySQLiteHelper dbhelper;
    SQLiteHelper sqLiteHelper;

    public DataAccess(Context context) {
        dbhelper = new SQLiteHelper.MySQLiteHelper(context);
        Message.message(context, "DataAccess Constructor called");
    }

    protected void onCreate(Bundle savedInstanceState){

    }

    public long insertData(String randString, Double topScore, String playerName) throws SQLiteException{

        long id = sqLiteHelper.insertData(randString, topScore, playerName);
        return id;

    }

    public String getData(){
       String buffer = sqLiteHelper.getAllData();
        return buffer;
    }
}

