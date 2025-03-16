/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.seannkelleyy.budgets.Controller;

import org.springframework.graphql.data.method.annotation.Argument;
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
    
        
}
