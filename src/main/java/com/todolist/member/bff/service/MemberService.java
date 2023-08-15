package com.todolist.member.bff.service;

import com.todolist.member.bff.adapter.client.AccountServiceClient;
import com.todolist.member.bff.adapter.http.models.MemberLoginCommand;
import com.todolist.member.bff.service.models.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final AccountServiceClient accountServiceClient;


  public TokenDTO login(MemberLoginCommand memberLoginCommand) {
    return accountServiceClient.memberLogin(memberLoginCommand);
  }
}
