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
@GraphQlTest(ExpenseController.class)
public class ExpenseControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getExpenseById_validExpenseId_returnsExpenseDetails() {
        this.graphQlTester
                .document("""
                            query expenseDetails($id: ID!) {
                                expenseById(id: $id) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
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
                .path("expenseById")
                .matchesJson("""
                            {
                                "id": "1",
                                "name": "Rent",
                                "amount": 1100,
                                "budget": {
                                    "id": "1",
                                    "year": 2024,
                                    "month": 10
                                },
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    void getExpenseById_invalidExpenseId_returnsNull() {
        this.graphQlTester
                .document("""
                            query expenseDetails($id: ID!) {
                                expenseById(id: $id) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "-1")
                .execute()
                .path("expenseById")
                .equals(null);
    }

    @Test
    void getExpensesByBudgetId_validBudgetId_returnsExpenseDetailsList() {
        this.graphQlTester
                .document("""
                            query expensesByBudgetId($budgetId: ID!) {
                                expensesByBudgetId(budgetId: $budgetId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("budgetId", "1")
                .execute()
                .path("expensesByBudgetId")
                .matchesJson("""
                                [
                                    {
                                        "id": "1",
                                        "name": "Rent",
                                        "amount": 1100.0,
                                        "budget": {
                                        "id": "1",
                                        "year": 2024,
                                        "month": 10
                                        },
                                        "user": {
                                        "id": "1",
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                        }
                                    },
                                    {
                                        "id": "8",
                                        "name": "Water",
                                        "amount": 45.67,
                                        "budget": {
                                        "id": "1",
                                        "year": 2024,
                                        "month": 10
                                        },
                                        "user": {
                                        "id": "1",
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                        }
                                    }
                                    ]
                        """);
    }

    @Test
    void getExpensesByBudgetId_invalidBudgetId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query expensesByBudgetId($budgetId: ID!) {
                                expensesByBudgetId(budgetId: $budgetId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("budgetId", "-1")
                .execute()
                .path("expensesByBudgetId")
                .matchesJson("""
                            []
                        """);
    }

    @Test
    void getExpensesByUserId_validUserId_returnsExpenseDetailsList() {
        this.graphQlTester
                .document("""
                            query expensesByUserId($userId: ID!) {
                                expensesByUserId(userId: $userId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        id
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("userId", "1")
                .execute()
                .path("expensesByUserId")
                .matchesJson("""
                                [
                                    {
                                        "id": "1",
                                        "name": "Rent",
                                        "amount": 1100.0,
                                        "budget": {
                                        "id": "1",
                                        "year": 2024,
                                        "month": 10
                                        },
                                        "user": {
                                        "id": "1",
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                        }
                                    },
                                    {
                                        "id": "5",
                                        "name": "Graeter's",
                                        "amount": 15.32,
                                        "budget": {
                                        "id": "5",
                                        "year": 2025,
                                        "month": 2
                                        },
                                        "user": {
                                        "id": "1",
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                        }
                                    },
                                    {
                                        "id": "8",
                                        "name": "Water",
                                        "amount": 45.67,
                                        "budget": {
                                        "id": "1",
                                        "year": 2024,
                                        "month": 10
                                        },
                                        "user": {
                                        "id": "1",
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                        }
                                    }
                                ]
                        """);
    }

    @Test
    void getExpensesByUserId_invalidUserId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query expensesByUserId($userId: ID!) {
                                expensesByUserId(userId: $userId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
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
                .path("expensesByUserId")
                .matchesJson("""
                            []
                        """);
    }
}
