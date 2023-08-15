package com.todolist.member.bff.adapter.client;

import com.todolist.member.bff.service.models.CreateTaskCommand;
import com.todolist.member.bff.service.models.TaskDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${services.task.name}", url = "${services.task.url}")
public interface TaskServiceClient {

  @PostMapping("/task")
  void createTask(@RequestBody CreateTaskCommand createTaskCommand);

  @GetMapping("/task")
  List<TaskDTO> getAllTasks();
}
