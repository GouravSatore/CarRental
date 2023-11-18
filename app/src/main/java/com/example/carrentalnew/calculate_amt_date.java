package com.example.carrentalnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class calculate_amt_date extends AppCompatActivity {

    Button con_book;
    EditText Amt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_amt_date);

        int Amount, dis;
        Intent intent = getIntent();
        int charge = intent.getIntExtra("charge", 0);
        int Days = intent.getIntExtra("Days", 0);
        Amount = charge * Days * 24;
        dis = 5 * Amount / 100;
        Amount = Amount - dis;
        Amt = findViewById(R.id.amt);
        NumberFormat numberFormat = NumberFormat.getInstance();
        String formattedAmount = numberFormat.format(Amount);
        Amt.setText(formattedAmount);
        con_book = findViewById(R.id.con_book);
        int finalAmount = Amount;
        con_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Days<=6) {
                    String name = intent.getStringExtra("name");
                    Toast.makeText(calculate_amt_date.this, String.valueOf(finalAmount), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(calculate_amt_date.this, "You can't Rent for more than 6 days", Toast.LENGTH_SHORT).show();
            }
        });
    }
}