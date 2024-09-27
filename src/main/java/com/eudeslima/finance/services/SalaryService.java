package com.eudeslima.finance.services;

import com.eudeslima.finance.models.Salary;
import com.eudeslima.finance.repositories.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public ResponseEntity<Salary> getSalaryById(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);
        return salary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Salary> updateSalary(Long id, Salary updatedSalary) {
        Optional<Salary> existingSalary = salaryRepository.findById(id);
        if (existingSalary.isPresent()) {
            Salary salary = existingSalary.get();
            salary.setSalaryAmount(updatedSalary.getSalaryAmount());
            salary.setMonth(updatedSalary.getMonth());
            return ResponseEntity.ok(salaryRepository.save(salary));
        }
        return ResponseEntity.notFound().build();
    }

    public void deleteSalary(Long id) {
        salaryRepository.deleteById(id);
    }
}
