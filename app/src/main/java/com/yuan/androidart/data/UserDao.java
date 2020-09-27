package com.yuan.androidart.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface UserDao {
    @Insert
     void insert(User user);


    @Query("SELECT * FROM USER")
    public List<User> getAllUser();

}
