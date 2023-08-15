package com.todolist.member.bff.adapter.client;

import com.todolist.member.bff.adapter.http.models.MemberLoginCommand;
import com.todolist.member.bff.service.models.TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${services.account.name}", url = "${services.account.url}")
public interface AccountServiceClient {


  @PostMapping("member/login")
  TokenDTO memberLogin(@RequestBody MemberLoginCommand memberLoginCommand);
}
