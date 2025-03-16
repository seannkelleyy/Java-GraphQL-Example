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
  @GraphQlTest(UserController.class)
  public class UserControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void getUserById_validUserId_returnsUserDetails() {
        this.graphQlTester.document("""
                            query userDetails($id: ID!) {
                                userById(id: $id) {
                                    id
                                    firstName
                                    lastName
                                }
                            }
                """)
                .variable("id", "3")
                .execute()
                .path("userById")
                .matchesJson("""
                            {
                                "id": "3",
                                "firstName": "Lulah",
                                "lastName": "Kelley"
                            }
                        """);
    }
    
    @Test
    void getUserById_invalidUserId_returnsNull() {
        this.graphQlTester.document("""
                            query userDetails($id: ID!) {
                                userById(id: $id) {
                                    id
                                    firstName
                                    lastName
                                }
                            }
                """)
                .variable("id", "-1")
                .execute()
                .path("userById")
                .equals(null);
    }

}
