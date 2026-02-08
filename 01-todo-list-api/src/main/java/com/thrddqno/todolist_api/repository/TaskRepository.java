package com.thrddqno.todolist_api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thrddqno.todolist_api.domain.Task;
import com.thrddqno.todolist_api.domain.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	Page<Task> findAllByUser(User user, Pageable pageable);
	
	Optional<Task> findByUser(User user);
	
	Optional<Task> findByUserAndId(User user, Integer id);
}
