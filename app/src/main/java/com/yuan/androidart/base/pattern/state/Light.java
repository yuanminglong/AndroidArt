package com.yuan.androidart.base.pattern.state;

public class Light {
    private LightState mLightState;
    public Light() {
       mLightState = new LightStateOn();
    }

    public void setmLightState(LightState lightState){
        mLightState = lightState;
    }

    public void lightSwitch(){
        mLightState.change(this);
    }
}
