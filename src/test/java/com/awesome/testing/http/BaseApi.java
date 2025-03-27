package com.awesome.testing.http;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.interceptors.OkHttpLoggingInterceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public abstract class BaseApi {

    protected static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json");

    protected static String apiUrl(String endpoint) {
        return ConfigProvider.get("backend.url") + endpoint;
    }

    protected static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new OkHttpLoggingInterceptor())
                .build();
    }
}
