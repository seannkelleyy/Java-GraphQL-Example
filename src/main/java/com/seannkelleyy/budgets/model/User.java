/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.seannkelleyy.budgets.model;

/**
 *
 * @author seankelley
 */
import java.util.Arrays;
import java.util.List;

public record User (Integer id, String firstName, String lastName) {

    private static final List<User> users = Arrays.asList(
            new User(1, "Sean", "Kelley"),
            new User(2, "Aalissia", "Kelley"),
            new User(3, "Lulah", "Kelley")
    );

    public static User getById(Integer id) {
        return users.stream()
				.filter(users -> users.id().equals(id))
				.findFirst()
				.orElse(null);
    }
}