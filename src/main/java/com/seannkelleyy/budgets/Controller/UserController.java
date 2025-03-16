/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.seannkelleyy.budgets.model.User;

/**
 *
 * @author seankelley
 */
 @Controller
 public class UserController {

    @QueryMapping
    public User userById(@Argument Integer id) {
        return User.getById(id);
    }

    @QueryMapping
    public List<User> allUsers() {
        return User.getAll();
    }
    
    @MutationMapping
    public User createUser(@Argument String firstName, @Argument String lastName) {
        return User.createUser(firstName, lastName);
    }

    @MutationMapping
    public User updateUser(@Argument Integer id, @Argument String firstName, @Argument String lastName) {
        return User.updateUser(id, firstName, lastName);
    }

    @MutationMapping
    public User deleteUser(@Argument Integer id) {
        return User.deleteUser(id);
    }
}
