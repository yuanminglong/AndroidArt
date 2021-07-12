package com.yuan.androidart.base.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class LiMing implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("liming get message :"+arg);
    }
}
