package com.quansu.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.quansu.myapplication.aidl.IMyAidlInterface;
import com.quansu.myapplication.databinding.ActivityServiceBinding;
import com.quansu.myapplication.service.DemoService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @NonNull ActivityServiceBinding binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> {
            Log.d("thread", "Activity::onCreate: pid=" + Process.myPid() + "  threadName=" + Thread.currentThread().getName());
//            startService(new Intent(ServiceActivity.this, DemoService.class));


            try {
                int plus = iMyAidlInterface.plus(1, 2);
                Log.d("thread", "plus=" + plus);
                String upperCase = iMyAidlInterface.toUpperCase("hello world");
                Log.d("thread", "upperCase=" + upperCase);
//            startService(new Intent(ServiceActivity.this, DemoService.class));

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        });


        binding.btnBind.setOnClickListener(v -> {

            Log.d("thread", "Activity::onCreate: pid=" + Process.myPid() + "  threadName=" + Thread.currentThread().getName());
            ServiceConnection serviceConnection = new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

//                    DemoService.MyBinder myBinder = (DemoService.MyBinder) service;
//                    myBinder.startDownload();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }

                @Override
                public void onBindingDied(ComponentName name) {
                    ServiceConnection.super.onBindingDied(name);
                }

                @Override
                public void onNullBinding(ComponentName name) {
                    ServiceConnection.super.onNullBinding(name);
                }
            };


//            Intent intent = new Intent(ServiceActivity.this, DemoService.class);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setPackage("com.quansu.myapplication");
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        });


    }
}
