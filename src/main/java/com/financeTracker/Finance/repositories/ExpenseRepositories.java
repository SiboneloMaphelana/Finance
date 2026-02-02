package com.financeTracker.Finance.repositories;

import com.financeTracker.Finance.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepositories extends JpaRepository<Expense, Integer> {
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
