package com.example.test_back.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequestDto {
    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private String description;

    @NotNull
    private Long restaurantId;
}
