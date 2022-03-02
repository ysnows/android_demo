package com.quansu.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.quansu.myapplication.aidl.IMyAidlInterface;

import androidx.annotation.Nullable;

public class DemoService extends Service {
//    MyBinder binder = new MyBinder();

    IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            return str.toUpperCase();
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("thread", "onBind" + Thread.currentThread().getName());
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("thread", "onStartCommand" + Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("thread", "onCreate: pid=" + Process.myPid() + "  threadName=" + Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public class MyBinder extends Binder {
        public void startDownload() {
            Toast.makeText(DemoService.this, "started", Toast.LENGTH_SHORT).show();
        }

    }
}
