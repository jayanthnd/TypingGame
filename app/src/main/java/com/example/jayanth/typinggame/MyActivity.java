package com.example.jayanth.typinggame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MyActivity extends Activity {

    EditText inputTextBox;
    TextView refString;
    Button submitButton;
    Button quitButton;
    TextView setTime;

    private static final int READY_DIALOG = 1;

    double startTime;
    double endTime;
    double timeTaken;
    double topScore;
    double compareScore;
    String newTimeSet;
    String reference;
    int randomNumber;

    private String [] randomStrings = new String[6];
    private static final int CORRECT_DIALOG = 2;
    private static final int INCORRECT_DIALOG = 3;

    Random randomGenerator = new Random();

    void populateStrings(){

        randomStrings[0] = "The quick brown fox jumps over the lazy dog";
        randomStrings[1] = "I have no option but to delete this text";
        randomStrings[2] = "The weirdest cookie in the world crumbles too quick";
        randomStrings[3] = "The shit is way too serious to get real, you know?";
        randomStrings[4] = "On this date, in this place, the world will change forever";
        randomStrings[5] = "Hi";

    }

    int getRandomNumber(){

        randomNumber =  randomGenerator.nextInt(5);
        randomNumber -= 1;
        return randomNumber;
    }

    void setRefString(){

        randomNumber = getRandomNumber();
        reference = randomStrings[randomNumber];
        refString.setText(reference);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        inputTextBox = (EditText)findViewById(R.id.inputEditText);
        submitButton = (Button)findViewById(R.id.submitButton);
        quitButton = (Button)findViewById(R.id.quitButton);
        refString = (TextView)findViewById(R.id.inputTextView);
        setTime = (TextView)findViewById(R.id.setTimeTextView);

        refString.setText("");
        populateStrings();

        topScore = 20;
        compareScore = 0;



        quitButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                onQuitButtonClick(v);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitButtonClick(v);
            }
        });

        showDialog(READY_DIALOG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    public void onSubmitButtonClick(View view){

        setRefString();

        String input = inputTextBox.getText().toString();
        String referencenew = refString.getText().toString();//"The quick brown fox jumps over the lazy dog";//

        if(input.equals(referencenew)){

            endTime = System.currentTimeMillis();
            timeTaken = (endTime - startTime) / 1000;
            removeDialog(CORRECT_DIALOG);
            showDialog(CORRECT_DIALOG);
            //Toast.makeText(this, "That's Right! You took " + timeTaken + " seconds", Toast.LENGTH_LONG).show();

        } else {
            removeDialog(INCORRECT_DIALOG);
            showDialog(INCORRECT_DIALOG);
            //Toast.makeText(this, "That's Not Right!!!!", Toast.LENGTH_LONG).show();
        }

    }

    public void onQuitButtonClick(View view){

        finish();
    }

    protected Dialog onCreateDialog(int id){
        if (id == READY_DIALOG){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_ready);

            builder.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startTime = System.currentTimeMillis();
                    inputTextBox.setText("");
                    setRefString();
                    dialog.cancel();

                }
            });
            return builder.create();
        } else if (id == CORRECT_DIALOG){

            endTime = System.currentTimeMillis();
            timeTaken = (endTime - startTime) / 1000;
            compareScore = timeTaken;
            newTimeSet = String.valueOf(timeTaken);
            setTime.setText(newTimeSet);

            AlertDialog.Builder builderCorrect = new AlertDialog.Builder(this);

            if(compareScore < topScore) {

                builderCorrect.setMessage("Congratulations!!! We have a new high score of " + timeTaken + " seconds! Click Yes to play again");
                topScore = compareScore;
            } else {

                builderCorrect.setMessage("That's Right! " + "You took " + timeTaken + " seconds! Click Yes to play again");
            }

            builderCorrect.setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    startTime = System.currentTimeMillis();
                    inputTextBox.setText("");
                    setRefString();
                    dialog.cancel();

                }
            });

            builderCorrect.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });
            return builderCorrect.create();

        } else if (id == INCORRECT_DIALOG){

            final AlertDialog.Builder builderIncorrect = new AlertDialog.Builder(this);
            //builderIncorrect.setMessage(R.string.incorrectString);
            builderIncorrect.setMessage("That's Incorrect! Click Ok to try again");
            builderIncorrect.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startTime = System.currentTimeMillis();
                    inputTextBox.setText("");
                    dialog.cancel();
                }
            });
            builderIncorrect.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });
            return builderIncorrect.create();
        } else {

            return null;
        }
    }


}
