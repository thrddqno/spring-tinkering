package com.thrddqno.todolist_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thrddqno.todolist_api.domain.User;
import com.thrddqno.todolist_api.domain.dto.PagedTaskResponse;
import com.thrddqno.todolist_api.domain.dto.TaskRequest;
import com.thrddqno.todolist_api.domain.dto.TaskResponse;
import com.thrddqno.todolist_api.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;
	
	@PostMapping
	public ResponseEntity<TaskResponse> createTask(@AuthenticationPrincipal User user, @RequestBody TaskRequest request){
		System.out.println("Testing here!!");
		return ResponseEntity.ok(taskService.createNewTask(user, request));
	}
	
	@GetMapping
	public ResponseEntity<PagedTaskResponse<TaskResponse>> getTask(@AuthenticationPrincipal User user, @RequestParam int page, @RequestParam int size){
		return ResponseEntity.ok(taskService.getTask(user, page, size));
	}
	
	@PutMapping("/{taskId}")
	public ResponseEntity<TaskResponse> updateTask(@AuthenticationPrincipal User user, @PathVariable Integer taskId, @RequestBody TaskRequest request){
		return ResponseEntity.ok(taskService.updateTask(user, taskId, request));
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@AuthenticationPrincipal User user, @PathVariable Integer taskId){
		return ResponseEntity.noContent().build();
	}
}
