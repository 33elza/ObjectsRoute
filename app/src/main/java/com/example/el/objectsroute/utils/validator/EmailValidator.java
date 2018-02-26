package com.example.el.objectsroute.utils.validator;

import com.example.el.objectsroute.R;

/**
 * Created by el on 19.02.2018.
 */

public class EmailValidator extends BaseValidator<String> {

    private static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";

    @Override
    public boolean validate(String email, Callback callback) {
        if (email == null || email.isEmpty()) {
            callback.onError(R.string.empty_email_error);
            return false;
        } else if (!email.matches(EMAIL_PATTERN)) {
            callback.onError(R.string.invalid_email_error);
            return false;
        } else {
            return true;
        }
    }

}
