package com.example.dummy;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Singleton
    @Provides
    static MyAPp provideApplication(@ApplicationContext Context context) {
        return (MyAPp) context;
    }

    @Singleton
    @Provides
    static MyInterceptor provideEncryptionInterceptor() {
        return new MyInterceptor();
    }

    @Provides
    @Singleton
    static OkHttpClient.Builder provideOkHttpBuilder(MyInterceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(40, TimeUnit.SECONDS);
        builder.callTimeout(40, TimeUnit.SECONDS);
        builder.readTimeout(40, TimeUnit.SECONDS);
        builder.writeTimeout(40, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(false);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.i("logger", message));
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
            builder.addInterceptor(interceptor);
        }
        return builder;
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    static EndPoints provideRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(EndPoints.class);
    }
}
