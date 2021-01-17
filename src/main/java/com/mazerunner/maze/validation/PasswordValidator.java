package com.mazerunner.maze.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    // Todo: implement with regex expression ^.*(?=.{8,20})(?=.*[a-z])(?=.*[A-Z])(?=.*[-_]).*$

    public Result validate(String password) {
        if (password == null) {
            return new Error("No password was provided");
        }

        if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")){
            return new Error("Password does not match complexity");
        }

        if (password.length() < 8) {
            return new Error("Password is too short");
        }

        if (password.length() > 12) {
            return new Error("Password is too long");
        }

        return new Success("Password is correct");
    }
}
