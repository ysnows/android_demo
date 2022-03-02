package com.quansu.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.quansu.myapplication.databinding.ActivityOkhttpBinding;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class OkhttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @NonNull ActivityOkhttpBinding binding = ActivityOkhttpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnJumpViewDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient httpClient = new OkHttpClient();

                String url = "https://www.baidu.com/";
                Request getRequest = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                Call call = httpClient.newCall(getRequest);

//                new Thread(() -> {
//                    try {
//                        //同步请求，要放到子线程执行
//                        Response response = call.execute();
//                        Log.i(TAG, "okHttpGet run: response:" + response.body().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }).start();

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.i(TAG, "okHttpGet run: response:" + response.body().string());
                    }
                });

            }
        });
    }
}
