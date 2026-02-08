package com.thrddqno.todolist_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thrddqno.todolist_api.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
