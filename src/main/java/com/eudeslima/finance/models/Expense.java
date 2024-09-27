package com.eudeslima.finance.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="expenses")
public class Expense {

    @Id
    Long id;
    String name;
    String description;

}
