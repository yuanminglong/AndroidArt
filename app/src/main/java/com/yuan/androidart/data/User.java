package com.yuan.androidart.data;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @PrimaryKey(autoGenerate = true)
    private int userId;
    @ColumnInfo(name = "user_name")
    private    String name;
    @ColumnInfo(name = "user_age")
    private   String age;

    protected User(Parcel in) {
        name = in.readString();
        age = in.readString();
        email = in.readString();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
        //notify();

    }

    public void setEmail(String email) {
        this.email = email;
    }

    private   String email;

    public User( String name, String age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }



    public String getAge() {
        return age;
    }



    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
