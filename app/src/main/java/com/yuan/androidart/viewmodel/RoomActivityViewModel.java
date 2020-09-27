package com.yuan.androidart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.yuan.androidart.data.User;
import com.yuan.androidart.data.UserDatebase;

import java.util.List;

public class RoomActivityViewModel extends AndroidViewModel {
    private UserDatebase userDatebase;
    private List<User>users;

    public RoomActivityViewModel(@NonNull Application application) {
        super(application);
        userDatebase = Room.databaseBuilder(getApplication(), UserDatebase.class,"user_database").allowMainThreadQueries().build();

    }

    public List<User> getUsers() {
        users = userDatebase.getUserDao().getAllUser();
        return users;
    }
    public void insert(User user){
         userDatebase.getUserDao().insert(user);
    }
}
