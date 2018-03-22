package com.example.el.objectsroute.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.el.objectsroute.R;
import com.example.el.objectsroute.presentation.view.BaseView;
import com.example.el.objectsroute.repository.NetworkRepository;
import com.example.el.objectsroute.router.BaseRouter;
import com.example.el.objectsroute.ui.activity.AuthorizationActivity;
import com.example.el.objectsroute.ui.activity.BaseActivity;

/**
 * Created by el on 15.02.2018.
 */

public class BaseFragment extends MvpAppCompatFragment implements BaseView, BaseRouter {

    private View progressView;

    @Override
    public void showError(int error) {
        ((BaseActivity) getActivity()).showError(error);
    }

    @Override
    public void showDialog(int title, int positiveButtonTitle, DialogInterface.OnClickListener positiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener negativeButtonClickListener) {
        ((BaseActivity) getActivity()).showDialog(title, positiveButtonTitle, positiveButtonClickListener, negativeButtonTitle, negativeButtonClickListener);
    }

    @Override
    public void showDialog(int title, int positiveButtonTitle, DialogInterface.OnClickListener positiveButtonClickListener) {
        ((BaseActivity) getActivity()).showDialog(title, positiveButtonTitle, positiveButtonClickListener);
    }

    @Override
    public void showProgressBar() {
        if (progressView == null) {
            progressView = getView().findViewById(R.id.progressView);
        }
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if (progressView == null) return;
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void goBack() {
        getActivity().onBackPressed();
    }

    @Override
    public void goToAuthorization() {
        try {
            final Intent intent = new Intent(getContext(), AuthorizationActivity.class);
            startActivity(intent);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getActivity().finish();
            throw new NetworkRepository.GoToAuthException("Ошибка");

        } catch (NetworkRepository.GoToAuthException e) {
            e.printStackTrace();
        }
    }
}

