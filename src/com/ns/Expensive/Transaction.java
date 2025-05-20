package com.ns.Expensive;

import java.time.LocalDate;

public class Transaction {
    private String type; // Income or Expense
    private double amount;
    private String category;
    private LocalDate date;

    public Transaction(String type, double amount, String category, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return type + "," + amount + "," + category + "," + date;
    }
}
