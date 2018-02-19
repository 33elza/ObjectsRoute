package com.example.el.objectsroute.utils.validator;

import com.example.el.objectsroute.R;

/**
 * Created by el on 19.02.2018.
 */

public class EmailValidator extends BaseValidator<String> {

    @Override
    public boolean validate(String email, Callback callback) {
        if (email == null || email.isEmpty()) {
            callback.onError(R.string.empty_email_error);
            return false;
        } else {
            return true;
        }
    }

}
