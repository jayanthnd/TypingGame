package com.example.jayanth.typinggame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TopScoreActivity extends Activity {

    public static final String TAG_TOPSCORE = "TopScoreActivity";

    ListView listView;
    public String [] topScoresArray = new String[6];
    public String [] outputString = new String[6];

    public String [] topScoreNameList = new String[6];
    //String outputStringTemp;

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putStringArray("top_score_list", outputString);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);

        if (savedInstanceState != null) {
            outputString = savedInstanceState.getStringArray("top_score_list");
        }

        //ArrayList<String> outputString = new ArrayList<String>();

        //String topscores = "";

        /*Intent intent = getIntent();
        if (intent != null) {
            topscores = intent.getStringExtra("topscore-array");

        }
        */
        for (int i=0; i<6; i++) {
            Log.v(TAG_TOPSCORE, "TopscoreString = " + MyActivity.topScoresString[i]);
        }

        for (int i =0; i<6; i++){

            topScoreNameList[i] = MyActivity.login_username_list[i];
            Log.v(TAG_TOPSCORE, "Top Users = " + topScoreNameList[i]);
        }

        for (int i=0; i<6; i++){

            outputString[i] = MyActivity.randomStrings[i] + "\n" + topScoreNameList[i] + "\n" + MyActivity.topScoresString[i] + " sec";
            //outputString.add(outputStringTemp);
        }


        //for (int i=0; i<6; i++){

            //topScoresArray = String.valueOf(MyActivity.topScores);
            //topScoresArray = Double.toString(MyActivity.topScores);
        //}

        //Get ListView object from xml
        listView = (ListView)findViewById(R.id.topScoreListView);

        //Define a new Adapter
        //First parameter - Context
        //Second parameter - Layout for the row
        //Third parameter - ID of the TextView to which the data is written
        //Fourth - the Array of data
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, outputString);
        //setListAdapter(new ArrayAdapter<String>(getCallingActivity()), R.layout )

        //Assign Adapter to ListView
        listView.setAdapter(adapter);

        //listView.setAdapter(adapter1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_score, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
