package com.example.shane.MAV.Objects;



import com.example.shane.MAV.Interfaces.usersInterfce;


public class User implements usersInterfce{

    private String email;
    private String name;
    private String password;
    private int is_Admin;

    public User(){

    }

    public User(String name, String email, String password, int is_Admin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.is_Admin = is_Admin;
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
    public int getAdminStatus() { return is_Admin; }
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setName(String name) {this.name = name;}
    public void changeAdminStatus(){
        if (is_Admin == 0)
            is_Admin = 1;
        else
            is_Admin = 0;
    }

}
