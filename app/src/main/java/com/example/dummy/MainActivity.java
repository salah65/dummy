package com.example.dummy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    String current = "";
    @Inject
    EndPoints endPoints;
    private static final String TAG = "MainActivity";
    @Inject
    MyInterceptor interceptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBtn = findViewById(R.id.amount);
        testBtn.setOnClickListener(view -> sendRequest());
        testBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                interceptor.setData("salah", "samy", "mahmoud");
                return false;
            }
        });

    }

    void sendRequest() {
        endPoints.getPosts().
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).subscribe(new SingleObserver<Post>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Post post) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}