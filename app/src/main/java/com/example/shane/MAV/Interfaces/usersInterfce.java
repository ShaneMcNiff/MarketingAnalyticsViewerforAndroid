package com.example.shane.MAV.Interfaces;

/**
 * Created by Shane on 22/12/2016.
 */

public interface usersInterfce {
    public String getName();
    public String getEmail();
    public String getPassword();
    public int getAdminStatus();
    public void setName(String name);
    public void setEmail(String email);
    public void setPassword(String password);
    public void changeAdminStatus();
}
