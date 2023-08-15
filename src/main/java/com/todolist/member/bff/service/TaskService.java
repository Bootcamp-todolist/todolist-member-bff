package com.todolist.member.bff.service;

import com.todolist.member.bff.adapter.client.TaskServiceClient;
import com.todolist.member.bff.adapter.http.models.CreateTaskCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskServiceClient taskServiceClient;

  public void createTask(CreateTaskCommand createTaskCommand) {
    taskServiceClient.createTask(createTaskCommand);
  }
}
