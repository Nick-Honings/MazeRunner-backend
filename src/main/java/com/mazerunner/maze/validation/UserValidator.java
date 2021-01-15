package com.mazerunner.maze.validation;

import com.mazerunner.maze.domain.user.User;
import com.mazerunner.maze.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserValidator {


    private UserService userService;
    private PasswordValidator passwordValidator;

    public UserValidator(UserService userService, PasswordValidator passwordValidator) {
        this.userService = userService;
        this.passwordValidator = passwordValidator;
    }

    public Result validate(User user) {
        if (user.getName() == null || !StringUtils.hasText(user.getName())) {
            return new Error("No valid name was provided");
        }

        Result passwordResult = validatePassword(user.getPassword());
        if(!passwordResult.isSuccess()) {
            return passwordResult;
        }

        if (user.getConfirmPassword() == null) {
            return new Error("No confirm password was provided");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            return new Error("Passwords don't match");
        }

        if (userService.findByName(user.getName()).isPresent()) {
            return new Error("Username is already in use");
        }

        return new Success("Successfully registered user");
    }

    public Result validatePassword(String password) {
        return passwordValidator.validate(password);
    }


}
