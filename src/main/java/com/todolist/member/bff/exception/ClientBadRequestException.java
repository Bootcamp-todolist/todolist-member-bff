package com.todolist.member.bff.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ClientBadRequestException extends RuntimeException {

  private final HttpStatus httpStatus;

  private final ErrorResult errorResult;

  protected ClientBadRequestException(ErrorResult errorResult, HttpStatus httpStatus,
      String message) {
    super(message);
    this.errorResult = errorResult;
    this.httpStatus = httpStatus;
  }

  public ClientBadRequestException(ErrorResult errorResult, HttpStatus httpStatus) {
    this(errorResult, httpStatus, errorResult.getErrorMsg());
  }

}
