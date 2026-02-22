package com.thrddqno.expense_tracker_api.expense;

import com.thrddqno.expense_tracker_api.expense.dto.ExpenseRequest;
import com.thrddqno.expense_tracker_api.expense.dto.ExpenseResponse;
import com.thrddqno.expense_tracker_api.expense.dto.PagedExpenseResponse;
import com.thrddqno.expense_tracker_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<PagedExpenseResponse<ExpenseResponse>> getExpenses(
            @AuthenticationPrincipal User user,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false, defaultValue = "all") String filter,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end
    ) {
        LocalDate customStart = start != null ? LocalDate.parse(start) : null;
        LocalDate customEnd = end != null ? LocalDate.parse(end) : null;

        var response = expenseService.getExpense(user, page, size, filter, customStart, customEnd);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@AuthenticationPrincipal User user, @RequestBody ExpenseRequest request){
        return ResponseEntity.ok(expenseService.createExpense(user, request));
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@AuthenticationPrincipal User user, @RequestBody ExpenseRequest request, @PathVariable Integer expenseId){
        return ResponseEntity.ok(expenseService.updateExpense(user,expenseId,request));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@AuthenticationPrincipal User user, @PathVariable Integer expenseId){
        expenseService.deleteExpense(user, expenseId);
        return ResponseEntity.noContent().build();
    }
}
