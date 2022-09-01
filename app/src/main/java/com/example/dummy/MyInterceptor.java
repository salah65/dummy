package com.example.dummy;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Response;

@Singleton
public class MyInterceptor implements Interceptor {
    String key, iv, sessionID;

    @Inject
    public MyInterceptor() {
    }

    void setData(String key, String iv, String sessionID) {
        this.key = key;
        this.iv = iv;
        this.sessionID = sessionID;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        if (key == null)
            Log.i("TAG", "Encryption  should be defined for auth apis");
        else
            Log.i("TAG", key + " " + iv + " " + sessionID);
        return chain.proceed(chain.request());
    }
}
