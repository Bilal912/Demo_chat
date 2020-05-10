package com.example.demo_chat;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo_chat.Model.User;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class User_Adapter extends RecyclerView.Adapter<User_Adapter.ViewHolder> {
    private Context context;
    private List<User> userList;

    public User_Adapter(Context context,List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list ,parent,false);
        
        return new User_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.textView.setText(user.getUsername());
        holder.imageView.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MessagingActivty.class);
                intent.putExtra("Name",user.getUsername());
                intent.putExtra("id",user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.name);
        imageView = itemView.findViewById(R.id.image_id);

    }
}

}