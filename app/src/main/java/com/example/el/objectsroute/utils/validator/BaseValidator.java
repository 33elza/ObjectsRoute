package com.example.el.objectsroute.utils.validator;

import android.support.annotation.StringRes;

/**
 * Created by el on 19.02.2018.
 */

public abstract class BaseValidator<T> {
    public abstract boolean validate(T data, Callback callback);

    public interface Callback {
        void onError(@StringRes int error);
    }
}
