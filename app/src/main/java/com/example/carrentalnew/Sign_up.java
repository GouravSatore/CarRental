package com.example.carrentalnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Sign_up extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://otp-verification-909eb-default-rtdb.firebaseio.com");
    EditText pass,con_pass,mobile,name;
    TextInputLayout eye1,eye2;
    Button btn;
    ProgressBar pg_bar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.Name);
        pass = findViewById(R.id.Password);
        con_pass = findViewById(R.id.Confirm_Password);
        btn =findViewById(R.id.Register);
        pg_bar = findViewById(R.id.progress_otp);
        mobile = findViewById(R.id.Contact);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Username, Mobile, Pass, Con_pass;
                Username = name.getText().toString();
                Mobile = mobile.getText().toString();
                Pass = pass.getText().toString();
                Con_pass = con_pass.getText().toString();
                if(Mobile.length()<10) {
                    Toast.makeText(Sign_up.this, "Mobile number field is empty", Toast.LENGTH_SHORT).show();
                }
                else if(Username.isEmpty()){
                    Toast.makeText(Sign_up.this, "Name field is empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.trim().isEmpty()) {
                    Toast.makeText(Sign_up.this, "Password field is empty", Toast.LENGTH_SHORT).show();
                }
                else if(Con_pass.trim().isEmpty()) {
                    Toast.makeText(Sign_up.this, "Confirm password field is empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.length()<8){
                    Toast.makeText(Sign_up.this, "Password Should be atleast of 8 alphabets", Toast.LENGTH_SHORT).show();
                }
                else if(!Pass.equals(Con_pass)) {
                    Toast.makeText(Sign_up.this, "Password and Confirm Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else if(!Mobile.trim().isEmpty()){
                    if ((Mobile.trim()).length() == 10) {
                        pg_bar.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);


                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            int x=0;
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.hasChild(Mobile)) {
                                    x++;
                                    Toast.makeText(Sign_up.this, "Username already exist", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                            "+91 " + mobile.getText().toString(),
                                            60,
                                            TimeUnit.SECONDS,
                                            Sign_up.this,
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
                                                    Toast.makeText(Sign_up.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });


                    }
                    else
                        Toast.makeText(Sign_up.this, "Phone number is less than 10 digit", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void verify(String backendotp){
        Intent intent = new Intent(this, verification.class);
        mobile = findViewById(R.id.Contact);
        intent.putExtra("name",name.getText().toString());
        intent.putExtra("mobile",mobile.getText().toString());
        intent.putExtra("pass",pass.getText().toString());
        intent.putExtra("con_pass",con_pass.getText().toString());
        intent.putExtra("backendotp",backendotp);
        startActivity(intent);
    }
}

