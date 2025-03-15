// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */

// package com.seannkelleyy.budgets.repository;

// import java.util.ArrayList;
// import java.util.List;
// import com.seannkelleyy.budgets.model.Author;

// import org.springframework.stereotype.Repository;

// import jakarta.annotation.PostConstruct;

// /**
//  *
//  * @author seankelley
//  */
// @Repository
// public class AuthorRepository {

//     private List<Author> authors = new ArrayList<>();

//     public List<Author> findAll() {
//         return authors;
//     }

//     public Author findById(int id) {
//         return authors.stream()
//                 .filter(author -> author.id() == id)
//                 .findFirst()
//                 .orElseThrow(() -> new RuntimeException("Author not found"));
//     }

//     public Author findByName(String name) {
//         return authors.stream()
//                 .filter(author -> author.fullName().equals(name))
//                 .findFirst()
//                 .orElseThrow(() -> new RuntimeException("Author not found"));
//     }

//     @PostConstruct
//     private void init() {
//         authors.add(new Author(1,"Josh","Long"));
//         authors.add(new Author(2,"Mark","Heckler"));
//         authors.add(new Author(3,"Greg","Turnquist"));
//     }
// }