package com.example.demo_chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chat.Model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Message_adapter extends RecyclerView.Adapter<Message_adapter.ViewHolder> {
    private Context context;
    private List<User> userList;

    public Message_adapter(Context context,List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Message_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list ,parent,false);
        return new Message_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image_id);

        }
    }

}