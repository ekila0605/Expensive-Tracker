package com.ns.Expensive;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void loadFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                LocalDate date = LocalDate.parse(parts[3], formatter);
                transactions.add(new Transaction(type, amount, category, date));
            }
        }
        br.close();
    }

    public void showMonthlySummary(String yearMonth) {
        double totalIncome = 0;
        double totalExpense = 0;
        Map<String, Double> expenseCategories = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getDate().toString().startsWith(yearMonth)) {
                if (t.getType().equalsIgnoreCase("Income")) {
                    totalIncome += t.getAmount();
                } else {
                    totalExpense += t.getAmount();
                    expenseCategories.merge(t.getCategory(), t.getAmount(), Double::sum);
                }
            }
        }

        System.out.println("\nMonthly Summary for " + yearMonth + ":");
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpense);
        System.out.println("Net Balance: " + (totalIncome - totalExpense));
        System.out.println("Expenses by Category:");
        for (String cat : expenseCategories.keySet()) {
            System.out.println(" - " + cat + ": " + expenseCategories.get(cat));
        }
    }
}