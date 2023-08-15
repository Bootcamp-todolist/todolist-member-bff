package com.todolist.member.bff.config;

import static com.todolist.member.bff.common.Constant.USER_ID;
import static com.todolist.member.bff.common.Constant.USER_ROLE;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class LoggingAuthRequestInterceptor implements RequestInterceptor {

  public static final String HEADER_REQUEST_ID = "X-Request-ID";

  @Override
  public void apply(RequestTemplate template) {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;
    String requestId = requestAttributes.getRequest().getHeader(HEADER_REQUEST_ID);
    ThreadContext.put(HEADER_REQUEST_ID, requestId);
    template.header(HEADER_REQUEST_ID, requestId);
    template.header(USER_ID, requestAttributes.getRequest().getHeader(USER_ID));
    template.header(USER_ROLE, requestAttributes.getRequest().getHeader(USER_ROLE));
  }
}
