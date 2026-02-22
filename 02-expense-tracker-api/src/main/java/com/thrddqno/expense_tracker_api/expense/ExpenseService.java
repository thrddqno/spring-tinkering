package com.thrddqno.expense_tracker_api.expense;

import com.thrddqno.expense_tracker_api.category.Category;
import com.thrddqno.expense_tracker_api.category.CategoryRepository;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseRequest;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseResponse;
import com.thrddqno.expense_tracker_api.expense.dto.PagedExpenseResponse;
import com.thrddqno.expense_tracker_api.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    //get
    public PagedExpenseResponse<ExpenseResponse> getExpense(User user, int page, int size, String filter, LocalDate customStart, LocalDate customEnd){
        PageRequest request = PageRequest.of(page - 1, size);

        LocalDate startDate;
        LocalDate endDate = LocalDate.now();

        switch (filter.toLowerCase()) {
            case "week" -> startDate = endDate.minusWeeks(1);
            case "month" -> startDate = endDate.minusMonths(1);
            case "3months" -> startDate = endDate.minusMonths(3);
            case "custom" -> {
                if (customStart == null || customEnd == null) throw new IllegalArgumentException("Custom filter requires start and end dates");
                startDate = customStart;
                endDate = customEnd;
            }
            default -> startDate = LocalDate.of(1970, 1, 1);
        }

        return expenseMapper.toPagedExpenseResponse(expenseRepository.findAllByUserAndExpenseDateBetween(user, startDate, endDate ,request));
    }

    //post
    @Transactional
    public ExpenseResponse createExpense(User user, ExpenseRequest request){
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow();

        var expense = Expense.builder()
                .name(request.name())
                .amount(request.amount())
                .expenseDate(request.expenseDate())
                .category(category)
                .user(user)
                .build();

        expenseRepository.save(expense);
        return expenseMapper.toResponse(expense);
    }

    //put
    @Transactional
    public ExpenseResponse updateExpense(User user, Integer expenseId, ExpenseRequest request){
        Expense expense = expenseRepository.findByUserAndId(user, expenseId).orElseThrow();
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow();

        expense.setName(request.name());
        expense.setAmount(request.amount());
        expense.setExpenseDate(request.expenseDate());
        expense.setCategory(category);

        expenseRepository.save(expense);
        return expenseMapper.toResponse(expense);
    }

    //delete
    public void deleteExpense(User user, Integer expenseId){
        Expense expense = expenseRepository.findByUserAndId(user, expenseId).orElseThrow();
        expenseRepository.delete(expense);
    }


}
