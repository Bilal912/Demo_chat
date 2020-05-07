package com.example.demo_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
EditText email,pass,username;
Button button;

FirebaseAuth auth;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email  =findViewById(R.id.email);
        pass = findViewById(R.id.password);
        button = findViewById(R.id.button);
        username = findViewById(R.id.user);

        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = email.getText().toString();
                String Username = username.getText().toString();
                String Password = pass.getText().toString();

                if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Username) || TextUtils.isEmpty(Password)){
                    Toast.makeText(RegisterActivity.this,"Yeh to ni hoga",Toast.LENGTH_LONG).show();
                }
                else {

                    register(Email,Username,Password);

                }

            }
        });


    }

    private void register(final String email, final String username, final String password) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           FirebaseUser firebaseUser = auth.getCurrentUser();
                           assert firebaseUser != null;
                           String userid = firebaseUser.getUid();

                           reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                           HashMap<String , String> hashMap= new HashMap<>();
                           hashMap.put("id",userid);
                           hashMap.put("username",username);
                           hashMap.put("email",email);
                           hashMap.put("password",password);
                           hashMap.put("imageURL","https://upload.wikimedia.org/wikipedia/commons/7/7c/User_font_awesome.svg");


                           reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if (task.isSuccessful()){
                                       Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                       startActivity(intent);
                                   }
                               }
                           });

                       }
                       else {
                           Toast.makeText(RegisterActivity.this, "Ab ye to kisi kitaab ma ni likha",Toast.LENGTH_LONG).show();
                       }
                    }
                });
    }
}
