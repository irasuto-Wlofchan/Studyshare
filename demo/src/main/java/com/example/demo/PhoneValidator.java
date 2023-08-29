package com.example.demo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone,String> {
    
    @Override
    public void initialize(Phone Phone){
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext cxt){
        if(input == null){
            return true;
        }
        return input.matches("[0-9()-]*");

    }
}
