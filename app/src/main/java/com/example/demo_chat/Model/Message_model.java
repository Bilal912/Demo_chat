package com.example.demo_chat.Model;

public class Message_model {
    String send;
    String reciver;
    String message;

    public Message_model() {
    }

    public Message_model(String send, String reciver, String message) {
        this.send = send;
        this.reciver = reciver;
        this.message = message;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
