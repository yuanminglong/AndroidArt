package com.yuan.androidart.base.pattern.adapter;

public abstract class Adaptee implements TargetInterface{

     String specialSomeThing(){
         return this.getSomething();
     }
}
