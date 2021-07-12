package com.yuan.androidart.base.pattern.observer;

import java.util.Observable;

public class Subject extends Observable {
    private int mIndex = 0;
    private Thread mThread = null;
    private boolean hasStopped = false;

    public synchronized void startrRun() {
        if (mThread == null) {
            mThread = new Thread() {
                @Override
                public void run() {
                    while (!hasStopped) {
                        mIndex++;
                        setChanged();
                        notifyObservers(mIndex);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            mThread.start();
        } else {
            return;
        }
    }

    public synchronized void stopRun(){
        hasStopped = true;
    }
}
