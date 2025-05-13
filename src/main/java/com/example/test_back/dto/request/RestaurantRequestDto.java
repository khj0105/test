package com.example.test_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequestDto {
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phoneNumber;
}
