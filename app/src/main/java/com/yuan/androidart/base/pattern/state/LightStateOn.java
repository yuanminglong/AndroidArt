package com.yuan.androidart.base.pattern.state;

public class LightStateOn implements LightState {
    @Override
    public void change(Light light) {
        System.out.println(" 灯亮了");
        light.setmLightState(new LightStateOff());
    }
}
