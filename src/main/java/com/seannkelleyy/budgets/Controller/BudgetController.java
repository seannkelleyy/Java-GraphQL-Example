/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.seannkelleyy.budgets.model.Budget;
import com.seannkelleyy.budgets.model.User;

/**
 *
 * @author seankelley
 */
 @Controller
 public class BudgetController {
        @QueryMapping
        public Budget budgetById(@Argument Integer id) {
            return Budget.getById(id);
        }
    
        @QueryMapping
        public List<Budget> budgetsByUserId(@Argument Integer userId) {
            return Budget.getByUserId(userId);
        }

        @MutationMapping
        public Budget createBudget(@Argument Integer year, @Argument Integer month, @Argument double income,
                @Argument double expenses, @Argument Integer userId) {
            return Budget.createBudget(year, month, income, expenses, userId);
        }
        
        @MutationMapping
        public Budget updateBudget(@Argument Integer id, @Argument Integer year, @Argument Integer month,
                @Argument double income, @Argument double expenses, @Argument Integer userId) {
            return Budget.updateBudget(id, year, month, income, expenses, userId);
        }

        @MutationMapping
        public Budget deleteBudget(@Argument Integer id) {
            return Budget.deleteBudget(id);
        }
    
        @SchemaMapping
        public User user(Budget budget) {
            return User.getById(budget.userId());
        }
}
