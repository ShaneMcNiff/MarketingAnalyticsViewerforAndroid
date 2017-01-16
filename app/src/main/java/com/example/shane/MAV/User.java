package com.example.shane.MAV;

/**
 * Created by Shane on 22/12/2016.
 */

public class User implements usersInterfce{

    private String email;
    private String name;
    private String password;

    public User(){

    }

    public User(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getName(){
        return name;
    }
}
