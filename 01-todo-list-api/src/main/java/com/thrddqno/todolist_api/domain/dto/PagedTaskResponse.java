package com.thrddqno.todolist_api.domain.dto;

import java.util.List;

public record PagedTaskResponse<T>(
		List<T> data,
		int page,
		int limit,
		int total
		) {

}
