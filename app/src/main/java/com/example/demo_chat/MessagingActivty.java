package com.example.demo_chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MessagingActivty extends AppCompatActivity {
TextView textView,Send;
EditText editText;
RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_activty);

        recyclerView = findViewById(R.id.recycler_id);

        textView = findViewById(R.id.username);
        editText= findViewById(R.id.img);
        Send  = findViewById(R.id.message);

        final String id = getIntent().getStringExtra("id");

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editText.getText().toString();
                if (TextUtils.isEmpty(message)){
                    Toast.makeText(MessagingActivty.this,"Pehla message likho",Toast.LENGTH_LONG).show();
                }
                else {
                    sendMessage(FirebaseAuth.getInstance().getCurrentUser().getUid(),id,message);
                }
                editText.setText("");
            }
        });
        String name = getIntent().getStringExtra("Name");
        textView.setText(name);


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
