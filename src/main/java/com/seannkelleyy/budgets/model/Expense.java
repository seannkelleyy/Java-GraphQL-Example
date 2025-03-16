/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.seannkelleyy.budgets.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author seankelley

    public static <any> getExpenses() {
        return expenses;
    }
 */
public record Expense(Integer Id, String name, Double amount, Integer budgetId, Integer userId) {
    
    private static final List<Expense> expenses = Arrays.asList(
            new Expense(1, "Rent", 1100.00, 1, 1),
            new Expense(2, "Gas", 113.29, 2, 2),
            new Expense(3, "Grocery Store", 212.67, 3, 2),
            new Expense(4, "H&M", 76.43, 4, 3),
            new Expense(5, "Graeter's", 15.32, 5, 1),
            new Expense(6, "Tickets", 56.73, 6, 3),
            new Expense(7, "Electric", 130.34, 4, 3),
            new Expense(8, "Water", 45.67, 1, 1)
    );

    public static Expense getById(Integer id) {
        return expenses.stream()
                .filter(expense -> expense.Id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Expense> getByBudgetId(Integer budgetId) {
        return expenses.stream()
                .filter(expense -> expense.budgetId().equals(budgetId))
                .toList();
    }

    public static List<Expense> getByUserId(Integer userId) {
        return expenses.stream()
                .filter(expense -> expense.userId().equals(userId))
                .toList();
    }
}
