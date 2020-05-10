package com.example.demo_chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chat.Model.Message_model;
import com.example.demo_chat.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Message_adapter extends RecyclerView.Adapter<Message_adapter.ViewHolder> {
    public static final int Type_Left = 0;
    public static final int Type_Right = 1;

    private Context context;
    private List<Message_model> userList;
    FirebaseUser firebaseUser;

    public Message_adapter(Context context,List<Message_model> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Message_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == Type_Right){
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right ,parent,false);
            return new ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left ,parent,false);
            return new ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Message_model message_model = userList.get(position);

        holder.textView.setText(message_model.getMessage());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.message);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (userList.get(position).getSend().equals(firebaseUser.getUid())){
            return Type_Right;
        }
        else {
            return Type_Left;
        }
    }
}