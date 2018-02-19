package com.example.el.objectsroute.presentation.view;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

/**
 * Created by el on 19.02.2018.
 */

public interface BaseView extends MvpView {
    void showError(final @StringRes int error);
}
