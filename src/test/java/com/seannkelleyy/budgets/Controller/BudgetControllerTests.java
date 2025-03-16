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
@GraphQlTest(BudgetController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BudgetControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    @Order(1)
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
    @Order(2)
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
                .variable("id", "-1")
                .execute()
                .path("budgetById")
                .valueIsNull();
    }

    @Test
    @Order(3)
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
    @Order(4)
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

    @Test
    @Order(5)
    void createBudget_validBudgetDetails_returnsBudgetDetails() {
        this.graphQlTester
                .document(
                        """
                                    mutation createBudget($year: Int!, $month: Int!, $income: Float!, $expenses: Float!, $userId: ID!) {
                                        createBudget(year: $year, month: $month, income: $income, expenses: $expenses, userId: $userId) {
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
                .variable("year", 2025)
                .variable("month", 4)
                .variable("income", 5000.00)
                .variable("expenses", 3000.00)
                .variable("userId", 3)
                .execute()
                .path("createBudget")
                .matchesJson("""
                            {
                                "year": 2025,
                                "month": 4,
                                "income": 5000.0,
                                "expenses": 3000.0,
                                "user": {
                                    "id": "3",
                                    "firstName": "Lulah",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }
    
    @Test
    @Order(6)
    void updateBudget_validBudgetDetails_returnsBudgetDetails() {
        this.graphQlTester
                .document(
                        """
                                    mutation updateBudget($id: ID!, $year: Int!, $month: Int!, $income: Float!, $expenses: Float!, $userId: ID!) {
                                        updateBudget(id: $id, year: $year, month: $month, income: $income, expenses: $expenses, userId: $userId) {
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
                .variable("id", 1)
                .variable("year", 2025)
                .variable("month", 4)
                .variable("income", 5000.00)
                .variable("expenses", 3000.00)
                .variable("userId", 3)
                .execute()
                .path("updateBudget")
                .matchesJson("""
                            {
                                "year": 2025,
                                "month": 4,
                                "income": 5000.0,
                                "expenses": 3000.0,
                                "user": {
                                    "id": "3",
                                    "firstName": "Lulah",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }

    @Test
    @Order(7)
    void deleteBudget_validBudgetId_returnsBudgetDetails() {
        this.graphQlTester
                .document(
                        """
                                    mutation deleteBudget($id: ID!) {
                                        deleteBudget(id: $id) {
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
                .variable("id", 1)
                .execute()
                .path("deleteBudget")
                .matchesJson("""
                            {
                                "year": 2024,
                                "month": 10,
                                "income": 5000.0,
                                "expenses": 3000.0,
                                "user": {
                                    "id": "1",
                                    "firstName": "Sean",
                                    "lastName": "Kelley"
                                }
                            }
                        """);
    }
}