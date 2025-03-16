/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.seannkelleyy.budgets.model.Budget;
import com.seannkelleyy.budgets.model.Expense;
import com.seannkelleyy.budgets.model.User;

/**
 *
 * @author seankelley
 */
 @Controller
 public class ExpenseController {

    @QueryMapping
    public Expense expenseById(@Argument Integer id) {
        return Expense.getById(id);
    }

    @QueryMapping
    public List<Expense> expensesByUserId(@Argument Integer userId) {
        return Expense.getByUserId(userId);
    }

    @QueryMapping
    public List<Expense> expensesByBudgetId(@Argument Integer budgetId) {
        return Expense.getByBudgetId(budgetId);
    }

    @SchemaMapping
    public User user(Expense expense) {
        return User.getById(expense.userId());
    }

    @SchemaMapping
    public Budget budget(Expense expense) {
        return Budget.getById(expense.budgetId());
    }
}
