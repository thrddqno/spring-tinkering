package com.thrddqno.todolist_api.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thrddqno.todolist_api.domain.Task;
import com.thrddqno.todolist_api.domain.User;
import com.thrddqno.todolist_api.domain.dto.PagedTaskResponse;
import com.thrddqno.todolist_api.domain.dto.TaskRequest;
import com.thrddqno.todolist_api.domain.dto.TaskResponse;
import com.thrddqno.todolist_api.domain.mapper.TaskMapper;
import com.thrddqno.todolist_api.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;
	
	//POST
	public TaskResponse createNewTask(User user, TaskRequest request) {
		Task task = Task.builder()
				.title(request.title())
				.description(request.description())
				.user(user)
				.build();
		taskRepository.save(task);
		return taskMapper.toResponse(task);
	}
	
	//GET
	public PagedTaskResponse<TaskResponse> getTask(User user, int page, int size){
		PageRequest pageRequest = PageRequest.of(page - 1, size);
		return taskMapper.toPagedTaskResponse(taskRepository.findAllByUser(user, pageRequest));
	}
	
	
	// PUT
	public TaskResponse updateTask(User user, Integer id, TaskRequest request) {
		Task task = taskRepository.findByUserAndId(user, id).orElseThrow();
		
		task.setTitle(request.title());
		task.setDescription(request.description());
		taskRepository.save(task);
		return taskMapper.toResponse(task);
	}
	
	//DELETE
	public void deleteTask(User user, Integer taskId) {
		Task task = taskRepository.findByUserAndId(user, taskId).orElseThrow();
		taskRepository.delete(task);
	}
}
