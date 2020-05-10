package com.example.demo_chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo_chat.Model.Message_model;
import com.example.demo_chat.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MessagingActivty extends AppCompatActivity {
TextView textView,Send;
EditText editText;
RecyclerView recyclerView;
Message_adapter message_adapter;

DatabaseReference reference;
FirebaseUser firebaseUser;
List<Message_model> message_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_activty);

        recyclerView = findViewById(R.id.recycler_id);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        textView = findViewById(R.id.username);
        editText= findViewById(R.id.img);
        Send  = findViewById(R.id.message);

        final String id = getIntent().getStringExtra("id");
//
//        String name = getIntent().getStringExtra("Name");
//        textView.setText(name);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;

        assert id != null;
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                textView.setText(user.getUsername());

                readmessage(firebaseUser.getUid(),id);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 //       Toast.makeText(MessagingActivty.this,firebaseUser.getUid()+" - "+id,Toast.LENGTH_LONG).show();

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editText.getText().toString();
                if (TextUtils.isEmpty(message)){
                    Toast.makeText(MessagingActivty.this,"Write your message first",Toast.LENGTH_LONG).show();
                }
                else {
                    sendMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),id,message);
                }
                editText.setText("");
            }
        });

    }

    private void readmessage(final String uid, final String id) {
        message_models = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                message_models.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Message_model chat = snapshot.getValue(Message_model.class);

                    if (chat.getReciver().equals(uid) && chat.getSend().equals(id) ||
                        chat.getReciver().equals(id) && chat.getSend().equals(uid)){
                    message_models.add(chat);
                    }
                    message_adapter = new Message_adapter(MessagingActivty.this,message_models);
                    recyclerView.setAdapter(message_adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void back(View view) {
        finish();
    }
    private void sendMessage(String sender,String reciver,String Message){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("send",sender);
        hashMap.put("reciver",reciver);
        hashMap.put("message",Message);

        reference.child("Chats").push().setValue(hashMap);


    }
}
