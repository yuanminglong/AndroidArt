package com.yuan.androidart.base.pattern.state;

public class LightStateOff implements LightState {
    @Override
    public void change(Light light) {
        System.out.println(" 灯灭了");
        light.setmLightState(new LightStateOn());
    }
}
