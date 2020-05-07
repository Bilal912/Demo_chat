package com.example.demo_chat.Model;

public class User {
    String username;
    String email;
    String id;
    String imageurl;

    public User() {
    }

    public User(String username, String email, String id, String imageurl) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.imageurl = imageurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
