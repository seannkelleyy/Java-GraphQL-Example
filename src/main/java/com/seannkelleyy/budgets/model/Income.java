/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.seannkelleyy.budgets.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author seankelley
 */
public record Income(Integer id, String name, Double amount, Integer budgetId, Integer userId) {

    private static final List<Income> incomes = new ArrayList<>(List.of(
            new Income(1, "Paycheck", 5000.00, 1, 1),
            new Income(2, "Paycheck", 5000.00, 2, 2),
            new Income(3, "Paycheck", 5000.00, 3, 2),
            new Income(4, "Paycheck", 5000.00, 4, 3),
            new Income(5, "Paycheck", 5000.00, 5, 1),
            new Income(6, "Paycheck", 5000.00, 6, 3),
            new Income(7, "Tips", 1000.00, 4, 3)
    ));

    public static Income getById(Integer id) { 
        return incomes.stream()
                .filter(income -> income.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<Income> getByBudgetId(Integer budgetId) {
        return incomes.stream()
                .filter(income -> income.budgetId().equals(budgetId))
                .toList();
    }

    public static List<Income> getByUserId(Integer userId) {
        return incomes.stream()
                .filter(income -> income.userId().equals(userId))
                .toList();
    }
    
    public static Income createIncome(String name, Double amount, Integer budgetId, Integer userId) {
        Income newIncome = new Income(incomes.size() + 1, name, amount, budgetId, userId);
        incomes.add(newIncome);
        return newIncome;
    }

    public static Income updateIncome(Integer id, String name, Double amount, Integer budgetId, Integer userId) {
        Income incomeToUpdate = getById(id);
        if (incomeToUpdate != null) {
            incomeToUpdate = new Income(id, name, amount, budgetId, userId);
        }
        return incomeToUpdate;
    }

    public static Income deleteIncome(Integer id) {
        Income incomeToDelete = getById(id);
        if (incomeToDelete != null) {
            incomes.remove(incomeToDelete);
        }
        return incomeToDelete;
    }
}
