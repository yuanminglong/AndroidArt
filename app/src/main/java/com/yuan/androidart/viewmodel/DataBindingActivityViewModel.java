package com.yuan.androidart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.yuan.androidart.data.User;

public class DataBindingActivityViewModel extends ViewModel {
    public DataBindingActivityViewModel() {
        if (liveDataUser==null ){
            liveDataUser = new MutableLiveData<>();
            liveDataUser.setValue(getUser());
        }

    }

    private User user;
    private MutableLiveData<User> liveDataUser;

    private User getUser() {
        if (user==null){
            synchronized (this.getClass()){
                if (user==null){
                    user = new User("yuan","29","yuanminglong@163.com");
                }
            }
        }
        return user;
    }

    public void changeUserName(String userName){
        getUser().setName(userName);
        liveDataUser.setValue(getUser());
    }

    public LiveData<User> getLiveDataUser() {
        return liveDataUser;
    }
}
