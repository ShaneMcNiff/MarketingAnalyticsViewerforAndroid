package com.example.shane.MAV;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shane on 22/12/2016.
 */

public class User implements usersInterfce, Parcelable{

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

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        is_Admin = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeInt(is_Admin);
    }
}
