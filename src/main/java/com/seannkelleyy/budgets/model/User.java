/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.seannkelleyy.budgets.model;

import java.util.ArrayList;
import java.util.List;

public record User (Integer id, String firstName, String lastName) {

    private static final List<User> users = new ArrayList<>(List.of(
            new User(1, "Sean", "Kelley"),
            new User(2, "Aalissia", "Kelley"),
            new User(3, "Lulah", "Kelley")
    ));

    public static User getById(Integer id) {
        return users.stream()
                .filter(user -> user.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<User> getAll() {
        return users;
    }
    
    public static User createUser(String firstName, String lastName) {
        User user = new User(users.size() + 1, firstName, lastName);
        users.add(user);
        return user;
    }

    public static User updateUser(Integer id, String firstName, String lastName) {
        User user = getById(id);
        if (user != null) {
            user = new User(id, firstName, lastName);
        }
        return user;
    }

    public static User deleteUser(Integer id) {
        User user = getById(id);
        if (user != null) {
            users.remove(user);
        }
        return user;
    }
}