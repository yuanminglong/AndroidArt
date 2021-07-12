package com.yuan.androidart.base;


import com.yuan.androidart.base.pattern.observer.HuaHua;
import com.yuan.androidart.base.pattern.observer.LiMing;
import com.yuan.androidart.base.pattern.observer.Subject;
import com.yuan.androidart.base.pattern.origin.Product;
import com.yuan.androidart.base.pattern.state.Light;

public class JavaBaseMain {

    public static void main(String[] args){
        //testStatePattern();
        //testObserverPattern();  //测试观察者模式
        testClone();
    }

    public static void testStatePattern(){
        Light light = new Light();
        light.lightSwitch();
        light.lightSwitch();
        light.lightSwitch();
        light.lightSwitch();
    }

    /**
     * 测试观察者模式
     */
    public static void testObserverPattern(){
        Subject sj = new Subject();
        sj.addObserver(new LiMing());
        sj.addObserver(new HuaHua());
        sj.startrRun();
        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sj.stopRun();
    }

    public static void testClone(){
        Product product = new Product();
        product.setInteger(new Integer(1));
        product.setmBoolean(true);
        product.setString("123");
        System.out.println(product.toString());
        Product product1 = (Product) product.clone();
        System.out.println(product1.toString());
    }
}
