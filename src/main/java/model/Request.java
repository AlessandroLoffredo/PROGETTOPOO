package model;

import java.util.ArrayList;

public class Request {
    private String message;
    private User sender;

    public Request() {
        this.message = null;
        this.sender = null;
    }
    public Request(String message, User user) {
        this.message = message;
        this.sender = user;
    }

    public User getSender() {
        return this.sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
