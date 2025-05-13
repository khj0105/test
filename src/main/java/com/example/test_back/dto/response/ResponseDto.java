package com.example.test_back.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // getter, setter, toString 등을 생성
@AllArgsConstructor(staticName = "set")

public class ResponseDto<D> {
    private boolean result;
    private String message;
    private D data;

    // 성공 응답 생성
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    // 실패 응답 생성
    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }
}
