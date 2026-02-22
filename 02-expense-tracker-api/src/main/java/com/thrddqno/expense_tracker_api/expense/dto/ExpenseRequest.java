package com.thrddqno.expense_tracker_api.expense.dto;

import java.time.LocalDate;

public record ExpenseRequest(
    String name,
    Double amount,
    LocalDate expenseDate,
    Integer categoryId
) {
}
