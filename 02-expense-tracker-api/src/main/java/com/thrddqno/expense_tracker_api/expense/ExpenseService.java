package com.thrddqno.expense_tracker_api.expense;

import com.thrddqno.expense_tracker_api.category.Category;
import com.thrddqno.expense_tracker_api.category.CategoryRepository;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseRequest;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseResponse;
import com.thrddqno.expense_tracker_api.expense.dto.PagedExpenseResponse;
import com.thrddqno.expense_tracker_api.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    //get
    public PagedExpenseResponse<ExpenseResponse> getExpense(User user, int page, int size){
        PageRequest request = PageRequest.of(page - 1, size);
        return expenseMapper.toPagedExpenseResponse(expenseRepository.findAllByUser(user, request));
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
