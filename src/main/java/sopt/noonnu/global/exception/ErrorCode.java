package sopt.noonnu.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getStatus();
    String getPrefix();
    String getMessage();
}
