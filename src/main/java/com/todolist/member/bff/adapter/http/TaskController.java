package com.todolist.member.bff.adapter.http;

import com.todolist.member.bff.adapter.http.models.CreateTaskCommand;
import com.todolist.member.bff.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping
  public void createTask(@RequestBody CreateTaskCommand createTaskCommand) {
    taskService.createTask(createTaskCommand);
  }
}
