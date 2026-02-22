package com.thrddqno.expense_tracker_api.expense.dto;

import java.util.List;

public record PagedExpenseResponse<T>(
        List<T> data,
        int page,
        int limit,
        int total
) {
}
