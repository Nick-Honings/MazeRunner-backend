package com.mazerunner.maze.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    public Result validate(String password) {
        if (password == null) {
            return new Error("No password was provided");
        }

        if (password.length() < 8) {
            return new Error("Password is too short");
        }

        if (password.length() > 64) {
            return new Error("Password is too long");
        }

        return new Success("Password is correct");
    }
}
