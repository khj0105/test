package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.RestaurantRequestDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantDetailResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.RESTAURANT_API)
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<ResponseDto<RestaurantDetailResponseDto>> createRestaurant(@Valid @RequestBody RestaurantRequestDto requestDto) {
        ResponseDto<RestaurantDetailResponseDto> response = restaurantService.createRestaurant(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantDetailResponseDto>> getRestaurantById(@PathVariable Long id) {
        ResponseDto<RestaurantDetailResponseDto> response = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<RestaurantResponseDto>>> getAllRestaurants() {
        ResponseDto<List<RestaurantResponseDto>> response = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<RestaurantDetailResponseDto>> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequestDto requestDto) {
        ResponseDto<RestaurantDetailResponseDto> response = restaurantService.updateRestaurant(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteRestaurant(@PathVariable Long id) {
        ResponseDto<Void> response = restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}