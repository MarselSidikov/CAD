package ru.itis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 25.01.17
 * UsersController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@RestController
public class UsersController {
    @RequestMapping("/login")
    public void getLogin() {
        System.out.println("Hello!");
    }
}
