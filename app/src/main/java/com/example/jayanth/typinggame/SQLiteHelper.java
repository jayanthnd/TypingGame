package com.example.jayanth.typinggame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jayanth Nallapothula on 9/8/14.
 * This class is the SQLite Helper class
 */
public class SQLiteHelper {

    MySQLiteHelper helper;

    public SQLiteHelper(Context context) {
        this.helper = new MySQLiteHelper(context);
    }

    public long insertData(String randString, Double topScore, String playerName){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySQLiteHelper.COLUMN_RANDOMSTRING, randString);
        contentValues.put(MySQLiteHelper.COLUMN_TOPSCORE, topScore);
        contentValues.put(MySQLiteHelper.COLUMN_PLAYER, playerName);

        long id = db.insert(MySQLiteHelper.TABLE_NAME, null, contentValues);
        // id indicates the Row id. Null if error executing
        return id;
    }

    public String getAllData(){

        SQLiteDatabase db = helper.getWritableDatabase();
        String [] columns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_RANDOMSTRING,
                MySQLiteHelper.COLUMN_TOPSCORE, MySQLiteHelper.COLUMN_PLAYER};
        Cursor cursor = db.query(MySQLiteHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            String randstring = cursor.getString(1);
            Double topscore = cursor.getDouble(2);
            String pname = cursor.getString(3);
            buffer.append(cid + " " + randstring + " " + topscore + " " + pname + "\n");
        }
        return buffer.toString();
        // Check if an arrayList can be created of type cursor and directly displayed as a ListView
        // Modify return type as per functionality.
    }

    public void updateTopScore(Double topScore, String playerName){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.COLUMN_TOPSCORE, topScore);
        contentValues.put(MySQLiteHelper.COLUMN_PLAYER, playerName);

       // db.update(MySQLiteHelper.TABLE_NAME, contentValues, );
    }
    /*public String getSpecificData(Pass parameters here ) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String [] selectedColumns = {};
        String [] selectionArgs = { Parameters here };
        Cursor cursor = db.query(MySQLiteHelper.TABLE_NAME, selectedColumns,
                MySQLiteHelper.COLUMN_RANDOMSTRING + "=? AND " + MySQLiteHelper.COLUMN_TOPSCORE +
        " =? AND " + MySQLiteHelper.COLUMN_PLAYER + "=? AND " ,selectionArgs, null , null, null,
                null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            // Save all the data from the Cursor object
            //  All function calls are incomplete.
            //  Use it to Advantage and needs
            buffer.append();
        }
        return  buffer.toString();
    }*/



    public void update(){


    }
    static class MySQLiteHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "topScores.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "Scores";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_RANDOMSTRING = "strings";
        private static final String COLUMN_TOPSCORE = "topscore";
        private static final String COLUMN_PLAYER = "name";

        public static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COLUMN_RANDOMSTRING +
                " VARCHAR(255) " + COLUMN_PLAYER + " VARCHAR(255) " + COLUMN_TOPSCORE +
                " DOUBLE);";
        private Context context;

        public MySQLiteHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Message.message(context, "Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                Message.message(context, ""+e);
                e.printStackTrace();
            }
            Message.message(context, "onCreate called");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

            Message.message(context, "onUpgrade called");

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }

}
