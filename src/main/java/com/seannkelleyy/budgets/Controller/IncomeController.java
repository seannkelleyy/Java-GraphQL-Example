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
import com.seannkelleyy.budgets.model.Income;
import com.seannkelleyy.budgets.model.User;

/**
 *
 * @author seankelley
 */
 @Controller
 public class IncomeController {

    @QueryMapping
    public Income incomeById(@Argument Integer id) {
        return Income.getById(id);
    }

    @QueryMapping
    public List<Income> incomesByUserId(@Argument Integer userId) {
        return Income.getByUserId(userId);
    }

    @QueryMapping
    public List<Income> incomesByBudgetId(@Argument Integer budgetId) {
        return Income.getByBudgetId(budgetId);
    }

    @SchemaMapping
    public User user(Income income) {
        return User.getById(income.userId());
    }

    @SchemaMapping
    public Budget budget(Income income) {
        return Budget.getById(income.budgetId());
    }
}
