package com.thrddqno.todolist_api.domain.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.thrddqno.todolist_api.domain.Task;
import com.thrddqno.todolist_api.domain.dto.PagedTaskResponse;
import com.thrddqno.todolist_api.domain.dto.TaskRequest;
import com.thrddqno.todolist_api.domain.dto.TaskResponse;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	TaskRequest toRequest(Task task);
	
	TaskResponse toResponse(Task task);
	
	@Mapping(target = "data", source = "content")
    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "limit", source = "size")
    @Mapping(target = "total", source = "totalElements")
	PagedTaskResponse<TaskResponse> toPagedTaskResponse(Page<Task> page);
}
