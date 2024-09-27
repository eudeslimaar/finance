package com.eudeslima.finance.services;

import com.eudeslima.finance.models.Expense;
import com.eudeslima.finance.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense) {
        if (expense.getInstallments().isEmpty()) {
            expense.generateEqualInstallments();
        }
        return expenseRepository.save(expense);
    }

    public ResponseEntity<Expense> getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Expense> updateExpense(Long id, Expense updatedExpense) {
        Optional<Expense> existingExpense = expenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            Expense expense = existingExpense.get();
            expense.setName(updatedExpense.getName());
            expense.setDescription(updatedExpense.getDescription());
            expense.setTotalValue(updatedExpense.getTotalValue());
            expense.setNumberOfInstallments(updatedExpense.getNumberOfInstallments());
            expense.setInstallments(updatedExpense.getInstallments()); // Atualizar a lista de parcelas
            expense.setStartDate(updatedExpense.getStartDate());
            expense.setActive(updatedExpense.isActive());
            return ResponseEntity.ok(expenseRepository.save(expense));
        }
        return ResponseEntity.notFound().build();
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
