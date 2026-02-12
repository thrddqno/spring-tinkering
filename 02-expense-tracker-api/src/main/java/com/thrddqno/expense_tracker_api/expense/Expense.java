package com.thrddqno.expense_tracker_api.expense;

import java.awt.List;
import java.time.LocalDate;

import com.thrddqno.expense_tracker_api.category.Category;
import com.thrddqno.expense_tracker_api.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 50)
	private String name;
	
	@Column(nullable = false, columnDefinition = "Decimal(10,2)")
	private Double amount;
	
	@Column(nullable = false)
	private LocalDate expenseDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
