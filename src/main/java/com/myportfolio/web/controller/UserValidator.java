package com.myportfolio.web.controller;

import com.myportfolio.web.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;
        String id = user.getId();
        String pwd = user.getPwd();

//        if (id == null || "".equals(id.trim())) {
//            errors.rejectValue("id", "required");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");

        if(id==null || id.length() < 5 || id.length() >12) {
            errors.rejectValue("id", "invalidLength", new String[]{"5", "12"}, null);
        }
    }
}
