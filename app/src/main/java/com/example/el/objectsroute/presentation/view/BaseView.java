package com.example.el.objectsroute.presentation.view;

import android.content.DialogInterface;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

/**
 * Created by el on 19.02.2018.
 */

public interface BaseView extends MvpView {
    void showError(final @StringRes int error);

    void showDialog(@StringRes final int title,
                    @StringRes final int positiveButtonTitle,
                    DialogInterface.OnClickListener positiveButtonClickListener,
                    @StringRes final int negativeButtonTitle,
                    DialogInterface.OnClickListener negativeButtonClickListener);

    void showDialog(@StringRes final int title,
                    @StringRes final int positiveButtonTitle,
                    DialogInterface.OnClickListener positiveButtonClickListener);

    void showProgressBar();

    void hideProgressBar();
}
