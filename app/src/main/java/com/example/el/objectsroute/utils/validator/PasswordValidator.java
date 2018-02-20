package com.example.el.objectsroute.utils.validator;

import com.example.el.objectsroute.R;

/**
 * Created by el on 20.02.2018.
 */

public class PasswordValidator extends BaseValidator<String> {

    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @Override
    public boolean validate(String password, Callback callback) {
        if (password == null || password.isEmpty()) {
            callback.onError(R.string.empty_password_error);
            return false;
        } else if (!password.matches(passwordPattern)) {
            callback.onError(R.string.invalid_password_error);
            return false;
        } else {
            return true;
        }
    }
}

