/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 *
 * @author seankelley
 */
 @GraphQlTest(GoalController.class)
 public class GoalControllerTests {
    
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getGoalById_validGoalId_returnsGoalDetails() {
        this.graphQlTester
                .document("""
                            query goalDetails($id: ID!) {
                                goalById(id: $id) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "1")
                .execute()
                .path("goalById")
                .matchesJson("""
                            {
                                "id": "1",
                                "goal": "Pay off house",
                                "isCompleted": false,
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    void getGoalById_invalidGoalId_returnsNull() {
        this.graphQlTester
                .document("""
                            query goalDetails($id: ID!) {
                                goalById(id: $id) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "7")
                .execute()
                .path("goalById").equals(null);
    }

    @Test
    void getGoalsByUserId_validUserId_returnsGoalDetailsList() {
        this.graphQlTester
                .document("""
                            query goalsByUserId($userId: ID!) {
                                goalsByUserId(userId: $userId) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("userId", "2")
                .execute()
                .path("goalsByUserId")
                .matchesJson("""
                            [
                                {
                                    "id": "3",
                                    "goal": "Take a vacation",
                                    "isCompleted": true,
                                    "user": {
                                        "id": "2",
                                        "firstName": "Aalissia",
                                        "lastName": "Kelley"
                                    }
                                },
                                {
                                    "id": "4",
                                    "goal": "Buy a new house",
                                    "isCompleted": true,
                                    "user": {
                                        "id": "2",
                                        "firstName": "Aalissia",
                                        "lastName": "Kelley"
                                    }
                                }
                            ]
                        """);
    }

    @Test
    void getGoalsByUserId_invalidUserId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query goalsByUserId($userId: ID!) {
                                goalsByUserId(userId: $userId) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("userId", "-1")
                .execute()
                .path("goalsByUserId")
                .matchesJson("""
                            []
                        """);
    }

    @Test
    void createGoal_validGoalDetails_returnsGoalDetails() {
        this.graphQlTester
                .document("""
                            mutation createGoal($goal: String!, $isCompleted: Boolean!, $userId: ID!) {
                                createGoal(goal: $goal, isCompleted: $isCompleted, userId: $userId) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("goal", "Test Goal")
                .variable("isCompleted", false)
                .variable("userId", "1")
                .execute()
                .path("createGoal")
                .matchesJson("""
                            {
                                "id": "7",
                                "goal": "Test Goal",
                                "isCompleted": false,
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    void updateGoal_validGoalDetails_returnsGoalDetails() {
        this.graphQlTester
                .document("""
                            mutation updateGoal($id: ID!, $goal: String!, $isCompleted: Boolean!, $userId: ID!) {
                                updateGoal(id: $id, goal: $goal, isCompleted: $isCompleted, userId: $userId) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "1")
                .variable("goal", "Updated Goal")
                .variable("isCompleted", true)
                .variable("userId", "1")
                .execute()
                .path("updateGoal")
                .matchesJson("""
                            {
                                "id": "1",
                                "goal": "Updated Goal",
                                "isCompleted": true,
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    void deleteGoal_validGoalId_returnsGoalDetails() {
        this.graphQlTester
                .document("""
                            mutation deleteGoal($id: ID!) {
                                deleteGoal(id: $id) {
                                    id
                                    goal
                                    isCompleted
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "1")
                .execute()
                .path("deleteGoal")
                .matchesJson("""
                            {
                                "id": "1",
                                "goal": "Pay off house",
                                "isCompleted": false,
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    } 
}
