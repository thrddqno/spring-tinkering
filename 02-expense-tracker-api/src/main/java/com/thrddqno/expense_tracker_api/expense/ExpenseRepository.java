package com.thrddqno.expense_tracker_api.expense;

import com.thrddqno.expense_tracker_api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    Optional<Expense> findByUserAndId(User user, Integer id);

    Page<Expense> findAllByUser(User user, Pageable pageable);
}

