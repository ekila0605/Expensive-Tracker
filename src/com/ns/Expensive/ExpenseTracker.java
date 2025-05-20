package com.ns.Expensive;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Income/Expense");
            System.out.println("2. Load from File");
            System.out.println("3. Show Monthly Summary");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter transaction type (Income/Expense): ");
                String type = scanner.nextLine();

                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.print("Enter category (e.g., Salary, Business, Food, Rent, Travel): ");
                String category = scanner.nextLine();

                System.out.print("Enter date (yyyy-mm-dd): ");
                String dateStr = scanner.nextLine();
                LocalDate date = LocalDate.parse(dateStr, formatter);

                manager.addTransaction(new Transaction(type, amount, category, date));
                System.out.println("Transaction added successfully.");

            } else if (choice == 2) {
                System.out.print("Enter file name: ");
                String fileName = scanner.nextLine();
                try {
                    manager.loadFromFile(fileName);
                    System.out.println("File loaded successfully.");
                } catch (Exception e) {
                    System.out.println("Failed to load file: " + e.getMessage());
                }
            } else if (choice == 3) {
                System.out.print("Enter year and month (yyyy-MM): ");
                String yearMonth = scanner.nextLine();
                manager.showMonthlySummary(yearMonth);
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }
        }

        scanner.close();
    }
}
