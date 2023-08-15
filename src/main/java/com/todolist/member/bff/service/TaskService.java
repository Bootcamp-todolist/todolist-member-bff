package com.todolist.member.bff.service;

import com.todolist.member.bff.adapter.client.TaskServiceClient;
import com.todolist.member.bff.service.models.CreateTaskCommand;
import com.todolist.member.bff.service.models.TaskDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

  private final TaskServiceClient taskServiceClient;

  public void createTask(CreateTaskCommand createTaskCommand) {
    taskServiceClient.createTask(createTaskCommand);
  }

  public List<TaskDTO> getAllTasks() {
    return taskServiceClient.getAllTasks();
  }
}
