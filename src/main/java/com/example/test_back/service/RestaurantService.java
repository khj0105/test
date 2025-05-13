package com.example.test_back.service;

import com.example.test_back.dto.request.RestaurantRequestDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantDetailResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {
    ResponseDto<RestaurantDetailResponseDto> createRestaurant(RestaurantRequestDto requestDto);
    ResponseDto<RestaurantDetailResponseDto> getRestaurantById(Long id);
    ResponseDto<List<RestaurantResponseDto>> getAllRestaurants();
    ResponseDto<RestaurantDetailResponseDto> updateRestaurant(Long id, RestaurantRequestDto requestDto);
    ResponseDto<Void> deleteRestaurant(Long id);
}
