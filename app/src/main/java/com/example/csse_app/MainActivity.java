package com.example.csse_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button recharge;
    private Button history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recharge = (Button) findViewById(R.id.recharge_btn);
        history = (Button) findViewById(R.id.history_btn);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recharge();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history();
            }
        });

    }

    private void recharge(){
        Intent intent = new Intent(MainActivity.this, Recharge.class);
        startActivity(intent);
    }

    private void history(){
        Intent intent = new Intent(MainActivity.this, JourneyHistory.class);
        startActivity(intent);
    }

}
