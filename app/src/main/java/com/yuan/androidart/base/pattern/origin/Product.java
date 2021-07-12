package com.yuan.androidart.base.pattern.origin;

import androidx.annotation.NonNull;


public class Product{
    private int price;
    private boolean mBoolean;
    Integer integer;
    private String string;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean ismBoolean() {
        return mBoolean;
    }

    public void setmBoolean(boolean mBoolean) {
        this.mBoolean = mBoolean;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @NonNull
    @Override
    public Object clone() {
        Product product = null;
        try {
            product = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", mBoolean=" + mBoolean +
                ", integer=" + integer.toString() +
                ", string='" + string.hashCode() + '\'' +
                '}';
    }
}
