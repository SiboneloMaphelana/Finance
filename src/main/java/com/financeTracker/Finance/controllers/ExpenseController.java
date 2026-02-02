package com.financeTracker.Finance.controllers;


import com.financeTracker.Finance.models.Expense;
import com.financeTracker.Finance.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return expenseService.getExpenses(startDate, endDate);
    }

    @GetMapping("/total")
    public Double getTotalExpenses(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return expenseService.getTotalExpenses(startDate, endDate);
    }

    @PostMapping("/expense")
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @PutMapping("/expense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Integer id, @RequestBody Expense expense){
        Expense updateExpense = expenseService.updateExpense(expense, id);
        return ResponseEntity.ok(updateExpense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteExpense(@PathVariable Integer id){
        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }
}
