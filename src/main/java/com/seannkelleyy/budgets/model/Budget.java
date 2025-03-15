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
 */
public record Budget(Integer id, Integer year, Integer month, double income, double expenses, Integer userId) {
    
    private static List<Budget> budgets = Arrays.asList(
            new Budget(1, 2024, 10, 5000.00, 3000.00, 1),
            new Budget(2, 2024, 11, 5000.00, 3000.00, 2),
            new Budget(3, 2024, 12, 5000.00, 3000.00, 2),
            new Budget(4, 2025, 1, 5000.00, 3000.00, 3),
            new Budget(5, 2025, 2, 5000.00, 3000.00, 1),
            new Budget(6, 2025, 3, 5000.00, 3000.00, 3)
    );
    public static Budget getById(Integer id)
    {
        return budgets.stream().filter(budget -> budget.id().equals(id)).findFirst().orElse(null);
    }

    public static List<Budget> getByUserId(Integer userId)
    {
        return budgets.stream().filter(budget -> budget.userId().equals(userId)).toList();
    }

}
