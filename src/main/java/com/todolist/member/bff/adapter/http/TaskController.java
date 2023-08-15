package com.todolist.member.bff.adapter.http;

import com.todolist.member.bff.service.models.CreateTaskCommand;
import com.todolist.member.bff.service.TaskService;
import com.todolist.member.bff.service.models.TaskDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @PostMapping("/task")
  public void createTask(@RequestBody CreateTaskCommand createTaskCommand) {
    taskService.createTask(createTaskCommand);
  }

  @GetMapping("/tasks")
  public List<TaskDTO> getAllTasks() {
    return taskService.getAllTasks();
  }

  @DeleteMapping("/task/{id}")
  public void deleteTask(@PathVariable("id") String taskId) {
    taskService.deleteTask(taskId);
  }
}
