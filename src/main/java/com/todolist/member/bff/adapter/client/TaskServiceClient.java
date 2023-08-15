package com.todolist.member.bff.adapter.client;

import com.todolist.member.bff.adapter.http.models.CreateTaskCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${services.task.name}", url = "${services.task.url}")
public interface TaskServiceClient {

  @PostMapping("/task")
  void createTask(@RequestBody CreateTaskCommand createTaskCommand);
}
