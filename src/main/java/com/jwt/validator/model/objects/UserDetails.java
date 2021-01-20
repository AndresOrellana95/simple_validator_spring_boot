package com.jwt.validator.model.objects;

public class UserDetails {
    
    private String key;

    public UserDetails(String key) {
        this.key = key;
    }

    public UserDetails() {

    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
