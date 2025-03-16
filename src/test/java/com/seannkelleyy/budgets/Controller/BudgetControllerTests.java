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
@GraphQlTest(BudgetController.class)
public class BudgetControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getBudgetById_validBudgetId_returnsBudgetDetails() {
        this.graphQlTester
                .document("""
                            query budgetDetails($id: ID!) {
                                budgetById(id: $id) {
                                    id
                                    year
                                    month
                                    income
                                    expenses
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "2")
                .execute()
                .path("budgetById")
                .matchesJson("""
                            {
                                "id": "2",
                                "year": 2024,
                                "month": 11,
                                "income": 5000,
                                "expenses": 3000,
                                "user": {
                                    "id": "2",
                                    "firstName": "Aalissia",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    void getBudgetById_invalidBudgetId_returnsNull() {
        this.graphQlTester
                .document("""
                            query budgetDetails($id: ID!) {
                                budgetById(id: $id) {
                                    id
                                    year
                                    month
                                    income
                                    expenses
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "4")
                .execute()
                .path("budgetById").equals(null);
    }

    @Test
    void getBudgetByUserId_validUserId_returnsBudgetDetailsList() {
        this.graphQlTester
                .document("""
                            query budgetsByUserId($userId: ID!) {
                                budgetsByUserId(userId: $userId) {
                                    id
                                    year
                                    month
                                    income
                                    expenses
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
                .path("budgetsByUserId")
                .matchesJson("""
                            [
                                {
                                    "id": "2",
                                    "year": 2024,
                                    "month": 11,
                                    "income": 5000,
                                    "expenses": 3000,
                                    "user": {
                                        "id": "2",
                                        "firstName": "Aalissia",
                                        "lastName": "Kelley"
                                    }
                                },
                                {
                                    "id": "3",
                                    "year": 2024,
                                    "month": 12,
                                    "income": 5000,
                                    "expenses": 3000,
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
    void getBudgetByUserId_validUserId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query budgetsByUserId($userId: ID!) {
                                budgetsByUserId(userId: $userId) {
                                    id
                                    year
                                    month
                                    income
                                    expenses
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("userId", "4")
                .execute()
                .path("budgetsByUserId")
                .matchesJson("""
                            []
                        """);
    }
}