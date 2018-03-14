package com.example.el.objectsroute.ui.fragment;

import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.view.BaseView;

/**
 * Created by el on 15.02.2018.
 */

public class BaseFragment extends MvpAppCompatFragment implements BaseView {

    private View progressView;

    @Override
    public void showError(int error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    public void showDialog(@StringRes final int title,
                           @StringRes final int positiveButtonTitle,
                           DialogInterface.OnClickListener positiveButtonClickListener,
                           @StringRes final int negativeButtonTitle,
                           DialogInterface.OnClickListener negativeButtonClickListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setPositiveButton(positiveButtonTitle, positiveButtonClickListener)
                .setNegativeButton(negativeButtonTitle, negativeButtonClickListener)
                .show();
    }

    public void showDialog(@StringRes final int title,
                           @StringRes final int positiveButtonTitle,
                           DialogInterface.OnClickListener positiveButtonClickListener) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setPositiveButton(positiveButtonTitle, positiveButtonClickListener)
                .show();
    }

    public void showProgressBar() {
        if (progressView == null) {
            progressView = getView().findViewById(R.id.progressView);
        }
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        if (progressView == null) return;
        progressView.setVisibility(View.GONE);
    }
}

