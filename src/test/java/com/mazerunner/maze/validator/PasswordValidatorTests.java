package com.mazerunner.maze.validator;

import com.mazerunner.maze.validation.PasswordValidator;
import com.mazerunner.maze.validation.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTests {

    private PasswordValidator validator;

    @BeforeEach
    public void beforeAll(){
        validator = new PasswordValidator();
    }

    // Todo: test edge cases
    @Test
    public void validate_ValidPassword(){
        // Arrange
        String pws = "1234Abcd!";

        // Act
        Result result = validator.validate(pws);

        // Assert
        Assertions.assertTrue(result.isSuccess());

    }

    @Test
    public void validate_TooShort(){
         // Arrange
        String pws = "Abcd!";

        // Act
        Result result = validator.validate(pws);

        // Assert
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    public void validate_TooLong(){
        // Arrange
        String pws = "AbcdAbcdAbcdAbcd!";

        // Act
        Result result = validator.validate(pws);

        // Assert
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    public void validate_NoDigit(){
        // Arrange
        String pws = "Abcdefghi!";

        // Act
        Result result = validator.validate(pws);

        // Assert
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    public void validate_NoCapital(){
        // Arrange
        String pws = "abcdefgh1!";

        // Act
        Result result = validator.validate(pws);

        // Assert
        Assertions.assertFalse(result.isSuccess());
    }

}
