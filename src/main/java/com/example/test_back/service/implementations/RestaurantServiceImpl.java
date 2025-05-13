package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.RestaurantRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.dto.response.RestaurantDetailResponseDto;
import com.example.test_back.dto.response.RestaurantResponseDto;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public ResponseDto<RestaurantDetailResponseDto> createRestaurant(RestaurantRequestDto requestDto) {
        Restaurant newRestaurant = Restaurant.builder()
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(newRestaurant);

        RestaurantDetailResponseDto responseDto = RestaurantDetailResponseDto.builder()
                .id(savedRestaurant.getId())
                .name(savedRestaurant.getName())
                .address(savedRestaurant.getAddress())
                .phoneNumber(savedRestaurant.getPhoneNumber())
                .menus(savedRestaurant.getMenus().stream()
                        .map(menu -> MenuResponseDto.builder()
                                .id(menu.getId())
                                .name(menu.getName())
                                .price(menu.getPrice())
                                .description(menu.getDescription())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<RestaurantDetailResponseDto> getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + id));

        List<MenuResponseDto> menuResponseDtos = restaurant.getMenus().stream()
                .map(menu -> MenuResponseDto.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .description(menu.getDescription())
                        .build())
                .collect(Collectors.toList());

        RestaurantDetailResponseDto responseDto = RestaurantDetailResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .menus(menuResponseDtos)
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }
}