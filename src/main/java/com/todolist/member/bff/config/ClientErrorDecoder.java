package com.todolist.member.bff.config;

import static feign.FeignException.errorStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.member.bff.exception.ClientBadRequestException;
import com.todolist.member.bff.exception.ClientException;
import com.todolist.member.bff.exception.ErrorResult;
import com.todolist.member.bff.exception.ServerException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.nio.charset.Charset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ClientErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    HttpStatus responseStatus = HttpStatus.valueOf(response.status());
    FeignException exception = errorStatus(methodKey, response);
    if (responseStatus.is5xxServerError()) {
      return new ServerException(exception.getMessage(), responseStatus);
    } else if (responseStatus.is4xxClientError()) {
      var byteBuffer = exception.responseBody();
      var body = byteBuffer.map(buffer -> new String(buffer.array(), Charset.defaultCharset()))
          .orElse("");
      ObjectMapper mapper = new ObjectMapper();
      try {
        ErrorResult errorResult = mapper.readValue(body, ErrorResult.class);
        return new ClientBadRequestException(errorResult, HttpStatus.resolve(response.status()));
      } catch (JsonProcessingException e) {
        return new ClientException(body, responseStatus);
      }
    }
    return new Default().decode(methodKey, response);
  }
}
