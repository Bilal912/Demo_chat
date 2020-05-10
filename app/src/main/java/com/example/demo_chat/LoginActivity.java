package com.example.demo_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
Button button;
EditText email,pass;

FirebaseAuth auth;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = findViewById(R.id.login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                String Password = pass.getText().toString();

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)){
                    Toast.makeText(LoginActivity.this,"Data is necessary",Toast.LENGTH_LONG).show();
                }
                else if (Password.length() < 6){
                    Toast.makeText(LoginActivity.this,"Password must be 6 characters",Toast.LENGTH_LONG).show();
                }
                else {
                    login(Email,Password);
                }
            }
        });
    }

    private void login(String email, String password) {

        final android.app.AlertDialog loading = new ProgressDialog(LoginActivity.this);
        loading.setMessage("Loading...");
        loading.show();
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          loading.dismiss();
                          Intent intent = new Intent(LoginActivity.this,Menu.class);
                          startActivity(intent);
                          finish();
                      }
                      else {
                          loading.dismiss();
                          Toast.makeText(LoginActivity.this,"Aisa to ni hota",Toast.LENGTH_LONG).show();

                      }
                    }
                });
    }
}
