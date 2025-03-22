package com.awesome.testing.http.interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.awesome.testing.http.ObjectMapperProvider.getObjectMapper;

@Slf4j
public class OkHttpLoggingInterceptor implements Interceptor {

    // Set a maximum length for request/response logging (in characters)
    private static final int MAX_BODY_LENGTH = 2048; // e.g. 2 KB

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Prepare a builder that will accumulate everything
        StringBuilder sb = new StringBuilder();

        // Grab original request
        Request request = chain.request();
        logRequest(sb, request);

        // Proceed with the request
        Response response = chain.proceed(request);

        // Append response details
        response = logResponse(sb, response);

        // Finally log everything in ONE statement
        log.info("\n{}", sb);

        // Return the (possibly rebuilt) response
        return response;
    }

    private void logRequest(StringBuilder sb, Request request) throws IOException {
        sb.append("===== OKHTTP REQUEST =====\n");
        sb.append(String.format("Request: %s %s\n", request.method(), request.url()));

        // Headers
        Headers headers = request.headers();
        if (headers.size() > 0) {
            sb.append("Request headers:\n");
            for (String name : headers.names()) {
                sb.append(String.format("  %s: %s\n", name, headers.get(name)));
            }
        }

        // Body
        RequestBody requestBody = request.body();
        if (requestBody != null && requestBody.contentType() != null) {
            MediaType mediaType = requestBody.contentType();
            if (isJson(mediaType)) {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                String rawBody = buffer.readString(StandardCharsets.UTF_8);

                // Truncate if needed
                if (rawBody.length() > MAX_BODY_LENGTH) {
                    sb.append("Request JSON body is too large. Truncating...\n");
                    rawBody = safeSubstring(rawBody, MAX_BODY_LENGTH);
                }

                // Attempt pretty-print only if body is not too large
                // (You can skip or handle large bodies however you prefer)
                String prettyPrinted = tryPrettyPrint(rawBody);
                sb.append("Request JSON body:\n").append(prettyPrinted).append("\n");
            } else {
                sb.append("Request body is not JSON or is empty.\n");
            }
        }
    }

    private Response logResponse(StringBuilder sb, Response response) throws IOException {
        sb.append("\n===== OKHTTP RESPONSE =====\n");
        sb.append(String.format("Response: %d %s for %s\n",
                response.code(), response.message(), response.request().url()));

        // Headers
        Headers headers = response.headers();
        if (headers.size() > 0) {
            sb.append("Response headers:\n");
            for (String name : headers.names()) {
                sb.append(String.format("  %s: %s\n", name, headers.get(name)));
            }
        }

        // Body
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            MediaType contentType = responseBody.contentType();
            if (contentType != null && isJson(contentType)) {
                // Must consume the body, so do it carefully
                String rawBody = responseBody.string();

                // Truncate if needed
                if (rawBody.length() > MAX_BODY_LENGTH) {
                    sb.append("Response JSON body is too large. Truncating...\n");
                    rawBody = safeSubstring(rawBody, MAX_BODY_LENGTH);
                }

                // Attempt pretty-print
                String prettyPrinted = tryPrettyPrint(rawBody);
                sb.append("Response JSON body:\n").append(prettyPrinted).append("\n");

                // Rebuild response so downstream code can still read it
                ResponseBody newBody = ResponseBody.create(rawBody, contentType);
                return response.newBuilder().body(newBody).build();
            } else {
                sb.append("Response body is not JSON or is empty.\n");
            }
        }

        return response;
    }

    private boolean isJson(MediaType mediaType) {
        return mediaType.toString().toLowerCase().contains("application/json");
    }

    private String tryPrettyPrint(String body) {
        try {
            Object obj = getObjectMapper().readValue(body, Object.class);
            return getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            // If not valid JSON, return as-is
            return body;
        }
    }

    private String safeSubstring(String raw, int maxLen) {
        if (raw.length() > maxLen) {
            return raw.substring(0, maxLen) + "... [truncated]";
        }
        return raw;
    }
}
