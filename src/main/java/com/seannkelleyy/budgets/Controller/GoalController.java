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

import com.seannkelleyy.budgets.model.Goal;
import com.seannkelleyy.budgets.model.User;


/**
 *
 * @author seankelley
 */
@Controller
public class GoalController {
    @QueryMapping
    public Goal goalById(@Argument Integer id) {
        return Goal.getById(id);
    }

    @QueryMapping
    public List<Goal> goalsByUserId(@Argument Integer userId) {
        return Goal.getByUserId(userId);
    }
    
    @MutationMapping
    public Goal createGoal(@Argument String goal, @Argument Boolean isCompleted, @Argument Integer userId) {
        return Goal.createGoal(goal, isCompleted, userId);
    }

    @MutationMapping
    public Goal updateGoal(@Argument Integer id, @Argument String goal, @Argument Boolean isCompleted,
            @Argument Integer userId) {
        return Goal.updateGoal(id, goal, isCompleted, userId);
    }
    
    @MutationMapping
    public Goal deleteGoal(@Argument Integer id) {
        return Goal.deleteGoal(id);
    }
    
    @SchemaMapping
    public User user(Goal goal) {
        return User.getById(goal.userId());
    }
}
