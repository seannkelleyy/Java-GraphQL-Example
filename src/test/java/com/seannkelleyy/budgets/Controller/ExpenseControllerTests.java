/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 *
 * @author seankelley
 */
@GraphQlTest(ExpenseController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @Order(1)
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
    @Order(2)
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
                .valueIsNull();
    }

    @Test
    @Order(3)
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
    @Order(4)
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
    @Order(5)
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
    @Order(6)
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

    @Test
    @Order(7)
    void createExpense_validExpenseDetails_returnsExpenseDetails() {
        this.graphQlTester
                .document("""
                            mutation createExpense($name: String!, $amount: Float!, $budgetId: ID!, $userId: ID!) {
                                createExpense(name: $name, amount: $amount, budgetId: $budgetId, userId: $userId) {
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
                .variable("name", "Test")
                .variable("amount", 100.00)
                .variable("budgetId", "1")
                .variable("userId", "1")
                .execute()
                .path("createExpense")
                .matchesJson("""
                            {
                                "id": "9",
                                "name": "Test",
                                "amount": 100.0,
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
    @Order(8)
    void updateExpense_validExpenseDetails_returnsExpenseDetails() {
        this.graphQlTester
                .document(
                        """
                                    mutation updateExpense($id: ID!, $name: String!, $amount: Float!, $budgetId: ID!, $userId: ID!) {
                                        updateExpense(id: $id, name: $name, amount: $amount, budgetId: $budgetId, userId: $userId) {
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
                .variable("id", "9")
                .variable("name", "Test")
                .variable("amount", 100.00)
                .variable("budgetId", "1")
                .variable("userId", "1")
                .execute()
                .path("updateExpense")
                .matchesJson("""
                            {
                                "id": "9",
                                "name": "Test",
                                "amount": 100.0,
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
    @Order(9)
    void deleteExpense_validExpenseId_returnsExpenseDetails() {
        this.graphQlTester
                .document("""
                            mutation deleteExpense($id: ID!) {
                                deleteExpense(id: $id) {
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
                .variable("id", "9")
                .execute()
                .path("deleteExpense")
                .matchesJson("""
                            {
                                "id": "9",
                                "name": "Test",
                                "amount": 100.0,
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
}
