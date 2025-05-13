package com.example.test_back.service.implementations;

import com.example.test_back.common.ResponseMessage;
import com.example.test_back.dto.request.MenuRequestDto;
import com.example.test_back.dto.response.MenuResponseDto;
import com.example.test_back.dto.response.ResponseDto;
import com.example.test_back.entity.Menu;
import com.example.test_back.entity.Restaurant;
import com.example.test_back.repository.MenuRepository;
import com.example.test_back.repository.RestaurantRepository;
import com.example.test_back.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public ResponseDto<MenuResponseDto> createMenu(Long restaurantId, MenuRequestDto requestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_RESTAURANT + restaurantId));

        Menu newMenu = Menu.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .description(requestDto.getDescription())
                .build();

        restaurant.addMenu(newMenu);

        Menu savedMenu = menuRepository.save(newMenu);

        MenuResponseDto responseDto = MenuResponseDto.builder()
                .id(savedMenu.getId())
                .name(savedMenu.getName())
                .price(savedMenu.getPrice())
                .description(savedMenu.getDescription())
                .build();

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
    }


    }

    @Override
    @Transactional
    public ResponseDto<Void> deleteMenu(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_MENU + menuId));

        if (!menu.getRestaurant().getId().equals(restaurantId)) {
            throw new IllegalArgumentException("Menu (ID: " + menuId + ") does not belong to Restaurant (ID: " + restaurantId + ")");
        }

        menu.getRestaurant().removeMenu(menu);

        menuRepository.delete(menu);

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}