package com.example.carrentalnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class new_pass extends AppCompatActivity {

    DatabaseReference databaseReference;

    Button save;
    TextInputLayout newPass, newConPass;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);

        databaseReference = FirebaseDatabase.getInstance().getReference("users"); // Update "users" with your desired database reference

        save = findViewById(R.id.Save);
        newPass = findViewById(R.id.new_password);
        newConPass = findViewById(R.id.new_con_pass);
        mobile = getIntent().getStringExtra("mobile");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = newPass.getEditText().getText().toString();
                String con_pass = newConPass.getEditText().getText().toString();

                if (!pass.equals(con_pass)) {
                    Toast.makeText(new_pass.this, "Password and Confirm Password don't match", Toast.LENGTH_SHORT).show();
                } else {
                    updatePassword(pass);
                    openMain();
                }
            }
        });
    }

    private void updatePassword(String password) {
        databaseReference.child(mobile).child("Password").setValue(password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(new_pass.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(new_pass.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
