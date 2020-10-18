package com.example.csse_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recharge extends AppCompatActivity {

    private Button proceed;
    final Calendar myCalendar = Calendar.getInstance();
    private EditText expDate;
    private EditText editTextCardNo;
    private EditText editTextCvc;
    private EditText editTextAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        // initialize with corresponding IDs
        proceed = (Button) findViewById(R.id.recharge_proceed_btn);
        expDate = findViewById(R.id.credit_expire_input);
        editTextCardNo = (EditText) findViewById(R.id.credit_card_no_input);
        editTextCvc = (EditText) findViewById(R.id.credit_cvc_input);
        editTextAmount = (EditText) findViewById(R.id.credit_amount_input);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveDetails(createRequest());

            }
        });

        expDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Recharge.this, date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public UserRequests createRequest(){

        UserRequests userRequests = new UserRequests();

        userRequests.setCardnum(editTextCardNo.getText().toString());
        userRequests.setCvv(editTextCvc.getText().toString());
        userRequests.setExdate(expDate.getText().toString());
        userRequests.setAmount(editTextAmount.getText().toString());

        return userRequests;
    }

    public void saveDetails(UserRequests userRequests){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveDetails(userRequests);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    showAlert();
                    editTextCardNo.setText("");
                    editTextCvc.setText("");
                    editTextAmount.setText("");
                    //startActivity(new Intent(Recharge.this, MainActivity.class));
                }
                else{
                    Toast.makeText(Recharge.this,"Recharge Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Recharge.this,"Recharge Failed "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int date) {
            myCalendar.set(Calendar.YEAR,year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,date);
            updateLabel();
        }
    };

    private void updateLabel(){
        String dateFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
        expDate.setText(sdf.format(myCalendar.getTime()));

    }

    // display alert dialog
    private void showAlert(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Successful");
        alert.setIcon(R.drawable.ic_complete);
        alert.setMessage("Your Smartcard has recharged successfully.");
        alert.setPositiveButton("DONE", null);

        AlertDialog dialog =alert.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.alert_design);

        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // redirect to dashboard
                Intent i = new Intent(Recharge.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

}
