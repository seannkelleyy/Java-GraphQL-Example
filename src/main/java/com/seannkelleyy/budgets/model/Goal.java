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

public record Goal (Integer id, String goal, Boolean isCompleted, Integer userId) {

    private static final List<Goal> goals = new ArrayList<>(List.of(
            new Goal(1, "Pay off house", false, 1),
            new Goal(2, "Buy a new car", true, 3),
            new Goal(3, "Take a vacation", true, 2),
            new Goal(4, "Buy a new house", true, 2),
            new Goal(5, "Buy a new car", false, 3),
            new Goal(6, "Take a vacation", false, 1)
    ));

    public static Goal getById(Integer id) {
        return goals.stream()
                .filter(goal -> goal.id().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public static List<Goal> getByUserId(Integer userId) {
        return goals.stream()
                .filter(goal -> goal.userId().equals(userId))
                .toList();
    }

    public static Goal createGoal(String goal, Boolean isCompleted, Integer userId) {
        Goal newGoal = new Goal(goals.size() + 1, goal, isCompleted, userId);
        goals.add(newGoal);
        return newGoal;
    }

    public static Goal updateGoal(Integer id, String goal, Boolean isCompleted, Integer userId) {
        Goal goalToUpdate = getById(id);
        if (goalToUpdate != null) {
            goalToUpdate = new Goal(id, goal, isCompleted, userId);
        }
        return goalToUpdate;
    }

    public static Goal deleteGoal(Integer id) {
        Goal goalToDelete = getById(id);
        if (goalToDelete != null) {
            goals.remove(goalToDelete);
        }
        return goalToDelete;
    }
}