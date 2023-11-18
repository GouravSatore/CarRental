package com.example.carrentalnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Forgot_pass extends AppCompatActivity {

    Button btn;
    EditText mobile;
    ProgressBar pg_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        btn = findViewById(R.id.send_otp);
        mobile = findViewById(R.id.Contact_forgot);
        pg_bar = findViewById(R.id.p_bar);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username, Mobile;
                Mobile = mobile.getText().toString();
                if(!Mobile.trim().isEmpty()) {
                    if ((Mobile.trim()).length() == 10) {
                        pg_bar.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobile.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                Forgot_pass.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        pg_bar.setVisibility(View.GONE);
                                        btn.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        pg_bar.setVisibility(View.GONE);
                                        btn.setVisibility(View.VISIBLE);
                                        Toast.makeText(Forgot_pass.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        pg_bar.setVisibility(View.GONE);
                                        btn.setVisibility(View.VISIBLE);
                                        verify(backendotp);
                                    }
                                }
                        );
                    }
                    else
                        Toast.makeText(Forgot_pass.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Forgot_pass.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void verify(String backendotp){
        mobile = findViewById(R.id.Contact_forgot);
        Intent intent = new Intent(this, forgot_verification.class);
        intent.putExtra("mobile",mobile.getText().toString());
        intent.putExtra("backendotp",backendotp);
        startActivity(intent);
    }
}