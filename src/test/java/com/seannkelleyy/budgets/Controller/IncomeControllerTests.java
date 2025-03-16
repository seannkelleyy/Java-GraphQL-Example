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
@GraphQlTest(IncomeController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IncomeControllerTests {
    
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @Order(1)
    void getIncomeById_validIncomeId_returnsIncomeDetails() {
        this.graphQlTester
                .document("""
                            query incomeDetails($id: ID!) {
                                incomeById(id: $id) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "1")
                .execute()
                .path("incomeById")
                .matchesJson("""
                        {
                            "id": "1",
                            "name": "Paycheck",
                            "amount": 5000,
                            "budget": {
                                "id": "1",
                                "year": 2024,
                                "month": 10
                            },
                            "user": {
                                "firstName": "Sean",
                                "lastName": "Kelley"
                            }
                            }
                        """);
    }
    
    @Test
    @Order(2)
    void getIncomeById_invalidIncomeId_returnsNull() {
        this.graphQlTester
                .document("""
                            query incomeDetails($id: ID!) {
                                incomeById(id: $id) {
                                    id
                                    amount
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
                .path("incomeById")
                .equals(null);
    }

    @Test
    @Order(3)
    void getIncomeByUserId_validUserId_returnsIncomeDetailsList() {
        this.graphQlTester
                .document("""
                            query incomesByUserId($userId: ID!) {
                                incomesByUserId(userId: $userId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("userId", "1")
                .execute()
                .path("incomesByUserId")
                .matchesJson("""
                            [
                                {
                                    "id": "1",
                                    "name": "Paycheck",
                                    "amount": 5000,
                                    "budget": {
                                        "id": "1",
                                        "year": 2024,
                                        "month": 10
                                    },
                                    "user": {
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                    }
                                },
                                {
                                    "id": "5",
                                    "name": "Paycheck",
                                    "amount": 5000,
                                    "budget": {
                                        "id": "5",
                                        "year": 2025,
                                        "month": 2
                                    },
                                    "user": {
                                        "firstName": "Sean",
                                        "lastName": "Kelley"
                                    }
                                }
                            ]
                        """);
    }

    @Test
    @Order(4)
    void getIncomeByUserId_invalidUserId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query incomesByUserId($userId: ID!) {
                                incomesByUserId(userId: $userId) {
                                    id
                                    amount
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
                .path("incomesByUserId")
                .matchesJson("""
                            []
                        """);
    }

    @Test
    @Order(5)
    void getIncomeByBudgetId_validBudgetId_returnsIncomeDetailsList() {
        this.graphQlTester
                .document("""
                            query incomesByBudgetId($budgetId: ID!) {
                                incomesByBudgetId(budgetId: $budgetId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("budgetId", "4")
                .execute()
                .path("incomesByBudgetId")
                .matchesJson("""
                        [
                            {
                                "id": "4",
                                "name": "Paycheck",
                                "amount": 5000.0,
                                "budget": {
                                    "id": "4",
                                    "year": 2025,
                                    "month": 1
                                },
                                "user": {
                                    "firstName": "Lulah",
                                    "lastName": "Kelley"
                                }
                            },
                            {
                                "id": "7",
                                "name": "Tips",
                                "amount": 1000.0,
                                "budget": {
                                    "id": "4",
                                    "year": 2025,
                                    "month": 1
                                },
                                "user": {
                                    "firstName": "Lulah",
                                    "lastName": "Kelley"
                                }
                            }
                        ]
                        """);
    }

    @Test
    @Order(6)
    void getIncomeByBudgetId_invalidBudgetId_returnsEmptyList() {
        this.graphQlTester
                .document("""
                            query incomesByBudgetId($budgetId: ID!) {
                                incomesByBudgetId(budgetId: $budgetId) {
                                    id
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
                .path("incomesByBudgetId")
                .matchesJson("""
                            []
                        """);
    }

    @Test
    @Order(7)
    void createIncome_validIncomeDetails_returnsIncomeDetails() {
        this.graphQlTester
                .document("""
                            mutation createIncome($name: String!, $amount: Float!, $budgetId: ID!, $userId: ID!) {
                                createIncome(name: $name, amount: $amount, budgetId: $budgetId, userId: $userId) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("name", "Test Income")
                .variable("amount", 1000.0)
                .variable("budgetId", "1")
                .variable("userId", "1")
                .execute()
                .path("createIncome")
                .matchesJson("""
                        {
                            "id": "8",
                            "name": "Test Income",
                            "amount": 1000.0,
                            "budget": {
                                "id": "1",
                                "year": 2024,
                                "month": 10
                            },
                            "user": {
                                "firstName": "Sean",
                                "lastName": "Kelley"
                            }
                        }
                        """);
    }

    @Test
    @Order(8)
    void updateIncome_validIncomeDetails_returnsIncomeDetails() {
        this.graphQlTester
                .document(
                        """
                                    mutation updateIncome($id: ID!, $name: String!, $amount: Float!, $budgetId: ID!, $userId: ID!) {
                                        updateIncome(id: $id, name: $name, amount: $amount, budgetId: $budgetId, userId: $userId) {
                                            id
                                            name
                                            amount
                                            budget {
                                                id
                                                year
                                                month
                                            }
                                            user {
                                                firstName
                                                lastName
                                            }
                                        }
                                    }
                                """)
                .variable("id", "1")
                .variable("name", "Updated Paycheck")
                .variable("amount", 6000.0)
                .variable("budgetId", "1")
                .variable("userId", "1")
                .execute()
                .path("updateIncome")
                .matchesJson("""
                        {
                            "id": "1",
                            "name": "Updated Paycheck",
                            "amount": 6000.0,
                            "budget": {
                                "id": "1",
                                "year": 2024,
                                "month": 10
                            },
                            "user": {
                                "firstName": "Sean",
                                "lastName": "Kelley"
                            }
                        }
                        """);
    }
    
    @Test
    @Order(9)
    void deleteIncome_validIncomeId_returnsIncomeDetails() {
        this.graphQlTester
                .document("""
                            mutation deleteIncome($id: ID!) {
                                deleteIncome(id: $id) {
                                    id
                                    name
                                    amount
                                    budget {
                                        id
                                        year
                                        month
                                    }
                                    user {
                                        firstName
                                        lastName
                                    }
                                }
                            }
                        """)
                .variable("id", "1")
                .execute()
                .path("deleteIncome")
                .matchesJson("""
                        {
                            "id": "1",
                            "name": "Paycheck",
                            "amount": 5000.0,
                            "budget": {
                                "id": "1",
                                "year": 2024,
                                "month": 10
                            },
                            "user": {
                                "firstName": "Sean",
                                "lastName": "Kelley"
                            }
                        }
                        """);
    }
}
