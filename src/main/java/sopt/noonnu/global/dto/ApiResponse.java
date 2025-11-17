package sopt.noonnu.global.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"errorCode", "message", "result"})
public record ApiResponse<T>(
        String errorCode,

        String message,

        T result
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(null, "OK", data);
    }
}
