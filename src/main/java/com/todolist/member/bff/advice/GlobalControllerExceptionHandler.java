package com.todolist.member.bff.advice;

import static com.todolist.member.bff.exception.Error.INVALID_REQUEST_PARAMETERS;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.todolist.member.bff.exception.BusinessException;
import com.todolist.member.bff.exception.ClientBadRequestException;
import com.todolist.member.bff.exception.ClientException;
import com.todolist.member.bff.exception.ErrorResult;
import com.todolist.member.bff.exception.ServerException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseBody
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ClientBadRequestException.class)
  public ResponseEntity<ErrorResult> handleException(ClientBadRequestException clientException) {
    log.error("exception from client: ", clientException);
    return new ResponseEntity<>(clientException.getErrorResult(), clientException.getHttpStatus());
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResult> handleException(BusinessException e) {
    return new ResponseEntity<>(new ErrorResult(e.getError()), e.getHttpStatus());
  }

  @ExceptionHandler(ClientException.class)
  public ResponseEntity<String> handleException(ClientException clientException) {
    log.error("exception from client: ", clientException);
    return new ResponseEntity<>(clientException.getMessage(), clientException.getHttpStatus());
  }

  @ExceptionHandler(ServerException.class)
  public ResponseEntity<String> handleException(ServerException serverException) {
    log.error("exception from server: ", serverException);
    return new ResponseEntity<>("Internal Server Error", serverException.getHttpStatus());
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(new ErrorResult(INVALID_REQUEST_PARAMETERS, errors.toString()), BAD_REQUEST);
  }
}
