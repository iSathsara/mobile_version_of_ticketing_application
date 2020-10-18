package com.example.csse_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button helpBtn;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_btn);
        helpBtn = findViewById(R.id.help_btn);
        username = findViewById(R.id.login_input);
        password = findViewById(R.id.password_input);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("982652111v") && password.getText().toString().equals("admin")){
                    gotoDashboard();
                    username.setText("");
                    password.setText("");
                    finish();
                }
                else{
                    //Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    Snackbar snackbar = Snackbar.make(view, "Username or Password is incorrect. Please try again", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    username.setText("");
                    password.setText("");
                }

            }
        });

        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoHelp();
            }
        });


    }

    private void gotoDashboard(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void gotoHelp(){
        Uri uri = Uri.parse("http://google.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
