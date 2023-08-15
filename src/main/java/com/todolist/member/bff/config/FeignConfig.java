package com.todolist.member.bff.config;

import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.todolist.member.bff.adapter.client"})
@RequiredArgsConstructor
public class FeignConfig {

  @Bean
  public ErrorDecoder errorDecoder() {
    return new ClientErrorDecoder();
  }

  @Bean
  public OkHttpClient client() {
    return new OkHttpClient();
  }
}
