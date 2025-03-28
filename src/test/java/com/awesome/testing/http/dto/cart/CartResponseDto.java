package com.awesome.testing.http.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {

    String username;
    List<ItemDto> items;
    double totalPrice;
    int totalItems;

}
