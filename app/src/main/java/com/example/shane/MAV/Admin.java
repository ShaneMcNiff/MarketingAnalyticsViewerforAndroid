package com.example.shane.MAV;

/**
 * Created by Shane on 22/12/2016.
 */

public class Admin implements usersInterfce{
    private String email;
    private String name;
    private String password;
    private int adminID;

    public Admin(){}

    public Admin(String email, String name, String password, int adminID){
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public String getName(){
        return name;
    }
    public String getPassword() {
        return password;
    }
}
