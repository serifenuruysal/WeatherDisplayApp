package com.soulkitchen.app.domain;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by S.Nur Uysal on 07.04.2019.
 */
public class ApiClient {

    private static final String TAG = "ApiClient";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static final OkHttpClient client;
    private static ApiService mService;
    static {

        client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(new AuthInterceptor())
            .build();

    }
    public static ApiService getInstance() {
        synchronized (new Object()) {
            if (mService == null) {
                mService = getRetrofitInstance().create(ApiService.class);
            }
            return mService;
        }
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();
    }

    public ApiService getService() {
        return mService;
    }

    public static class AuthInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("appid", "6db6d1a9f8be4cfc0b46630e65add161")
                .build();

            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }


    }

}
