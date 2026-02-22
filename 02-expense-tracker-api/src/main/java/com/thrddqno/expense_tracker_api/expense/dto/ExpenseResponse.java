package com.thrddqno.expense_tracker_api.expense.dto;

import java.time.LocalDate;

public record ExpenseResponse(
    Integer id,
    String name,
    Double amount,
    LocalDate expenseDate,
    Integer categoryId
) {
}
