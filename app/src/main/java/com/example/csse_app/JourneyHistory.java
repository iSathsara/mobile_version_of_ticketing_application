package com.example.csse_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JourneyHistory extends AppCompatActivity {

    //String[] journeyHistory = {"J1","j2","j3","J4","J5","J6","J7","J8"};
    private Button backBtn;
    private TextView journeyResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_history);

        journeyResults = findViewById(R.id.label);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ticketing-network-system-api.herokuapp.com/") // base URL of API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                if (!response.isSuccessful()) {
                    journeyResults.setText("Code: " + response.code());
                    return;
                }

                List<Posts> posts = response.body();

                for (Posts post : posts) {
                    String content = "";
                    content += "Date: " + post.getJdate().substring(0,10) + "\n";
                    content += "Start Point: " + post.getJstartPosition() + "\n";
                    content += "End Point: " + post.getJendPosition() + "\n";
                    content += "Fare: " + post.getJfare() + "\n\n";
                    journeyResults.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                journeyResults.setText(t.getMessage());
            }
        });

        /*
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_view,journeyHistory);
        ListView listView = findViewById(R.id.journey_history);
        listView.setAdapter(adapter);
        */

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
