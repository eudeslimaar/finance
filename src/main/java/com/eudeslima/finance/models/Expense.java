package com.eudeslima.finance.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @Column(nullable = false)
    private int numberOfInstallments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "expense_id")
    private List<Installment> installments = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private boolean active = true;

    public Expense() {
    }

    public Expense(String name, String description, BigDecimal totalValue, int numberOfInstallments, LocalDate startDate, boolean equalInstallments) {

        this.name = name;
        this.description = description;
        this.totalValue = totalValue;
        this.numberOfInstallments = numberOfInstallments;
        this.startDate = startDate;

        if (equalInstallments) {
            generateEqualInstallments();
        }
    }

    public void generateEqualInstallments() {
        if (startDate == null) {
            throw new IllegalArgumentException("A data de início não pode ser nula.");
        }
        BigDecimal installmentValue = totalValue.divide(new BigDecimal(numberOfInstallments), 2, RoundingMode.HALF_UP);

        for (int i = 0; i < numberOfInstallments; i++) {
            LocalDate dueDate = startDate.plusMonths(i); // Gera a data de vencimento para cada parcela
            Installment installment = new Installment(installmentValue, dueDate);
            installments.add(installment);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
        if (this.startDate != null && this.totalValue != null && this.numberOfInstallments > 0) {
            generateEqualInstallments();
        }
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        if (this.startDate != null && this.totalValue != null && this.numberOfInstallments > 0) {
            generateEqualInstallments();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
