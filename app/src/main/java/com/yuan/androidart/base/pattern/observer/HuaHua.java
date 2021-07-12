package com.yuan.androidart.base.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class HuaHua implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("HuaHua get message :"+arg);
    }
}
