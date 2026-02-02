package com.financeTracker.Finance.services;

import com.financeTracker.Finance.exceptions.ResourceNotFoundException;
import com.financeTracker.Finance.models.Expense;
import com.financeTracker.Finance.repositories.ExpenseRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepositories expenseRepositories;

    public Expense addExpense(Expense expense){
        return expenseRepositories.save(expense);
    }

    public List<Expense> getExpenses(LocalDate startDate, LocalDate endDate){
        if (startDate != null && endDate != null){
            return  expenseRepositories.findByDateBetween(startDate, endDate);

        }
        return expenseRepositories.findAll();
    }

    public Double getTotalExpenses(LocalDate startDate, LocalDate endDate){
        return expenseRepositories.findByDateBetween(startDate, endDate)
                .stream().
                mapToDouble(Expense::getAmount)
                .sum();
    }

    public boolean deleteExpense(Integer id){
        Expense expense = expenseRepositories.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found for this id"));
        expenseRepositories.delete(expense);
        return true;
    }

    public Expense updateExpense(Expense expenseDetails, Integer id){
        Expense expense = expenseRepositories.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense not found for this id"));
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());
        expense.setDescription(expenseDetails.getDescription());
        return  expenseRepositories.save(expense);
    }
}
