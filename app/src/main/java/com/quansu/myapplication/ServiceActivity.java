package com.quansu.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.quansu.myapplication.databinding.ActivityServiceBinding;
import com.quansu.myapplication.service.DemoService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @NonNull ActivityServiceBinding binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStart.setOnClickListener(v -> {
            startService(new Intent(ServiceActivity.this, DemoService.class));
        });


        binding.btnBind.setOnClickListener(v -> {

            ServiceConnection serviceConnection = new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    DemoService.MyBinder myBinder = (DemoService.MyBinder) service;
                    myBinder.startDownload();
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

            bindService(new Intent(ServiceActivity.this, DemoService.class), serviceConnection, BIND_AUTO_CREATE);
        });


    }
}
