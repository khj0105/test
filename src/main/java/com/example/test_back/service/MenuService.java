package com.example.test_back.service;

import com.example.test_back.dto.request.MenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import jakarta.validation.Valid;

public interface MenuService {
    ResponseDto<MenuResponseDto> createMenu(Long restaurantId, @Valid MenuRequestDto requestDto);
    ResponseDto<MenuResponseDto> updateMenu(Long restaurantId, Long menuId, @Valid MenuRequestDto requestDto);
    ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId);
}
