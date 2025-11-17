package sopt.noonnu.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    // ===== 공통 에러 (4xx) =====
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "COMMON_001", "입력값 검증에 실패했습니다."),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "COMMON_002", "요청 형식이 잘못되었습니다."),
    MISSING_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON_003", "필수 파라미터가 누락되었습니다."),
    MISSING_PATH_VARIABLE(HttpStatus.BAD_REQUEST, "COMMON_004", "필수 경로 변수가 누락되었습니다."),
    TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "COMMON_005", "잘못된 데이터 타입입니다."),
    NOT_FOUND_URI(HttpStatus.NOT_FOUND, "COMMON_006", "존재하지 않는 URI입니다."),
    NOT_SUPPORTED_METHOD_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "COMMON_007", "지원하지 않는 HTTP 메서드입니다."),
    NOT_SUPPORTED_MEDIA_TYPE_ERROR(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "COMMON_008", "지원하지 않는 미디어 타입입니다."),

    // ===== 서버 에러 (5xx) =====
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_001", "서버 내부 오류가 발생했습니다.");


    private final HttpStatus status;
    private final String prefix;
    private final String message;
}
