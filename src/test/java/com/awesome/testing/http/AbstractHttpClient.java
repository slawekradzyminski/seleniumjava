package com.awesome.testing.http;

import com.awesome.testing.config.ConfigProvider;
import com.awesome.testing.http.interceptor.OkHttpLoggingInterceptor;
import okhttp3.*;

public class AbstractHttpClient {

    protected static final String BASE_URL = ConfigProvider.get("backend.url");
    protected static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");
    protected static final OkHttpClient CLIENT = buildOkHttpClient();

    protected static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new OkHttpLoggingInterceptor())
                .build();
    }

}