package com.example.jayanth.typinggame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginPage extends Activity {

    public static final String TAG_LOGINVIEW = "LoginPageActivity";

    EditText loginEditText;
    TextView welcomeTextView;
    Button submitButtonLoginPage;
    Button quitButtonLoginPage;

    public static String login_username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginEditText = (EditText)findViewById(R.id.loginEditText);
        welcomeTextView = (TextView)findViewById(R.id.loginTextView);
        submitButtonLoginPage = (Button)findViewById(R.id.loginSubmitButton);
        quitButtonLoginPage = (Button)findViewById(R.id.loginQuitButton);

        quitButtonLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onQuitButtonClick(v);//finish();
            }
        });

        submitButtonLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login_username = loginEditText.getText().toString();
                Intent intent = new Intent(LoginPage.this, MenuActivity.class);
                Intent intent1 = new Intent(LoginPage.this, MyActivity.class);
                intent1.putExtra("username_login", login_username);
                Log.v(TAG_LOGINVIEW, "Username = "+ login_username);

                startActivity(intent);

            }
        });


    }

    public void onQuitButtonClick(View view){

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_page, menu);
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


