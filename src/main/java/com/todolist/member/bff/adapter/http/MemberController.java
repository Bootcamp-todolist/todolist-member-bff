package com.todolist.member.bff.adapter.http;

import com.todolist.member.bff.adapter.http.models.MemberLoginCommand;
import com.todolist.member.bff.service.MemberService;
import com.todolist.member.bff.service.models.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/login")
  public TokenDTO memberLogin(@RequestBody @Validated MemberLoginCommand memberLoginCommand) {
    return memberService.login(memberLoginCommand);
  }


}
