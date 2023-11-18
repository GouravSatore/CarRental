package com.example.carrentalnew;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://otp-verification-909eb-default-rtdb.firebaseio.com");
    TextView username;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username  = (TextView) findViewById(R.id.Username);
        TextView password = (TextView) findViewById(R.id.Password);
        TextView forgot = (TextView) findViewById(R.id.Forgot_Pass);
        MaterialButton login_btn = (MaterialButton) findViewById(R.id.Login_Button);
        TextView signup = (TextView) findViewById(R.id.Sign_up);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Username field is empty", Toast.LENGTH_LONG).show();
                }
                else if(password.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Password field is empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(username.getText().toString())){
                                String user = username.getText().toString();
                                String pass = String.valueOf(snapshot.child(user).child("Password").getValue(String.class));

                                if(pass.equals(password.getText().toString())){
                                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    openBooking();
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "User not registered", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openForgot();
            }
        });

    }

    public void openForgot() {
        Intent intent = new Intent(this, Forgot_pass.class);
        startActivity(intent);
    }

    public void openSignup() {

        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

    public void openBooking()
    {
        Intent intent = new Intent(this, Booking1.class);
        intent.putExtra("mobile", (String.valueOf(username)));
        startActivity(intent);
    }
}