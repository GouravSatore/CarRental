package com.example.carrentalnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class verification extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://otp-verification-909eb-default-rtdb.firebaseio.com");
    EditText val1,val2,val3,val4,val5,val6;
    Button verify;

    String getOTP,pass,con_pass,contact,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        verify = findViewById(R.id.Verify_btn);
        val1 = findViewById(R.id.OTP_1);
        val2 = findViewById(R.id.OTP_2);
        val3 = findViewById(R.id.OTP_3);
        val4 = findViewById(R.id.OTP_4);
        val5 = findViewById(R.id.OTP_5);
        val6 = findViewById(R.id.OTP_6);

        TextView num = findViewById(R.id.mobile);
        num.setText(String.format(
                "+91 %s", getIntent().getStringExtra("mobile")
        ));

        getOTP = getIntent().getStringExtra("backendotp");
        pass = getIntent().getStringExtra("pass");
        con_pass = getIntent().getStringExtra("con_pass");
        contact = getIntent().getStringExtra("mobile");
        name = getIntent().getStringExtra("name");
        ProgressBar pg_Verify = findViewById(R.id.progress_verify);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!val1.getText().toString().trim().isEmpty() &&!val2.getText().toString().trim().isEmpty() &&!val3.getText().toString().trim().isEmpty() &&!val4.getText().toString().trim().isEmpty() &&!val5.getText().toString().trim().isEmpty() &&!val6.getText().toString().trim().isEmpty()){
                    String code = val1.getText().toString() +
                            val2.getText().toString() +
                            val3.getText().toString() +
                            val4.getText().toString() +
                            val5.getText().toString() +
                            val6.getText().toString();

                    if(getOTP!=null){
                        pg_Verify.setVisibility(View.VISIBLE);
                        verify.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOTP, code
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pg_Verify.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);

                                if(task.isSuccessful()){
                                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.hasChild(contact)){
                                                Toast.makeText(verification.this, "Mobile is already registered", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                databaseReference.child("users").child(contact).child("Mobile Number").setValue(contact);
                                                databaseReference.child("users").child(contact).child("Username").setValue(name);
                                                databaseReference.child("users").child(contact).child("Password").setValue(pass);
                                                verified();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                                else{
                                    Toast.makeText(verification.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(verification.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(verification.this, "Please enter all number", Toast.LENGTH_SHORT).show();
            }
        });

        otp_move();

        TextView resend = findViewById(R.id.Resend_OTP);
        resend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verify(newbackendotp);
                            }
                        }
                );
            }
        });
    }

    private void otp_move() {
        val1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(!s.toString().trim().isEmpty()){
                    val2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        val2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(!s.toString().trim().isEmpty()){
                    val3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        val3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(!s.toString().trim().isEmpty()){
                    val4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        val4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(!s.toString().trim().isEmpty()){
                    val5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        val5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
                if(!s.toString().trim().isEmpty()){
                    val6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void verified(){

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(this, "USer Registered Successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
    public void verify(String newbackendotp){
        getOTP = newbackendotp;
        Toast.makeText(this, "OTP sended successfully", Toast.LENGTH_SHORT).show();
    }
}