package com.example.el.objectsroute.ui.fragment;

import android.content.DialogInterface;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.el.objectsroute.presentation.view.BaseView;
import com.example.el.objectsroute.ui.activity.BaseActivity;

/**
 * Created by el on 15.02.2018.
 */

public class BaseFragment extends MvpAppCompatFragment implements BaseView {


    @Override
    public void showError(int error) {
        ((BaseActivity)getActivity()).showError(error);
    }

    @Override
    public void showDialog(int title, int positiveButtonTitle, DialogInterface.OnClickListener positiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener negativeButtonClickListener) {
        ((BaseActivity)getActivity()).showDialog(title, positiveButtonTitle, positiveButtonClickListener, negativeButtonTitle, negativeButtonClickListener);
    }

    @Override
    public void showDialog(int title, int positiveButtonTitle, DialogInterface.OnClickListener positiveButtonClickListener) {
        ((BaseActivity)getActivity()).showDialog(title, positiveButtonTitle, positiveButtonClickListener);
    }

    @Override
    public void showProgressBar() {
        ((BaseActivity)getActivity()).showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        ((BaseActivity)getActivity()).hideProgressBar();
    }
}

