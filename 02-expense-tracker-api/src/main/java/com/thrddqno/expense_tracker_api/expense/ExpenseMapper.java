package com.thrddqno.expense_tracker_api.expense;

import com.thrddqno.expense_tracker_api.expense.dto.ExpenseRequest;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseResponse;
import com.thrddqno.expense_tracker_api.expense.dto.PagedExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ExpenseRequest toRequest(Expense expense);

    @Mapping(target = "categoryId", source = "category.id")
    ExpenseResponse toResponse(Expense expense);

    @Mapping(target = "data", source = "content")
    @Mapping(target = "page", expression = "java(page.getNumber() + 1)")
    @Mapping(target = "limit", source = "size")
    @Mapping(target = "total", source = "totalElements")
    PagedExpenseResponse<ExpenseResponse> toPagedExpenseResponse(Page<Expense> page);
}
