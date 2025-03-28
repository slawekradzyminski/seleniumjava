package com.awesome.testing.http;

import com.awesome.testing.http.dto.cart.CartResponseDto;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import static com.awesome.testing.http.ObjectMapperProvider.getObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

public class GetCartApi extends BaseApi {

    private static final String GET_CART_URL = apiUrl("/api/cart");

    @SneakyThrows
    public static int getCurrentNumberOfItemsInCart(String token) {
        Request request = new Request.Builder()
                .url(GET_CART_URL)
                .header("Authorization", String.format("Bearer %s", token))
                .get()
                .build();

        try (Response response = getClient().newCall(request).execute()) {
            assertThat(response.code()).isEqualTo(200);
            return currentNumberOfItemsInCart(response);
        }
    }

    @SuppressWarnings("ConstantConditions")
    @SneakyThrows
    private static int currentNumberOfItemsInCart(Response response) {
        String responseBody = response.body().string();
        CartResponseDto loginResponseDto = getObjectMapper().readValue(responseBody, CartResponseDto.class);
        return loginResponseDto.getTotalItems();
    }

}
