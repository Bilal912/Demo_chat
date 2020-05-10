package com.example.demo_chat.Model;

public class Message_model {
    String sender;
    String recver;
    String message;

    public Message_model() {
    }

    public Message_model(String sender, String recver, String message) {
        this.sender = sender;
        this.recver = recver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecver() {
        return recver;
    }

    public void setRecver(String recver) {
        this.recver = recver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
