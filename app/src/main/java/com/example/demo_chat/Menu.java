package com.example.demo_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo_chat.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {
CircleImageView imageView;
TextView username;

FirebaseUser firebaseUser;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageView = findViewById(R.id.image);
        username = findViewById(R.id.username);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());

                imageView.setImageResource(R.mipmap.ic_launcher);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
        finish();
    }
}
