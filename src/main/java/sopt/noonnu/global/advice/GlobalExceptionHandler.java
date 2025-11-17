package sopt.noonnu.global.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import sopt.noonnu.global.dto.CustomErrorResponse;
import sopt.noonnu.global.exception.BaseException;
import sopt.noonnu.global.exception.CommonErrorCode;
import sopt.noonnu.global.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    /**
     * 커스텀 예외 처리 (BaseException 및 하위 클래스)
     * - 비즈니스 로직에서 발생하는 모든 예외 처리
     * - ErrorCode 기반으로 일관성 있는 응답 제공
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<CustomErrorResponse> handleBaseException(BaseException e) {
        ErrorCode code = e.getErrorCode();
        logError(code, e);

        return convert(code);
    }

    /**
     * 요청 데이터 Validation 전용 ExceptionHandler (@Valid, @ModelAttribute)
     * - @RequestBody + @Valid -> MethodArgumentNotValidException
     * - @ModelAttribute + @Valid -> BindException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElseGet(() -> ex.getBindingResult().getGlobalErrors()
                        .stream()
                        .findFirst()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .orElse("입력값이 잘못되었습니다.")
                );

        log.warn("Validation failed: {}", message);
        return convert(CommonErrorCode.VALIDATION_ERROR, message);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<CustomErrorResponse> handleBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = extractErrorMessage(fieldErrors);

        log.warn("Bind validation failed: {}", message);
        return convert(CommonErrorCode.VALIDATION_ERROR, message);
    }

    /**
     * 메서드 파라미터 검증 예외 (@PathVariable, @RequestParam 등에 @Valid 사용 시)
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        String message = e.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());

        log.warn("Method validation failed: {}", message);
        return convert(CommonErrorCode.VALIDATION_ERROR, message);
    }

    /**
     * JSON 파싱 실패 처리 (400)
     * 잘못된 enum 값
     * 빈 문자열 -> enum 매핑 실패
     * 숫자 -> 문자열 등 Jackson 바인딩 실패
     * DTO 생성 전에 터지므로 MethodArgumentNotValidException이 아님
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("JSON 파싱 실패: {}", e.getMessage());
        return convert(CommonErrorCode.JSON_PARSE_ERROR);
    }

    /**
     * 요청 파라미터 누락 처리
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException e) {
        log.warn("필수 파라미터 누락: {}", e.getParameterName());
        return convert(CommonErrorCode.MISSING_REQUEST_PARAMETER,
                "필수 파라미터가 누락되었습니다: " + e.getParameterName());
    }

    /**
     * 경로 변수 누락 처리
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingPathVariable(MissingPathVariableException e) {
        log.warn("경로 변수 누락: {}", e.getVariableName());
        return convert(CommonErrorCode.MISSING_PATH_VARIABLE,
                "필수 경로 변수가 누락되었습니다: " + e.getVariableName());
    }

    /**
     * 타입 불일치 처리
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse> handleTypeMismatch(TypeMismatchException e) {
        log.warn("타입 불일치: {} -> {}", e.getValue(), e.getRequiredType());
        return convert(CommonErrorCode.TYPE_MISMATCH);
    }

    /**
     * 존재하지 않는 엔드포인트 또는 타입 변환 실패 처리
     * 잘못된 URL 요청 (404)
     * 파라미터 타입 변환 실패 (예: String -> Integer 변환 실패)
     */
    @ExceptionHandler({NoHandlerFoundException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<CustomErrorResponse> handleNotFoundOrTypeMismatch(Exception e) {
        if (e instanceof MethodArgumentTypeMismatchException typeMismatch) {
            log.warn("Type conversion failed: {} -> {}",
                    typeMismatch.getValue(), typeMismatch.getRequiredType().getSimpleName());
        } else {
            log.warn("Handler not found: {}", e.getMessage());
        }

        return convert(CommonErrorCode.NOT_FOUND_URI);
    }

    /**
     * 지원하지 않는 HTTP 메서드 처리 (405)
     * POST만 지원하는 엔드포인트에 GET 요청을 보낸 경우
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("Method not supported: {} for {}", e.getMethod(), e.getMessage());
        return convert(CommonErrorCode.NOT_SUPPORTED_METHOD_ERROR);
    }

    /**
     * 지원하지 않는 미디어 타입 처리 (415)
     * JSON만 받는 API에 XML 데이터를 보낸 경우
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<CustomErrorResponse> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException e,
                                                                           HttpServletRequest request) {
        log.warn("Media type not supported: {}", e.getContentType());
        return convert(CommonErrorCode.NOT_SUPPORTED_MEDIA_TYPE_ERROR);
    }

    /**
     * 예상치 못한 서버 오류 처리 (500)
     * 위의 핸들러들로 처리되지 않은 모든 RuntimeException
     * 최후의 보루 역할
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleUnexpectedException(RuntimeException e, HttpServletRequest request) {
        log.error("Unexpected error occurred", e);
        log.error("Request info: {} {}", request.getMethod(), request.getRequestURI());

        return convert(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * ErrorCode를 CustomErrorResponse로 변환 (기본 메시지)
     */
    private ResponseEntity<CustomErrorResponse> convert(ErrorCode code) {
        return ResponseEntity
                .status(code.getStatus())
                .body(CustomErrorResponse.from(code));
    }

    /**
     *ErrorCode와 커스텀 메시지로 응답 생성
     */
    private ResponseEntity<CustomErrorResponse> convert(ErrorCode code, String message) {
        return ResponseEntity
                .status(code.getStatus())
                .body(CustomErrorResponse.of(code, message));
    }

    /**
     * FieldError 리스트에서 에러 메시지 추출
     */
    private String extractErrorMessage(List<FieldError> fieldErrors) {
        if (fieldErrors.size() == 1) {
            return fieldErrors.get(0).getDefaultMessage();
        }

        StringBuilder buffer = new StringBuilder();
        for (FieldError error : fieldErrors) {
            buffer.append(error.getDefaultMessage()).append("\n");
        }
        return buffer.toString();
    }

    /**
     * 구조화된 에러 로깅
     */
    private void logError(ErrorCode code, Exception e) {
        log.warn("[{}] {} | {} | {} | Message: {}",
                "BaseException",
                code.getStatus().value(),
                code.getPrefix(),
                code.getMessage(),
                e.getMessage());
    }
}
