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

    String document = """
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
    """;

    @Test
    void shouldGetSecondBudget() {
    this.graphQlTester
    .document(document)
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
}