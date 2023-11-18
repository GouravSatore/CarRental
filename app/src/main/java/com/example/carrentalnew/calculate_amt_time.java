package com.example.carrentalnew;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class calculate_amt_time extends AppCompatActivity {

    EditText Amount;
    Button con_book;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_amt);

        Intent intent = getIntent();

        con_book = findViewById(R.id.con_book);
        con_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                Intent intent = getIntent();
                name = intent.getStringExtra("name");
                Toast.makeText(calculate_amt_time.this, name, Toast.LENGTH_SHORT).show();
            }
        });

    }
}