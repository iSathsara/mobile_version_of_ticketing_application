package com.example.csse_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class JourneyHistory extends AppCompatActivity {

    String[] journeyHistory = {"J1","j2","j3","J4","J5","J6","J7","J8"};
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_history);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_view,journeyHistory);
        ListView listView = findViewById(R.id.journey_history);
        listView.setAdapter(adapter);

        backBtn = findViewById(R.id.back_to_dashboard);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDashboard();
            }
        });

    }

    private void gotoDashboard(){
        Intent intent = new Intent(JourneyHistory.this, MainActivity.class);
        startActivity(intent);
    }


}
