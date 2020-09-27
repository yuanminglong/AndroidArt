package com.yuan.androidart.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatebase extends RoomDatabase {

    public abstract UserDao getUserDao();
}
