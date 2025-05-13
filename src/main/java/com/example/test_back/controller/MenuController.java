package com.example.test_back.controller;

import com.example.test_back.common.ApiMappingPattern;
import com.example.test_back.dto.request.MenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiMappingPattern.MENU_API)
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseDto<MenuResponseDto>> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuRequestDto requestDto) {
        ResponseDto<MenuResponseDto> response = menuService.createMenu(restaurantId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<ResponseDto<MenuResponseDto>> updateMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId,
            @Valid @RequestBody MenuRequestDto requestDto) {
        ResponseDto<MenuResponseDto> response = menuService.updateMenu(restaurantId, menuId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<ResponseDto<Void>> deleteMenu(
            @PathVariable Long restaurantId,
            @PathVariable Long menuId) {
        ResponseDto<Void> response = menuService.deleteMenu(restaurantId, menuId);
        return ResponseEntity.noContent().build();
    }
}