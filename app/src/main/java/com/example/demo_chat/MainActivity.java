package com.example.demo_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //check user is login or not
        if (firebaseUser != null){
            startActivity(new Intent(MainActivity.this,Menu.class));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));

    }
}
